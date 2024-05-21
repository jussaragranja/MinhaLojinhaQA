package test.carrinhos;

import entidade.response.UsuarioResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import requestspecification.BaseTest;

import static datafactory.CarrinhoDataFactory.buildCarrinhoRequest;
import static datafactory.CarrinhoDataFactory.deleteCarrinhoUser;
import static datafactory.LoginDataFactory.getAuthorization;
import static datafactory.ProdutoDataFactory.getListProdutos;
import static datafactory.UsuarioDataFactory.getFindUser;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static util.Constants.*;

public class PostCarrinhoTest extends BaseTest {


    @Test
    public void contratoCriarCarrinhoomSucesso(){

        UsuarioResponse usuarioResponse = getFindUser();

        RestAssured
                .given()
                .spec(requestSpecification(getAuthorization(usuarioResponse)))
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .body(buildCarrinhoRequest(getListProdutos(2), 2))
                .post(PATH_CARRINHOS)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .log().all()
                .body(PARAM_MESSAGE, equalTo(MESSAGE_CADASTRO_REALIZADO_COM_SUCESSO))
                .body(matchesJsonSchemaInClasspath("schema/postCarrinhoSucesso.json"));

        deleteCarrinhoUser(usuarioResponse);

    }

    @Test
    public void criarCarrinhoomSucesso(){

        UsuarioResponse usuarioResponse = getFindUser();

        RestAssured
                .given()
                .spec(requestSpecification(getAuthorization(usuarioResponse)))
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .body(buildCarrinhoRequest(getListProdutos(2), 2))
                .post(PATH_CARRINHOS)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .log().all()
                .body(PARAM_MESSAGE, equalTo(MESSAGE_CADASTRO_REALIZADO_COM_SUCESSO));

        deleteCarrinhoUser(usuarioResponse);

    }


}

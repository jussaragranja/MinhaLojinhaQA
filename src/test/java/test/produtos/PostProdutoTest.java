package test.produtos;

import entidade.response.UsuarioResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import requestspecification.BaseTest;

import static datafactory.LoginDataFactory.getAuthorization;
import static datafactory.ProdutoDataFactory.createNewProduto;
import static datafactory.UsuarioDataFactory.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static util.Constants.*;

public class PostProdutoTest extends BaseTest {

    @Test
    public void contratoCriarProdutoComSucesso(){

        UsuarioResponse usuarioResponse = getFindUser();

        RestAssured
                .given()
                .spec(requestSpecification(getAuthorization(usuarioResponse)))
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .body(createNewProduto())
                .post(PATH_PRODUTOS)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .log().all()
                .body(PARAM_MESSAGE, equalTo(MESSAGE_CADASTRO_REALIZADO_COM_SUCESSO))
                .body(matchesJsonSchemaInClasspath("schema/postProdutoSucesso.json"));
    }

    @Test
    public void criarProdutoComSucesso(){

        UsuarioResponse usuarioResponse = getFindUser();

        RestAssured
                .given()
                .spec(requestSpecification(getAuthorization(usuarioResponse)))
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .body(createNewProduto())
                .post(PATH_PRODUTOS)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .log().all()
                .body(PARAM_MESSAGE, equalTo(MESSAGE_CADASTRO_REALIZADO_COM_SUCESSO));
    }


}

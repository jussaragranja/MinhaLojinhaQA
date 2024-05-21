package test.carrinhos;

import entidade.response.UsuarioResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import requestspecification.BaseTest;

import static datafactory.CarrinhoDataFactory.criarCarrinhoUser;
import static datafactory.LoginDataFactory.getAuthorization;
import static datafactory.UsuarioDataFactory.getFindUser;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static util.Constants.*;

public class GetCarrinhoTest extends BaseTest {

     @Test
    public void getListCarrinhosComSucesso(){

        UsuarioResponse usuarioResponse = getFindUser();

        RestAssured
                .given()
                .spec(requestSpecification(getAuthorization(usuarioResponse)))
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .get(PATH_CARRINHOS)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body(matchesJsonSchemaInClasspath("schema/getCarrinhoSucesso.json"));

    }

}

package test.login;

import entidade.response.UsuarioResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import requestspecification.BaseTest;

import static datafactory.LoginDataFactory.createBodyLogin;
import static datafactory.UsuarioDataFactory.getFindUser;
import static org.hamcrest.Matchers.equalTo;
import static util.Constants.*;

public class LoginTest extends BaseTest {

    @Test
    public void realizarLoginSucesso(){

        UsuarioResponse usuarioResponse = getFindUser();

        RestAssured.
            given()
                    .contentType(ContentType.JSON)
            .when()
                    .body(createBodyLogin(usuarioResponse.getEmail(), usuarioResponse.getPassword()))
                    .log()
                    .all()
                    .post(PATH_LOGIN)
            .then()
                    .statusCode(HttpStatus.SC_OK)
                    .log().all()
                .body(PARAM_MESSAGE, equalTo(MESSAGE_LOGIN_REALIZADO_COM_SUCESSO));
    }
    @Test
    public void realizarLoginSemSucessoSenhaIncorreta(){

        UsuarioResponse usuarioResponse = getFindUser();

        RestAssured.
            given()
                .contentType(ContentType.JSON)
            .when()
                .body(createBodyLogin(usuarioResponse.getEmail(), RandomStringUtils.randomAlphanumeric(10)))
                .log()
                .all()
                .post("login")
            .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .log().all()
                .body(PARAM_MESSAGE, equalTo(MESSAGE_EMAIL_E_OU_SENHA_INVALIDOS));
    }
}

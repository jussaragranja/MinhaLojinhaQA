package test.usuarios;

import com.github.javafaker.Faker;
import entidade.UsuarioResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import requestspecification.BaseTest;

import static datafactory.UsuarioDataFactory.getFindUser;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static util.Constants.*;

public class GetUsuarioTest extends BaseTest {

    @Test
    public void contratoListarUsuariosComSucesso(){

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .get(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body(matchesJsonSchemaInClasspath("schema/getUsuarioSucesso.json"));
    }

    @Test
    public void listarUsuariosComSucesso(){

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .get(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all();
    }

    @Test
    public void listarUsuarioPorIdComSucesso(){

        UsuarioResponse usuarioResponse = getFindUser();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .queryParam(PARAM_ID, usuarioResponse.getId())
                .log().all()
            .when()
                .get(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body(PARAM_USUARIOS_NOME, containsString(usuarioResponse.getNome()),
                        PARAM_USUARIOS_EMAIL, equalTo(usuarioResponse.getEmail()),
                        PARAM_USUARIOS_PASSWORD, equalTo(usuarioResponse.getPassword()),
                        PARAM_USUARIOS_ADMINISTRADOR, equalTo(usuarioResponse.getAdministrador()));
    }

    @Test
    public void listarUsuariosComIdValidoENomeInvalido(){

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .log().all()
                .queryParam(PARAM_ID, getFindUser().getId())
                .queryParam(PARAM_NOME, RandomStringUtils.randomAlphabetic(5))
            .when()
                .get(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body(PARAM_QUANTIDADE, equalTo(0));

    }

    @Test
    public void listarUsuariosComTodosOsCamposEmBranco(){

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .queryParam(PARAM_ID, StringUtils.EMPTY)
                .queryParam(PARAM_NOME, StringUtils.EMPTY)
                .queryParam(PARAM_EMAIL, StringUtils.EMPTY)
                .queryParam(PARAM_ADMINISTRADOR, StringUtils.EMPTY)
                .log().all()
            .when()
                .get(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .body(PARAM_EMAIL, equalTo(MESSAGE_EMAIL_DEVE_SER_UMA_STRING),
                        PARAM_ADMINISTRADOR, equalTo(MESSAGE_ADMINISTRADOR_DEVE_SER_TRUE_OU_FALSE));

    }

    @Test
    public void listarUsuariosComCamposIdENomeEmBranco(){

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .queryParam(PARAM_ID, StringUtils.EMPTY)
                .queryParam(PARAM_NOME, StringUtils.EMPTY)
                .log().all()
            .when()
                .get(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body(PARAM_QUANTIDADE, not(0));

    }

    @Test
    public void listarUsuariosSemQueryParams(){

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .get(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body(PARAM_QUANTIDADE, not(0));

    }

    @Test
    public void listarUsuariosComEmailInvalidoValorNumerico(){

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam(PARAM_EMAIL, RandomUtils.nextInt(1, 20))
            .when()
                .get(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .body(PARAM_EMAIL, equalTo(MESSAGE_EMAIL_DEVE_SER_UMA_STRING));

    }

    @Test
    public void listarUsuariosComEmailInexistenteNaBase(){

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam(PARAM_EMAIL, Faker.instance().internet().emailAddress())
            .when()
                .get(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body(PARAM_QUANTIDADE, equalTo(0));

    }

    @Test
    public void listarUsuariosComEmailInvalidoComCaracteresEspeciais(){

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam(PARAM_EMAIL, RandomStringUtils.randomAscii(10))
            .when()
                .get(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .body(PARAM_EMAIL, equalTo(MESSAGE_EMAIL_DEVE_SER_UM_EMAIL_VALIDO));

    }

    @Test
    public void listarUsuariosComAdministradorInvalidoComCaracteresEspeciais(){

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam(PARAM_ADMINISTRADOR, RandomStringUtils.randomAscii(10))
            .when()
                .get(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .body(PARAM_ADMINISTRADOR, equalTo(MESSAGE_ADMINISTRADOR_DEVE_SER_TRUE_OU_FALSE));

    }

    @Test
    public void listarUsuariosComAdministradorVazio(){

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam(PARAM_ADMINISTRADOR, StringUtils.EMPTY)
            .when()
                .get(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .body(PARAM_ADMINISTRADOR, equalTo(MESSAGE_ADMINISTRADOR_DEVE_SER_TRUE_OU_FALSE));

    }

}

package test.usuarios;

import com.github.javafaker.Faker;
import entidade.Usuario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import requestspecification.BaseTest;

import static datafactory.UsuarioDataFactory.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static util.Constants.*;

public class PostUsuarioTest extends BaseTest {

    @Test
    public void contratoCriarUsuarioComSucesso(){

        Usuario usuario = getNewUser();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .body(usuario)
                .post(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .log().all()
                .body(PARAM_MESSAGE, equalTo(MESSAGE_CADASTRO_REALIZADO_COM_SUCESSO))
                .body(matchesJsonSchemaInClasspath("schema/postUsuarioSucesso.json"));
    }

    @Test
    public void criarUsuarioComSucesso(){

        Usuario usuario = getNewUser();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .body(usuario)
                .post(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .log().all()
                .body(PARAM_MESSAGE, equalTo(MESSAGE_CADASTRO_REALIZADO_COM_SUCESSO));
    }

    @Test
    public void criarUsuarioComEmailExistente(){

        Faker faker = new Faker();

        Usuario usuario = getUser(faker.name().fullName(),
                getFindUser().getEmail(),
                "teste"+faker.random().nextInt(1,10),
                faker.random().nextBoolean().toString());

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .body(usuario)
                .post(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .body(PARAM_MESSAGE, equalTo(MESSAGE_ESTE_EMAIL_JA_ESTA_SENDO_USADO));
    }

    @Test
    public void criarUsuarioComTodosOsCamposEmBranco(){

        Usuario usuario = getUser(StringUtils.EMPTY,
                StringUtils.EMPTY,
                StringUtils.EMPTY,
                StringUtils.EMPTY);

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .body(usuario)
                .post(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .body(PARAM_NOME, equalTo(MESSAGE_NOME_NAO_PODE_FICAR_EM_BRANCO),
                        PARAM_EMAIL, equalTo(MESSAGE_EMAIL_NAO_PODE_FICAR_EM_BRANCO),
                        PARAM_PASSWORD, equalTo(MESSAGE_PASSWORD_NAO_PODE_FICAR_EM_BRANCO),
                        PARAM_ADMINISTRADOR, equalTo(MESSAGE_ADMINISTRADOR_DEVE_SER_TRUE_OU_FALSE)
                        );

    }

    @Test
    public void criarUsuarioComBodyEmBranco(){

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .body("{}")
                .post(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .body(PARAM_NOME, equalTo(MESSAGE_NOME_EH_OBRIGATORIO),
                        PARAM_EMAIL, equalTo(MESSAGE_EMAIL_EH_OBRIGATORIO),
                        PARAM_PASSWORD, equalTo(MESSAGE_PASSWORD_EH_OBRIGATORIO),
                        PARAM_ADMINISTRADOR, equalTo(MESSAGE_ADMINISTRADOR_EH_OBRIGATORIO)
                );

    }

    @Test
    public void criarUsuarioComCampoAdministradorInvalido(){

            Faker faker = new Faker();

            Usuario usuario = getUser(faker.name().fullName(),
                    faker.internet().emailAddress(),
                    "teste"+faker.random().nextInt(1,10),
                    faker.random().nextInt(1,10).toString());

            RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .log().all()
                .when()
                    .body(usuario)
                    .post(PATH_USERS)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .log().all()
                    .body(PARAM_ADMINISTRADOR, equalTo(MESSAGE_ADMINISTRADOR_DEVE_SER_TRUE_OU_FALSE));
    }

    @Test
    public void criarUsuarioComNomeEmBranco(){

        Faker faker = new Faker();

        Usuario usuario = getUser("",
                faker.internet().emailAddress(),
                "teste"+faker.random().nextInt(1,10),
                faker.random().nextBoolean().toString());

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .body(usuario)
                .post(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .body(PARAM_NOME, equalTo(MESSAGE_NOME_NAO_PODE_FICAR_EM_BRANCO));
    }

    @Test
    public void criarUsuarioComEmailEmBranco(){

        Faker faker = new Faker();

        Usuario usuario = getUser(faker.name().fullName(),
                StringUtils.EMPTY,
                "teste"+faker.random().nextInt(1,10),
                faker.random().nextBoolean().toString());

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .body(usuario)
                .post(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .body(PARAM_EMAIL, equalTo(MESSAGE_EMAIL_NAO_PODE_FICAR_EM_BRANCO));
    }

    @Test
    public void criarUsuarioComEmailInvalido(){

        Faker faker = new Faker();

        Usuario usuario = getUser(faker.name().fullName(),
                RandomStringUtils.randomAlphabetic(5),
                "teste"+faker.random().nextInt(1,10),
                faker.random().nextBoolean().toString());

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .body(usuario)
                .post(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .body(PARAM_EMAIL, equalTo(MESSAGE_EMAIL_DEVE_SER_UM_EMAIL_VALIDO));
    }

    @Test
    public void criarUsuarioComPasswordEmBranco(){

        Faker faker = new Faker();

        Usuario usuario = getUser(faker.name().fullName(),
                faker.internet().emailAddress(),
                null,
                faker.random().nextBoolean().toString());

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .body(usuario)
                .post(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .body(PARAM_PASSWORD, equalTo(MESSAGE_PASSWORD_DEVE_SER_UMA_STRING));

    }

    @Test
    public void criarUsuarioComAdministradorEmBranco(){

        Faker faker = new Faker();

        Usuario usuario = getUser(faker.name().fullName(),
                faker.internet().emailAddress(),
                "teste"+faker.random().nextInt(1,10),
                null);

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .body(usuario)
                .post(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .body(PARAM_ADMINISTRADOR, equalTo(MESSAGE_ADMINISTRADOR_DEVE_SER_TRUE_OU_FALSE));

    }

    @Test
    public void criarUsuarioComNomeNulo(){

        Faker faker = new Faker();

        Usuario usuario = getUser(null,
                faker.internet().emailAddress(),
                "teste"+faker.random().nextInt(1,10),
                faker.random().nextBoolean().toString());

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .log().all()
                .when()
                    .body(usuario)
                    .post(PATH_USERS)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .body(PARAM_NOME, equalTo(MESSAGE_NOME_DEVE_SER_UMA_STRING));
    }

    @Test
    public void criarUsuarioComEmailNulo(){

        Faker faker = new Faker();

        Usuario usuario = getUser(faker.name().fullName(),
                null,
                "teste"+faker.random().nextInt(1,10),
                faker.random().nextBoolean().toString());

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .body(usuario)
                .post(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .body(PARAM_EMAIL, equalTo(MESSAGE_EMAIL_DEVE_SER_UMA_STRING));
    }

    @Test
    public void criarUsuarioComPasswordNulo(){

        Faker faker = new Faker();

        Usuario usuario = getUser(faker.name().fullName(),
                faker.internet().emailAddress(),
                null,
                faker.random().nextBoolean().toString());

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .body(usuario)
                .post(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .body(PARAM_PASSWORD, equalTo(MESSAGE_PASSWORD_DEVE_SER_UMA_STRING));

    }

    @Test
    public void criarUsuarioComAdministradorNulo(){

        Faker faker = new Faker();

        Usuario usuario = getUser(faker.name().fullName(),
                faker.internet().emailAddress(),
            "teste"+faker.random().nextInt(1,10),
                null);

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .body(usuario)
                .post(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .body(PARAM_ADMINISTRADOR, equalTo(MESSAGE_ADMINISTRADOR_DEVE_SER_TRUE_OU_FALSE));
    }

}

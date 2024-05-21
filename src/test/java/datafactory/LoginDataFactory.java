package datafactory;

import entidade.Login;
import entidade.response.UsuarioResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import static util.Constants.PATH_LOGIN;

public class LoginDataFactory {

    public static Login createBodyLogin(String email, String senha){
        return Login.builder()
                .email(email)
                .password(senha)
                .build();
    }

    public static String getAuthorization(UsuarioResponse usuarioResponse){

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .body(createBodyLogin(usuarioResponse.getEmail(), usuarioResponse.getPassword()))
            .when()
                .post(PATH_LOGIN)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .extract().response().jsonPath().get("authorization");

    }

}

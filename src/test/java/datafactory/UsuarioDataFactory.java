package datafactory;

import com.github.javafaker.Faker;
import entidade.Usuario;
import entidade.UsuarioResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static util.Constants.PATH_USERS;

public class UsuarioDataFactory {

    public static Usuario getUser(String nome, String email, String password, String administrador){
        return Usuario.builder()
                .nome(nome)
                .email(email)
                .password(password)
                .administrador(administrador)
                .build();
    }

    public static Usuario getNewUser(){
        Faker faker = new Faker();
        return Usuario.builder()
                .nome(faker.name().fullName())
                .email(faker.internet().emailAddress())
                .password("teste"+faker.random().nextInt(1,10))
                .administrador(faker.random().nextBoolean().toString())
                .build();
    }
    
    public static UsuarioResponse getFindUser(){

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .get(PATH_USERS)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .extract().response().jsonPath().getObject("usuarios[0]", UsuarioResponse.class);

    }

}

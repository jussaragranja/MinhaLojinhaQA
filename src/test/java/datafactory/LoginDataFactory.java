package datafactory;

import com.github.javafaker.Faker;
import entidade.Login;
import entidade.Usuario;
import entidade.UsuarioResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import static util.Constants.PATH_USERS;

public class LoginDataFactory {

    public static Login createBodyLogin(String email, String senha){
        return Login.builder()
                .email(email)
                .password(senha)
                .build();
    }

}

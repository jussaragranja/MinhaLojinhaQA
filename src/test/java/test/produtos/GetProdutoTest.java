package test.produtos;

import com.github.javafaker.Faker;
import entidade.response.ProdutoResponse;
import entidade.response.UsuarioResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import requestspecification.BaseTest;

import static datafactory.ProdutoDataFactory.getFindProduto;
import static datafactory.UsuarioDataFactory.getFindUser;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static util.Constants.*;

public class GetProdutoTest extends BaseTest {

    @Test
    public void contratoListarProdutosComSucesso(){

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .get(PATH_PRODUTOS)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body(matchesJsonSchemaInClasspath("schema/getProdutoSucesso.json"))
                .body(PARAM_QUANTIDADE, not(0));
    }

    @Test
    public void listarProdutosComSucesso(){

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .get(PATH_PRODUTOS)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body(PARAM_QUANTIDADE, not(0));
    }

    @Test
    public void listarProdutosPorIdComSucesso(){

        ProdutoResponse produtoResponse = getFindProduto();

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .queryParam(PARAM_ID, produtoResponse.getId())
                .log().all()
            .when()
                .get(PATH_PRODUTOS)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body(PARAM_QUANTIDADE, equalTo(1),
                        PARAM_PRODUTOS_NOME, equalTo(produtoResponse.getNome()),
                        PARAM_PRODUTOS_PRECO, equalTo(produtoResponse.getPreco()),
                        PARAM_PRODUTOS_DESCRICAO, equalTo(produtoResponse.getDescricao()),
                        PARAM_PRODUTOS_QUANTIDADE, equalTo(produtoResponse.getQuantidade()));
    }

}

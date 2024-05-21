package datafactory;

import com.github.javafaker.Faker;
import entidade.Produto;
import entidade.response.ProdutoIdResponse;
import entidade.response.ProdutoResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static util.Constants.PATH_PRODUTOS;

public class ProdutoDataFactory {

    public static Produto createBodyProduto(String nome, int preco, String descricao, int quantidade){
        return Produto.builder()
                .nome(nome)
                .preco(preco)
                .descricao(descricao)
                .quantidade(quantidade)
                .build();
    }

    public static Produto createNewProduto(){
        Faker faker = new Faker();
        return Produto.builder()
                .nome(faker.name().fullName())
                .preco(Integer.parseInt(faker.number().digits(4)))
                .descricao(faker.lorem().paragraph())
                .quantidade(RandomUtils.nextInt(1,10))
                .build();
    }

    public static ProdutoResponse getFindProduto(){

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .get(PATH_PRODUTOS)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .extract().response().jsonPath().getObject("produtos[0]", ProdutoResponse.class);

    }

    public static ProdutoResponse getFindProdutoByIndex(int index){

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .get(PATH_PRODUTOS)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .extract().response().jsonPath().getObject("produtos["+index+"]", ProdutoResponse.class);

    }

    public static List<ProdutoResponse> getListProdutos(int quantidade){

        List<ProdutoResponse> produtoResponseList = new ArrayList<>();

        for (int i = 0; i < quantidade; i++){
            produtoResponseList.add(getFindProdutoByIndex(i));
        }

        return produtoResponseList;
    }

   public static List<ProdutoIdResponse> getListIdProdutos(){
      return RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .log().all()
                .when()
                    .get(PATH_PRODUTOS)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .log().all().extract().response().jsonPath().getList("produtos", ProdutoIdResponse.class);
        }

}

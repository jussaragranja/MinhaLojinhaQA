package test.carrinhos;

import entidade.response.UsuarioResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import requestspecification.BaseTest;

import static datafactory.CarrinhoDataFactory.buildCarrinhoRequest;
import static datafactory.CarrinhoDataFactory.criarCarrinhoUser;
import static datafactory.LoginDataFactory.getAuthorization;
import static datafactory.ProdutoDataFactory.getListProdutos;
import static datafactory.UsuarioDataFactory.getFindUser;
import static org.hamcrest.Matchers.equalTo;
import static util.Constants.*;

public class DeleteCarrinhoTest extends BaseTest {

     @Test
    public void deleteCancelarCompraComSucesso(){

        UsuarioResponse usuarioResponse = getFindUser();
        criarCarrinhoUser(usuarioResponse);

        RestAssured
                .given()
                .spec(requestSpecification(getAuthorization(usuarioResponse)))
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .delete(PATH_CARRINHOS_CANCELAR_COMPRA)
            .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all()
                .body(PARAM_MESSAGE, equalTo(MESSAGE_REGISTRO_EXCLUIDO_COM_SUCESSO_ESTOQUE_DOS_PRODUTOS_REABASTECIDO));

    }

    @Disabled("Mensagem de erro mas status code 200 - OK")
    @Test
    public void deleteCancelarCompraSemCarrinho(){

        UsuarioResponse usuarioResponse = getFindUser();

        RestAssured
                .given()
                .spec(requestSpecification(getAuthorization(usuarioResponse)))
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .delete(PATH_CARRINHOS_CANCELAR_COMPRA)
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .log().all()
                .body(PARAM_MESSAGE, equalTo(MESSAGE_NAO_FOI_ENCONTRADO_CARRINHO_PARA_ESSE_USUARIO));

    }

}

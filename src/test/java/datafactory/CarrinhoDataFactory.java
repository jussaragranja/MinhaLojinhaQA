package datafactory;

import entidade.request.CarrinhoRequest;
import entidade.request.ListCarrinhoProdutoRequest;
import entidade.response.ProdutoResponse;
import entidade.response.UsuarioResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

import static datafactory.LoginDataFactory.getAuthorization;
import static datafactory.ProdutoDataFactory.getListProdutos;
import static org.hamcrest.Matchers.equalTo;
import static requestspecification.BaseTest.requestSpecification;
import static util.Constants.*;

public class CarrinhoDataFactory {

    public static ListCarrinhoProdutoRequest buildCarrinhoRequest(List<ProdutoResponse> produtos, int quantidadeFixa) {
        List<CarrinhoRequest> carrinhoRequests = produtos.stream()
                .map(produto -> CarrinhoRequest.builder()
                        .idProduto(produto.getId())
                        .quantidade(quantidadeFixa)
                        .build())
                .collect(Collectors.toList());

        return ListCarrinhoProdutoRequest.builder()
                .carrinhoRequestList(carrinhoRequests)
                .build();
    }

    public static void deleteCarrinhoUser(UsuarioResponse usuarioResponse){
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

    public static void criarCarrinhoUser(UsuarioResponse usuarioResponse){
        RestAssured
                .given()
                .spec(requestSpecification(getAuthorization(usuarioResponse)))
                .contentType(ContentType.JSON)
                .log().all()
            .when()
                .body(buildCarrinhoRequest(getListProdutos(2), 2))
                .post(PATH_CARRINHOS)
            .then()
                .statusCode(HttpStatus.SC_CREATED)
                .log().all()
                .body(PARAM_MESSAGE, equalTo(MESSAGE_CADASTRO_REALIZADO_COM_SUCESSO));
    }


}

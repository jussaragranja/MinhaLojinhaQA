package datafactory;

import entidade.request.CarrinhoRequest;
import entidade.request.ListCarrinhoProdutoRequest;
import entidade.response.ProdutoResponse;

import java.util.List;
import java.util.stream.Collectors;

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


}

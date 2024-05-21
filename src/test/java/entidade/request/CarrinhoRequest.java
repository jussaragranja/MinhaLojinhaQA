package entidade.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarrinhoRequest {

    private String idProduto;
    private int quantidade;

}

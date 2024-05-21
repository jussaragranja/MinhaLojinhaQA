package entidade.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListCarrinhoProdutoRequest {

    @JsonProperty(value = "produtos")
    List<CarrinhoRequest> carrinhoRequestList;

}

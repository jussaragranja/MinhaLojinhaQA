package entidade.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProdutoResponse {

    private String nome;
    private int preco;
    private String descricao;
    private int quantidade;

    @JsonProperty(value = "_id")
    private String id;

}

package entidade;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Produto {

    private String nome;
    private int preco;
    private String descricao;
    private int quantidade;

}

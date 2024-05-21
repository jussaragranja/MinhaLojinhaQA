package entidade.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
public class UsuarioResponse {

    private String nome;
    private String email;
    private String password;
    private String administrador;

    @JsonProperty(value = "_id")
    private String id;

}

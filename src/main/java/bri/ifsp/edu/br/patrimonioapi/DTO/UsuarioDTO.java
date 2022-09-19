package bri.ifsp.edu.br.patrimonioapi.DTO;

import bri.ifsp.edu.br.patrimonioapi.Model.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    private Long prontuario;
    private String nome;
    private String senha;
    private Integer nivelAcesso;

    public UsuarioDTO(Usuario usuario) {
        new ModelMapper().map(usuario, this);
    }

    public Usuario toUsuario() {
        return new ModelMapper().map(this, Usuario.class);
    }
}

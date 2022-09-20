package bri.ifsp.edu.br.patrimonioapi.DTO;

import bri.ifsp.edu.br.patrimonioapi.Model.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data //gera tanto os getters e setters como algumas outras pré-configurações
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    private Long prontuario;
    private String nome;
    private String senha;
    private Integer nivelAcesso;

    public UsuarioDTO(Usuario usuario) {
        new ModelMapper().map(usuario, this); // mapeia todos os itens vindo de outra classe para este DTO
    }

    public Usuario toUsuario() {
        return new ModelMapper().map(this, Usuario.class); //mapeia esse dto para a entidade, ou seja,
                                                                  //se um método estiver utilzando essa entidade
                                                                  //o mesmo retornará baseado neste DTO
    }
}

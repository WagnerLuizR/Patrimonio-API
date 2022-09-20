package bri.ifsp.edu.br.patrimonioapi.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity // define como classe entidade
@Getter // define os getters por padrão
@Setter // define os setters por padrão
@NoArgsConstructor //define o construtor vazio
@AllArgsConstructor //define o contrutor da classe
@Table(name = "TB_LISTA") // refrência da tabela do banco dessa entidade
public class Usuario {

    @Id                                                         // define variável como id
    @Column(name = "ID")                                        // referencia a coluna do banco id
    @GeneratedValue(strategy=GenerationType.IDENTITY)           //gera id automaticamente
    private Long id;

    @Column(name = "PRONTUARIO", nullable = false)               // referencia a coluna prontuário do banco não permitindo ser nulo
    private String prontuario;

    @Column(name = "NOME")                                       // define a coluna nome do banco
    private String nome;

    @Column(name = "SENHA", nullable = false)                    // referencia a coluna senha do banco não permitindo ser nulo
    private String senha;

    @Column(name = "NIVEL_ACESSO",nullable = false)              // referencia a coluna nivel_acesso do banco não permitindo ser nulo
    private Integer nivelAcesso;
}

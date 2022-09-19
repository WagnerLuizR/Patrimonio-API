package bri.ifsp.edu.br.patrimonioapi.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBLISTA")
@SequenceGenerator(name = "SEQ_TB_LISTA", sequenceName = "SEQ_TB_LISTA", allocationSize = 1)
public class Usuario {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TB_LISTA")
    private Long id;

    @Column(name = "PRONTUARIO")
    private Long prontuario;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "NIVEL_ACESSO")
    private Integer nivelAcesso;
}

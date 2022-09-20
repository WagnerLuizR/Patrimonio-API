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
@Table(name = "TB_LISTA")
public class Usuario {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRONTUARIO", nullable = false)
    private String prontuario;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "SENHA", nullable = false)
    private String senha;

    @Column(name = "NIVEL_ACESSO",nullable = false)
    private Integer nivelAcesso;
}

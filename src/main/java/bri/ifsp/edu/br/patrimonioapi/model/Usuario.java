package bri.ifsp.edu.br.patrimonioapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_USUARIO") // refrência da tabela do banco dessa entidade
public class Usuario implements Serializable {

    @Id                                                         // define variável como id
    @Column(name = "ID")                                        // referencia a coluna do banco id
    @GeneratedValue(strategy = GenerationType.IDENTITY)           //gera id automaticamente
    private Integer id;

    @Column(name = "PRONTUARIO")               // referencia a coluna prontuário do banco não permitindo ser nulo
    private String prontuario;

    @Column(name = "NOME")                                       // define a coluna nome do banco
    private String nome;

    @Column(name = "SENHA")                    // referencia a coluna senha do banco não permitindo ser nulo
    private String senha;

    @Column(name = "NIVEL_ACESSO")              // referencia a coluna nivel_acesso do banco não permitindo ser nulo
    private Integer nivelAcesso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(Integer nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public Usuario() {
    }

    public Usuario(Integer id, String prontuario, String nome, String senha, Integer nivelAcesso) {
        this.id = id;
        this.prontuario = prontuario;
        this.nome = nome;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(prontuario, usuario.prontuario) && Objects.equals(nome, usuario.nome) && Objects.equals(senha, usuario.senha) && Objects.equals(nivelAcesso, usuario.nivelAcesso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prontuario, nome, senha, nivelAcesso);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", prontuario='" + prontuario + '\'' +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", nivelAcesso=" + nivelAcesso +
                '}';
    }
}

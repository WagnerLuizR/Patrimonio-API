package bri.ifsp.edu.br.patrimonioapi.service;

import bri.ifsp.edu.br.patrimonioapi.model.Usuario;
import bri.ifsp.edu.br.patrimonioapi.persistencia.ConexaoBancoDados;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

public class UsuarioService {
    @PersistenceContext(unitName = "patrimonioapi")
    private final EntityManager entityManager;

    public UsuarioService() {
        System.out.println("Excutando o construtor da classe");
        entityManager = ConexaoBancoDados.getConexao().getEntityManager();
    }

    public void showConexaoBanco() {
        System.out.println("Executando showConexaoBanco >>>>");
        EntityTransaction transacao = entityManager.getTransaction();
        if (entityManager != null) {
            System.out.println("banco aberto");
        }
        if (transacao != null) {
            System.out.println("Conectado com o banco de dados");
        }
    }

    public void salvarUsuario(Usuario usuario) {
        System.out.println(usuario.toString());
    }
}

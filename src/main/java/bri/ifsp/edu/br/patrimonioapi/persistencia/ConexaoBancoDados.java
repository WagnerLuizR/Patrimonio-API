package bri.ifsp.edu.br.patrimonioapi.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class ConexaoBancoDados {

    private static ConexaoBancoDados CONEXAO;
    private static EntityManagerFactory FACTORY;

    private ConexaoBancoDados() {
        if (FACTORY == null) {
            FACTORY = getFactory();
        }

    }

    public static ConexaoBancoDados getConexao() {

        if (CONEXAO == null) {
            CONEXAO = new ConexaoBancoDados();
        }
        return CONEXAO;

    }

    public EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    private EntityManagerFactory getFactory() {

        Map<String, String> propriedades = new HashMap<String, String>();

        propriedades.put("javax.persistence.schema-generation.database.action", "update");
        propriedades.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        propriedades.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/appvendas");
        propriedades.put("hibernate.connection.username", "root");
        propriedades.put("hibernate.connection.password", "");
        propriedades.put("hibernate.c3p0.min_size", "10");
        propriedades.put("hibernate.c3p0.max_size", "20");
        propriedades.put("hibernate.c3p0.acquire_increment", "1");
        propriedades.put("hibernate.c3p0.idle_text_period", "3000");
        propriedades.put("hibernate.c3p0.max_statements", "50");
        propriedades.put("hibernate.c3p0.timeout", "1800");
        propriedades.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        propriedades.put("hibernate.show_sql", "true");
        propriedades.put("hibernate.format_sql", "true");
        propriedades.put("useUnicode", "true");
        propriedades.put("characterEncoding", "UTF-8");
        propriedades.put("hibernate.default_schema", "appvendas");
        return Persistence.createEntityManagerFactory("appvendas", propriedades);
    }
}

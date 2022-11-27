package bri.ifsp.edu.br.patrimonioapi.DAO;

import bri.ifsp.edu.br.patrimonioapi.config.Page;
import bri.ifsp.edu.br.patrimonioapi.model.Lista;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class ListaDAO extends GenericDAO<Lista, Long> {

    public ListaDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public Page<Lista> listaPaginada(Integer page, Integer pageSize, String text) {

        List<Lista> lista = new ArrayList<Lista>();

        Long total = count();

        Integer paginaAtual = ((page - 1) * pageSize);

        if (paginaAtual < 0) {
            paginaAtual = 0;
        }

        Double totalPaginas = Math.ceil(total.doubleValue() / pageSize.doubleValue());

        TypedQuery<Lista> query = getEntityManager()
                .createQuery(
                        " SELECT c FROM Lista c " +
                                " left join Usuario u " +
                                " left join Patrimonio p " +
                                " WHERE c.area LIKE(CONCAT('%',:text,'%')) " +
                                " OR u.nome LIKE(CONCAT('%',:text,'%')) " +
                                " OR p.descPatrimonio LIKE(CONCAT('%',:text,'%')) ", Lista.class);

        lista = query.setParameter("text", text)
                .setFirstResult(paginaAtual)
                .setMaxResults(pageSize)
                .getResultList();
        return getPaginas(lista, page, pageSize, totalPaginas.intValue(), total.intValue());
    }
}

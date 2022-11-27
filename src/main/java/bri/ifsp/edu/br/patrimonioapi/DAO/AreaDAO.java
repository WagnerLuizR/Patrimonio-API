package bri.ifsp.edu.br.patrimonioapi.DAO;

import bri.ifsp.edu.br.patrimonioapi.config.Page;
import bri.ifsp.edu.br.patrimonioapi.model.Area;
import bri.ifsp.edu.br.patrimonioapi.model.Patrimonio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class AreaDAO extends GenericDAO<Area, Long> {

    public AreaDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public Page<Area> listaPaginada(Integer page, Integer pageSize, String text) {

        List<Area> lista = new ArrayList<Area>();

        Long total = count();

        Integer paginaAtual = ((page - 1) * pageSize);

        if (paginaAtual < 0) {
            paginaAtual = 0;
        }

        Double totalPaginas = Math.ceil(total.doubleValue() / pageSize.doubleValue());

        TypedQuery<Area> query = getEntityManager()
                .createQuery(
                        " SELECT c FROM Area c " +
                                " WHERE c.nomeArea LIKE(CONCAT('%',:text,'%')) " +
                                " OR c.tipoArea LIKE(CONCAT('%',:text,'%')) ", Area.class);

        lista = query.setParameter("text", text)
                .setFirstResult(paginaAtual)
                .setMaxResults(pageSize)
                .getResultList();


        return getPaginas(lista, page, pageSize, totalPaginas.intValue(), total.intValue());
    }
    public List<Area> listarArea() {
        TypedQuery<Area> query = getEntityManager()
                .createQuery("SELECT a FROM Area a", getClasse());
        return query.getResultList();
    }
}

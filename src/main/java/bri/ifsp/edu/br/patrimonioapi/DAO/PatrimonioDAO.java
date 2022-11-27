package bri.ifsp.edu.br.patrimonioapi.DAO;

import bri.ifsp.edu.br.patrimonioapi.config.Page;
import bri.ifsp.edu.br.patrimonioapi.model.Patrimonio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class PatrimonioDAO extends GenericDAO<Patrimonio, Long> {

    public PatrimonioDAO(EntityManager entityManager) {
        super(entityManager);
    }

    public Page<Patrimonio> listaPaginada(Integer page, Integer pageSize, String text) {

        List<Patrimonio> lista = new ArrayList<Patrimonio>();

        Long total = count();

        Integer paginaAtual = ((page - 1) * pageSize);

        if (paginaAtual < 0) {
            paginaAtual = 0;
        }

        Double totalPaginas = Math.ceil(total.doubleValue() / pageSize.doubleValue());

        TypedQuery<Patrimonio> query = getEntityManager()
                .createQuery(
                        " SELECT c FROM Patrimonio c " +
                                " WHERE concat(c.codigo,'') LIKE(CONCAT('%',:text,'%')) " +
                                " OR c.descPatrimonio LIKE(CONCAT('%',:text,'%')) " +
                                " OR concat(c.estado,'') LIKE(CONCAT('%',:text,'%')) ", Patrimonio.class);

        lista = query.setParameter("text", text)
                .setFirstResult(paginaAtual)
                .setMaxResults(pageSize)
                .getResultList();


        return getPaginas(lista, page, pageSize, totalPaginas.intValue(), total.intValue());
    }
    
    public List<Patrimonio> listaPatrimonio(){
    	return listar();
    }
}

package bri.ifsp.edu.br.patrimonioapi.DAO;

import bri.ifsp.edu.br.patrimonioapi.DTO.PatrimonioDTO;
import bri.ifsp.edu.br.patrimonioapi.config.Page;
import bri.ifsp.edu.br.patrimonioapi.model.Patrimonio;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Page<PatrimonioDTO> patrimonioPaginadoDTO(Integer page, Integer pageSize, String text) {
        List<PatrimonioDTO> lista = new ArrayList<PatrimonioDTO>();
        Page<PatrimonioDTO> pagina = new Page<PatrimonioDTO>();

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
                .getResultList().stream().map(PatrimonioDTO::new).collect(Collectors.toList());

        pagina.setContent(lista);
        pagina.setPage(page);
        pagina.setPageSize(pageSize);
        pagina.setTotalPage(totalPaginas.intValue());
        pagina.setTotalRecords(total.intValue());
        return pagina;
    }

    public Page<PatrimonioDTO> patrimonioPaginadoDTO(Integer page, Integer pageSize) {
        List<bri.ifsp.edu.br.patrimonioapi.DTO.PatrimonioDTO> lista = new ArrayList<bri.ifsp.edu.br.patrimonioapi.DTO.PatrimonioDTO>();
        Page<bri.ifsp.edu.br.patrimonioapi.DTO.PatrimonioDTO> pagina = new Page<bri.ifsp.edu.br.patrimonioapi.DTO.PatrimonioDTO>();

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

        lista = query.setFirstResult(paginaAtual)
                .setMaxResults(pageSize)
                .getResultList().stream().map(bri.ifsp.edu.br.patrimonioapi.DTO.PatrimonioDTO::new).collect(Collectors.toList());

        pagina.setContent(lista);
        pagina.setPage(page);
        pagina.setPageSize(pageSize);
        pagina.setTotalPage(totalPaginas.intValue());
        pagina.setTotalRecords(total.intValue());
        return pagina;
    }

    public List<Patrimonio> listaPatrimonio() {
        TypedQuery<Patrimonio> query = getEntityManager()
                .createQuery("SELECT p FROM Patrimonio p", getClasse());
        return query.getResultList();
    }
}

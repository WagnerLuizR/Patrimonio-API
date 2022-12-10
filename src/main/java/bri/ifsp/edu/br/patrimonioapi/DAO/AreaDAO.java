package bri.ifsp.edu.br.patrimonioapi.DAO;

import bri.ifsp.edu.br.patrimonioapi.DTO.AreaDTO;
import bri.ifsp.edu.br.patrimonioapi.config.Page;
import bri.ifsp.edu.br.patrimonioapi.model.Area;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Page<AreaDTO> paginadoDTO(Integer page, Integer pageSize, String text) {
        List<AreaDTO> lista = new ArrayList<AreaDTO>();
        Page<AreaDTO> pagina = new Page<AreaDTO>();

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
                .getResultList().stream().map(AreaDTO::new).collect(Collectors.toList());

        pagina.setContent(lista);
        pagina.setPage(page);
        pagina.setPageSize(pageSize);
        pagina.setTotalPage(totalPaginas.intValue());
        pagina.setTotalRecords(total.intValue());
        return pagina;
    }

    public Page<AreaDTO> paginadoDTO(Integer page, Integer pageSize) {
        List<bri.ifsp.edu.br.patrimonioapi.DTO.AreaDTO> lista = new ArrayList<bri.ifsp.edu.br.patrimonioapi.DTO.AreaDTO>();
        Page<bri.ifsp.edu.br.patrimonioapi.DTO.AreaDTO> pagina = new Page<bri.ifsp.edu.br.patrimonioapi.DTO.AreaDTO>();

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

        lista = query.setFirstResult(paginaAtual)
                .setMaxResults(pageSize)
                .getResultList().stream().map(bri.ifsp.edu.br.patrimonioapi.DTO.AreaDTO::new).collect(Collectors.toList());

        pagina.setContent(lista);
        pagina.setPage(page);
        pagina.setPageSize(pageSize);
        pagina.setTotalPage(totalPaginas.intValue());
        pagina.setTotalRecords(total.intValue());
        return pagina;
    }

    public List<Area> listarArea() {
        TypedQuery<Area> query = getEntityManager()
                .createQuery("SELECT a FROM Area a", getClasse());
        return query.getResultList();
    }
}

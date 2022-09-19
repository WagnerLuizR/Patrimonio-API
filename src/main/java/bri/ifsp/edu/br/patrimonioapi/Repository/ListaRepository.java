package bri.ifsp.edu.br.patrimonioapi.Repository;

import bri.ifsp.edu.br.patrimonioapi.Model.Lista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaRepository extends JpaRepository<Lista, Long> {

    @Query(value = """
            select l from Lista l
            left join l.Area a
            left join l.Usuario u
            left join l.Patrimonio p
            where (:search is null) 
            or concat(l.id,'') like concat('%',search,'%')
            or lower(a.nomeArea) like lower(concat('%',:search,'%')) 
            or lower(u.nome) like lower(concat('%',:search,'%'))
            or lower(p.codigo) like lower(concat('%',:search,'%'))
            """)
    Page<Lista> findAllPaginatedWithSearch(@Param("search") String search, Pageable pageable);

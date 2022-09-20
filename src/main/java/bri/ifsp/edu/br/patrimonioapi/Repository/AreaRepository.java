package bri.ifsp.edu.br.patrimonioapi.Repository;

import bri.ifsp.edu.br.patrimonioapi.Model.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository // define essa interace como um reposiotório
public interface AreaRepository extends JpaRepository<Area, Long> { // utiliza JPA para gedir este repositório

    //@query permite que seja desenvolvido querys em JPQL e nativeQuery podendo ser referênciado por um metodo
    @Query(value = """                                      
            select a from Area a
            where (:search is null)
            or concat(a.id,'') like lower(concat('%',:search,'%'))
            or concat(a.tipoArea,'') like lower(concat('%',:search,'%'))
            or lower(a.nomeArea) like lower(concat('%',:search,'%'))
            """)
    Page<Area> findAllPaginatedWithSearch(Pageable pageable, @Param("search") String search);
}

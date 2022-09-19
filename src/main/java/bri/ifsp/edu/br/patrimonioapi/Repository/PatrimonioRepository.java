package bri.ifsp.edu.br.patrimonioapi.Repository;

import bri.ifsp.edu.br.patrimonioapi.Model.Patrimonio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio,Long> {

    @Query(value = """
            select u from Patrimonio u
            left join u.area a
            where (:search) is null or concat(u.codigo,'') like lower(concat('%',:search,'%'))
            or concat(u.estado,'') like lower(concat('%',:search,'%'))
            or lower(a.nomeArea) like lower(concat('%',:search,'%'))
            """)
    Page<Patrimonio> findAllPaginatedWithSearch(Pageable pageable,
                                                @Param("search") String search);
}

package bri.ifsp.edu.br.patrimonioapi.Repository;

import bri.ifsp.edu.br.patrimonioapi.Model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = """
            select u from Usuario u
            where (:search is null)
            or concat(u.id,'') like lower(concat('%',:search,'%'))
            or concat(u.prontuario,'') like lower(concat('%',:search,'%'))
            or lower(u.nome) like lower(concat('%',:search,'%'))
            """)
    Page<Usuario> findAllPaginatedWithSearch(Pageable pageable, @Param("search") String search);
}

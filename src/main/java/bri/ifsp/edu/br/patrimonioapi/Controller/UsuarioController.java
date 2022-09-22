package bri.ifsp.edu.br.patrimonioapi.Controller;

import bri.ifsp.edu.br.patrimonioapi.DTO.UsuarioDTO;
import bri.ifsp.edu.br.patrimonioapi.Model.Usuario;
import bri.ifsp.edu.br.patrimonioapi.Repository.UsuarioRepository;
import javassist.NotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private static final String NOT_FOUND = "Usuário não encontrado.";
    private final UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<Page<Usuario>> getUsuario(@RequestParam(required = false) String search,
                                                    @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(usuarioRepository.findAllPaginatedWithSearch(pageable, search));
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable(value = "id") Integer id){
        return ResponseEntity.ok(usuarioRepository.findByIntId(id));
    }

    @PostMapping("/save")
    public ResponseEntity<Usuario> saveUsuario(@RequestBody UsuarioDTO UsuarioDTO) throws IOException {
        try {
            return ResponseEntity.ok(usuarioRepository.saveAndFlush(UsuarioDTO.toUsuario()));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IOException(ex);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> updateUsuario(@Param("id") Integer id,
                                                 @RequestBody UsuarioDTO usuarioDTO) throws NotFoundException {
        getUsuarioById(id);
        return ResponseEntity.ok(usuarioRepository.save(usuarioDTO.toUsuario()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@Param("id") Integer id) throws NotFoundException {
        getUsuarioById(id);
        usuarioRepository.deleteById(id.longValue());
        return new ResponseEntity<>("Usuário deletado com sucesso!", HttpStatus.OK);
    }
}

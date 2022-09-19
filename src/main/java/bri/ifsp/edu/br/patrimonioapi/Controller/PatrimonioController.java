package bri.ifsp.edu.br.patrimonioapi.Controller;

import bri.ifsp.edu.br.patrimonioapi.DTO.PatrimonioDTO;
import bri.ifsp.edu.br.patrimonioapi.Model.Patrimonio;
import bri.ifsp.edu.br.patrimonioapi.Repository.PatrimonioRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patrimonio")
@RequiredArgsConstructor
public class PatrimonioController {

    private static final String NOT_FOUND = "Patrimonio não encontrada.";
    private final PatrimonioRepository patrimonioRepository;

    @GetMapping
    public ResponseEntity<Page<Patrimonio>> getPatrimonio(@RequestParam(required = false) String search,
                                              @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(patrimonioRepository.findAllPaginatedWithSearch(pageable, search));
    }

    @GetMapping("{id}")
    public ResponseEntity<Patrimonio> getPatrimonioById(@RequestParam("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(patrimonioRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity<Patrimonio> savePatrimonio(@RequestBody PatrimonioDTO patrimonioDTO) {
        return ResponseEntity.ok(patrimonioRepository.save(patrimonioDTO.toPatrimonio()));
    }

    @PutMapping("{id}")
    public ResponseEntity<Patrimonio> updatePatrimonio(@Param("id") Long id,
                                           @RequestBody PatrimonioDTO patrimonioDTO) throws NotFoundException {
        getPatrimonioById(id);
        return ResponseEntity.ok(patrimonioRepository.save(patrimonioDTO.toPatrimonio()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePatrimonio(@Param("id") Long id) throws NotFoundException {
        getPatrimonioById(id);
        patrimonioRepository.deleteById(id);
        return new ResponseEntity<>("Usuário deletado com sucesso!", HttpStatus.OK);
    }
}

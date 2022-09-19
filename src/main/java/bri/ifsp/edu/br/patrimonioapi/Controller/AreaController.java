package bri.ifsp.edu.br.patrimonioapi.Controller;

import bri.ifsp.edu.br.patrimonioapi.DTO.AreaDTO;
import bri.ifsp.edu.br.patrimonioapi.Model.Area;
import bri.ifsp.edu.br.patrimonioapi.Repository.AreaRepository;
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
@RequiredArgsConstructor
@RequestMapping("/area")
public class AreaController {
    
    private static final String NOT_FOUND = "Área não encontrada.";
    private final AreaRepository areaRepository;

    @GetMapping
    public ResponseEntity<Page<Area>> getArea(@RequestParam(required = false) String search,
                                                 @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(areaRepository.findAllPaginatedWithSearch(pageable, search));
    }

    @GetMapping("{id}")
    public ResponseEntity<Area> getAreaById(@RequestParam("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(areaRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND)));
    }

    @PostMapping
    public ResponseEntity<Area> saveArea(@RequestBody AreaDTO areaDTO) {
        return ResponseEntity.ok(areaRepository.save(areaDTO.toArea()));
    }

    @PutMapping("{id}")
    public ResponseEntity<Area> updateArea(@Param("id") Long id,
                                                 @RequestBody AreaDTO areaDTO) throws NotFoundException {
        getAreaById(id);
        return ResponseEntity.ok(areaRepository.save(areaDTO.toArea()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteArea(@Param("id") Long id) throws NotFoundException {
        getAreaById(id);
        areaRepository.deleteById(id);
        return new ResponseEntity<>("Usuário deletado com sucesso!", HttpStatus.OK);
    }
}

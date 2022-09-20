package bri.ifsp.edu.br.patrimonioapi.Controller;

import bri.ifsp.edu.br.patrimonioapi.Model.Lista;
import bri.ifsp.edu.br.patrimonioapi.Repository.ListaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lista")
@RequiredArgsConstructor
public class ListaController {

    private final ListaRepository listaRepository;

    @GetMapping
    public ResponseEntity<Page<Lista>> getLista(@RequestParam(required = false) String search,
                                               @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(listaRepository.findAllPaginatedWithSearch(search, pageable));
    }
}

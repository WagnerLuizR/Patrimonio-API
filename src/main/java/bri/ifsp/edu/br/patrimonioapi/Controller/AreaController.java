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

@RestController             //sinaliza essa classe como um controtller
@RequiredArgsConstructor    //permite que crie injeção de dependência apenas utilizando FINAL na instância
@RequestMapping("/area")
public class AreaController {
    
    private static final String NOT_FOUND = "Área não encontrada.";
    private final AreaRepository areaRepository;


    //@GetMapping permite executar uma busca
    @GetMapping //responseEntity cria a requisição HTTP precisando apenas receber um retorno que atenda os tipos que nela foi setadot
    //neste caso vai retornar uma página do tipo Área, já com as informações de paginação necessárias
    public ResponseEntity<Page<Area>> getArea(@RequestParam(required = false) String search, //permite que possa ser usado esse path na request ou não
                                                                                             //neste caso está sendo utilizado para buscar pela cofig do repository
                                                 @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(areaRepository.findAllPaginatedWithSearch(pageable, search));
    }

    //@GetMapping permite executar uma busca, nestew caso personalizando a busca por um ID
    @GetMapping("{id}")
    public ResponseEntity<Area> getAreaById(@RequestParam("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(areaRepository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND))); // retorna uma excessão antes em chain,
                                                                                                                // portanto não precisa finalizar a sessão para gerar a excessão
    }
    //@PostMapping permite persistir na tabela
    @PostMapping
    public ResponseEntity<Area> saveArea(@RequestBody AreaDTO areaDTO) {
        return ResponseEntity.ok(areaRepository.save(areaDTO.toArea()));
    }

    //@PutMapping permite persistir em uma tabela para alterar um registro
    //neste caso está sendo usado o ID para encontrar quem deve ser alterado
    @PutMapping("{id}")
    public ResponseEntity<Area> updateArea(@Param("id") Long id,
                                                 @RequestBody AreaDTO areaDTO) throws NotFoundException {
        getAreaById(id);
        return ResponseEntity.ok(areaRepository.save(areaDTO.toArea()));
    }
    //@DeleteMapping permite deletar, neste caso por ID
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteArea(@Param("id") Long id) throws NotFoundException {
        getAreaById(id);
        areaRepository.deleteById(id);
        return new ResponseEntity<>("Usuário deletado com sucesso!", HttpStatus.OK);
    }
}

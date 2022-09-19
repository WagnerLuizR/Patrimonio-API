package bri.ifsp.edu.br.patrimonioapi.DTO;

import bri.ifsp.edu.br.patrimonioapi.Model.Area;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
public class AreaDTO {
    private Long id;
    private String nomeArea;
    private Integer tipoArea;

    public AreaDTO(Area area) {
        new ModelMapper().map(area, this);
    }

    public Area toArea() {
        return new ModelMapper().map(this, Area.class);
    }
}

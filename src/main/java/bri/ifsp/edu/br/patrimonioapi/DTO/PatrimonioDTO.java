package bri.ifsp.edu.br.patrimonioapi.DTO;

import bri.ifsp.edu.br.patrimonioapi.Model.Area;
import bri.ifsp.edu.br.patrimonioapi.Model.Patrimonio;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
public class PatrimonioDTO {

    private Long codigo;
    private Integer estado;
    private Area area;

    public PatrimonioDTO(Patrimonio patrimonio) {
        new ModelMapper().map(patrimonio, this);
    }

    public Patrimonio toPatrimonio() {
        return new ModelMapper().map(this, Patrimonio.class);
    }
}

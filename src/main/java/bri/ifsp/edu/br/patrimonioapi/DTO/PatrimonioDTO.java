package bri.ifsp.edu.br.patrimonioapi.DTO;

import bri.ifsp.edu.br.patrimonioapi.model.Area;
import bri.ifsp.edu.br.patrimonioapi.model.Patrimonio;
import org.modelmapper.ModelMapper;

public class PatrimonioDTO {

    private Long codigo;
    private String descPatrimonio;
    private String identificador;
    private String estado;
    private Area area;

    public PatrimonioDTO() {

    }


    public PatrimonioDTO(Patrimonio patrimonio) {
        this.codigo = patrimonio.getCodigo();
        switch (patrimonio.getEstado()) {
            case 1:
                this.estado = "NOVO";
                break;
            case 2:
                this.estado = "USADO";
                break;
            case 3:
                this.estado = "DANIFICADO";
                break;
            default:
                break;
        }
        this.descPatrimonio = patrimonio.getDescPatrimonio();
        this.identificador = patrimonio.getIdentificador();
        this.area = patrimonio.getArea();
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescPatrimonio() {
        return descPatrimonio;
    }

    public void setDescPatrimonio(String descPatrimonio) {
        this.descPatrimonio = descPatrimonio;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}

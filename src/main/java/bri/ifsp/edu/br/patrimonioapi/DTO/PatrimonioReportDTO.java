package bri.ifsp.edu.br.patrimonioapi.DTO;

import bri.ifsp.edu.br.patrimonioapi.model.Patrimonio;
import org.modelmapper.ModelMapper;

public class PatrimonioReportDTO {
    private Long codigo;
    private String descPatrimonio;
    private String identificador;
    private Integer estado;
    private String area;

    public PatrimonioReportDTO(Patrimonio patrimonio) {
        this.codigo = patrimonio.getCodigo();
        this.descPatrimonio = patrimonio.getDescPatrimonio();
        this.identificador = patrimonio.getIdentificador();
        this.estado = patrimonio.getEstado();
        this.area = patrimonio.getArea().getNomeArea();
    }

    public Patrimonio toEntity() {
        return new ModelMapper().map(this, Patrimonio.class);
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

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
}

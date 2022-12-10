package bri.ifsp.edu.br.patrimonioapi.DTO;

import bri.ifsp.edu.br.patrimonioapi.model.Area;

public class AreaDTO {
    private Long id;
    private String nomeArea;
    private String tipoArea;

    public AreaDTO() {
    }

    public AreaDTO(Area area) {
        this.id = area.getId();
        this.nomeArea = area.getNomeArea();
        switch (area.getTipoArea()) {
            case 1:
                this.tipoArea = "EXTERNA";
                break;
            case 2:
                this.tipoArea = "SALA DE AULA";
                break;
            case 3:
                this.tipoArea = "LABORATÓRIO";
                break;
            case 4:
                this.tipoArea = "ADMINISTRATIVO";
                break;
            default:
                break;
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArea() {
        return nomeArea;
    }

    public void setNomeArea(String nomeArea) {
        this.nomeArea = nomeArea;
    }

    public String getTipoArea() {
        return tipoArea;
    }

    public void setTipoArea(String tipoArea) {
        this.tipoArea = tipoArea;
    }
}

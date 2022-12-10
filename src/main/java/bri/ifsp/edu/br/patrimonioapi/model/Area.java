package bri.ifsp.edu.br.patrimonioapi.model;

import bri.ifsp.edu.br.patrimonioapi.DTO.AreaDTO;
import bri.ifsp.edu.br.patrimonioapi.service.errors.CampoRequerido;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TB_AREA")
public class Area implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CampoRequerido(valor = 1, mensagem = "O nome da Área deve ser informado")
    @Column(name = "NOME_AREA")
    private String nomeArea;

    @CampoRequerido(valor = 2, mensagem = "O tipo de Área deve ser informado")
    @Column(name = "TIPO_AREA")
    private Integer tipoArea;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "area")
    private Set<Patrimonio> patrimonios;

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

    public Integer getTipoArea() {
        return tipoArea;
    }

    public void setTipoArea(Integer tipoArea) {
        this.tipoArea = tipoArea;
    }

    @JsonIgnore
    public Set<Patrimonio> getPatrimonios() {
        return patrimonios;
    }

    @JsonIgnore
    public void setPatrimonios(Set<Patrimonio> patrimonios) {
        this.patrimonios = patrimonios;
    }

    public Area() {
    }

    public Area(AreaDTO areaDTO) {
        this.id = areaDTO.getId();
        this.nomeArea = areaDTO.getNomeArea();
        if (areaDTO.getTipoArea().equalsIgnoreCase("EXTERNA")) this.tipoArea = 1;
        else if (areaDTO.getTipoArea().equalsIgnoreCase("SALA DE AULA")) this.tipoArea = 2;
        else if (areaDTO.getTipoArea().equalsIgnoreCase("LABORATÓRIO")) this.tipoArea = 3;
        else if (areaDTO.getTipoArea().equalsIgnoreCase("ADMINISTRATIVO")) this.tipoArea = 4;
    }

    public Area(Long id, String nomeArea, Integer tipoArea) {
        this.id = id;
        this.nomeArea = nomeArea;
        this.tipoArea = tipoArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Area area = (Area) o;
        return Objects.equals(id, area.id) && Objects.equals(nomeArea, area.nomeArea) && Objects.equals(tipoArea, area.tipoArea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeArea, tipoArea);
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", nomeArea='" + nomeArea + '\'' +
                ", tipoArea=" + tipoArea +
                '}';
    }
}

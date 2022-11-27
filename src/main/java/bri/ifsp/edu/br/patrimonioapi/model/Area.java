package bri.ifsp.edu.br.patrimonioapi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_AREA")
public class Area implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME_AREA")
    private String nomeArea;

    @Column(name = "TIPO_AREA")
    private Integer tipoArea;

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

    public Area() {
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

package bri.ifsp.edu.br.patrimonioapi.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TB_LISTA")
public class Lista {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ID_AREA")
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ID_PATRIMONIO")
    private Patrimonio patrimonio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Patrimonio getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(Patrimonio patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Lista() {
    }

    public Lista(Long id, Area area, Patrimonio patrimonio) {
        this.id = id;
        this.area = area;
        this.patrimonio = patrimonio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lista lista = (Lista) o;
        return Objects.equals(id, lista.id) && Objects.equals(area, lista.area) && Objects.equals(patrimonio, lista.patrimonio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, area, patrimonio);
    }

    @Override
    public String toString() {
        return "Lista{" +
                "id=" + id +
                ", area=" + area +
                ", patrimonio=" + patrimonio +
                '}';
    }
}

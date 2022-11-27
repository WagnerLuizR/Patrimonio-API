package bri.ifsp.edu.br.patrimonioapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TB_PATRIMONIO")
public class Patrimonio implements Serializable {

    @Id
    @Column(name = "CODIGO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "desc_patrimonio")
    private String descPatrimonio;

    @Column(name = "ESTADO")
    private Integer estado;

    @ManyToOne(fetch = FetchType.LAZY)
    //referência do relacionamento de muitos para um com fetchType sendo Lazy
    @JoinColumn(name = "ID_AREA")
    //sabendo a coluna de referência relacionma apenas o id da entidade para unificar
    // na coluna da tabela que vai armazenar apenas o id
    private Area area;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patrimonio")  //referência do relacionamento de um para muitos que
    // está sendo referenciado na entidade lista
    @JsonIgnore
    // permite que o jackson ignore essa entidade no json de qualquer requisição
    private Set<Lista> lista;

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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Set<Lista> getLista() {
        return lista;
    }

    public void setLista(Set<Lista> lista) {
        this.lista = lista;
    }

    public Patrimonio() {
    }

    public Patrimonio(Long codigo, String descPatrimonio, Integer estado, Area area, Set<Lista> lista) {
        this.codigo = codigo;
        this.descPatrimonio = descPatrimonio;
        this.estado = estado;
        this.area = area;
        this.lista = lista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patrimonio that = (Patrimonio) o;
        return Objects.equals(codigo, that.codigo) && Objects.equals(descPatrimonio, that.descPatrimonio) && Objects.equals(estado, that.estado) && Objects.equals(area, that.area) && Objects.equals(lista, that.lista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, descPatrimonio, estado, area, lista);
    }

    @Override
    public String   toString() {
        return "Patrimonio{" +
                "codigo=" + codigo +
                ", descPatrimonio='" + descPatrimonio + '\'' +
                ", estado=" + estado +
                ", area=" + area +
                ", lista=" + lista +
                '}';
    }
}

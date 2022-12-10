package bri.ifsp.edu.br.patrimonioapi.model;

import bri.ifsp.edu.br.patrimonioapi.DTO.PatrimonioDTO;
import bri.ifsp.edu.br.patrimonioapi.service.errors.CampoRequerido;
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

    @CampoRequerido(valor=1, mensagem = "A descrição do Patrimonio deve ser informado")
    @Column(name = "desc_patrimonio")
    private String descPatrimonio;

    @CampoRequerido(valor=1, mensagem = "A estado do Patrimonio deve ser informado")
    @Column(name = "ESTADO")
    private Integer estado;

    @Column(name = "IDENTIFICADOR")
    private String identificador;


    @Column(name = "ID_AREA")
    private Long idArea;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Area.class)
    @MapsId("ID_AREA")
    @JoinColumn(name = "ID_AREA", insertable = false, updatable = false)
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

    @JsonIgnore
    public Area getArea() {
        return area;
    }

    @JsonIgnore
    public void setArea(Area area) {
        this.area = area;
    }


    public Long getIdArea() {
        return idArea;
    }

    public void setIdArea(Long idArea) {
        this.idArea = idArea;
    }

    @JsonIgnore
    public Set<Lista> getLista() {
        return lista;
    }

    @JsonIgnore
    public void setLista(Set<Lista> lista) {
        this.lista = lista;
    }

    public Patrimonio() {
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Patrimonio(PatrimonioDTO patrimonioDTO){
        this.codigo = patrimonioDTO.getCodigo();
        this.descPatrimonio = patrimonioDTO.getDescPatrimonio();
        this.identificador = patrimonioDTO.getIdentificador();
        if (patrimonioDTO.getEstado().equalsIgnoreCase("NOVO")) this.estado = 1;
        else if (patrimonioDTO.getEstado().equalsIgnoreCase("USADO")) this.estado = 2;
        else if (patrimonioDTO.getEstado().equalsIgnoreCase("DANIFICADO")) this.estado = 3;
        this.area = patrimonioDTO.getArea();
    }

    public Patrimonio(Long codigo, String descPatrimonio, Integer estado, String identificador, Long idArea, Area area, Set<Lista> lista) {
        this.codigo = codigo;
        this.descPatrimonio = descPatrimonio;
        this.estado = estado;
        this.identificador = identificador;
        this.idArea = idArea;
        this.area = area;
        this.lista = lista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patrimonio that = (Patrimonio) o;
        return codigo.equals(that.codigo) && descPatrimonio.equals(that.descPatrimonio) && estado.equals(that.estado) && identificador.equals(that.identificador) && idArea.equals(that.idArea) && area.equals(that.area) && lista.equals(that.lista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, descPatrimonio, estado, identificador, idArea, area, lista);
    }


    @Override
    public String toString() {
        return "Patrimonio{" +
                "codigo=" + codigo +
                ", descPatrimonio='" + descPatrimonio + '\'' +
                ", estado=" + estado +
                ", identificador='" + identificador + '\'' +
                ", idArea=" + idArea +
                ", area=" + area +
                ", lista=" + lista +
                '}';
    }
}

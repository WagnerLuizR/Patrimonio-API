package bri.ifsp.edu.br.patrimonioapi.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PATRIMONIO")
public class Patrimonio {

    @Id
    @Column(name = "CODIGO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(name = "desc_patrimonio")
    private String descPatrimonio;

    @Column(name = "ESTADO")
    private Integer estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AREA")
    private Area area;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patrimonio")
    @JsonIgnore
    private Set<Lista> lista;


}

package bri.ifsp.edu.br.patrimonioapi.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBPATRIMONIO")
@SequenceGenerator(name = "SEQ_TB_PATRIMONIO", sequenceName = "SEQ_TB_PATRIMONIO", allocationSize = 1)
public class Patrimonio {

    @Id
    @Column(name = "CODIGO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TB_PATRIMONIO")
    private Long codigo;

    @Column(name = "desc_patrimonio")
    private String descPatrimonio;

    @Column(name = "ESTADO")
    private Integer estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_AREA")
    private Area area;

}

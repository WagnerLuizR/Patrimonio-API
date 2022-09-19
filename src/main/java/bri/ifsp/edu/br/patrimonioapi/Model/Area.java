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
@Table(name = "TBAREA")
@SequenceGenerator(name = "SEQ_TB_AREA", sequenceName = "SEQ_TB_AREA", allocationSize = 1)
public class Area {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TB_AREA")
    private Long id;

    @Column(name = "NOME_AREA")
    private String nomeArea;

    @Column(name = "TIPO_AREA")
    private Integer tipoArea;
}

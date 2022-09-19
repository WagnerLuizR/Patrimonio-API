package bri.ifsp.edu.br.patrimonioapi.Model;

import bri.ifsp.edu.br.patrimonioapi.Controller.AbstractAuditEntity;
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
@Table(name = "TBLISTA")
@SequenceGenerator(name = "SEQ_TB_LISTA", sequenceName = "SEQ_TB_LISTA", allocationSize = 1)
public class Lista extends AbstractAuditEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TB_LISTA")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ID_AREA")
    private Area area;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ID_SERVIDOR")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "ID_PATRIMONIO")
    private Patrimonio patrimonio;
}

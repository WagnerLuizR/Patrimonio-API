package bri.ifsp.edu.br.patrimonioapi.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)                          //referência do relacionamento de muitos para um com fetchType sendo Lazy
    @JoinColumn(name = "ID_AREA")                               //sabendo a coluna de referência relacionma apenas o id da entidade para unificar
                                                                // na coluna da tabela que vai armazenar apenas o id
    private Area area;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patrimonio")  //referência do relacionamento de um para muitos que
                                                                // está sendo referenciado na entidade lista
    @JsonIgnore                                                 // permite que o jackson ignore essa entidade no json de qualquer requisição
    private Set<Lista> lista;


}

package bri.ifsp.edu.br.patrimonioapi.Controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)  //torna a entidade um listener que verifica alterações nas tabelas
public abstract class AbstractAuditEntity { // classe geradora de auditoria

    @JsonIgnore
    @Column(name = "CREATED_BY", updatable = false)
    @CreatedBy                      //gera salva nome dos usuários que executam um método de CREATE
    private String createdBy;

    @JsonIgnore
    @Column(name = "UPDATED_BY")
    @LastModifiedBy                 //gera salva nome dos usuários que executam um método de UTPDATE
    private String updatedBy;

    @JsonIgnore
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    @CreationTimestamp              //gera data e hora quando um método de CREATE é acionado
    private Date createdAt;

    @JsonIgnore
    @Column(name = "UPDATED_AT")
    @UpdateTimestamp                //gera data e hora quando um método de UTPDATE é acionado
    private Date updatedAt;
}

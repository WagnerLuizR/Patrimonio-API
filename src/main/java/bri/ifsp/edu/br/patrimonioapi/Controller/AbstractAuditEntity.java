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
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditEntity {

    @JsonIgnore
    @Column(name = "CREATED_BY", updatable = false)
    @CreatedBy
    private String createdBy;

    @JsonIgnore
    @Column(name = "UPDATED_BY")
    @LastModifiedBy
    private String updatedBy;

    @JsonIgnore
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @JsonIgnore
    @Column(name = "UPDATED_AT")
    @UpdateTimestamp
    private Date updatedAt;
}

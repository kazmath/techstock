package br.com.techhub.techstock.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@JsonIgnoreProperties(value = {
    "createdAt", "updatedAt"
}, allowGetters = true)
public class BaseModel implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_create", nullable = false, updatable = false)
    @CreatedDate
    private Date dtCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_update", nullable = false)
    @LastModifiedDate
    private Date dtUpdate;

}

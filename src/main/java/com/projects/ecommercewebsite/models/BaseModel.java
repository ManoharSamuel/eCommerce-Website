package com.projects.ecommercewebsite.models;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @CreatedDate
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;
}

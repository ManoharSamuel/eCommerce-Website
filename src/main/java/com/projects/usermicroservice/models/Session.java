package com.projects.usermicroservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Session extends BaseModel{
    private String token;
    private Date expiringAt;
    @ManyToOne
    private User user;
    private SessionStatus sessionStatus;
}

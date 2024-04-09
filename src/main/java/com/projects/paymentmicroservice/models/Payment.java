package com.projects.paymentmicroservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Payment extends BaseModel{
    private String refId;
    private long amount;
    private Date paymentDate;
}

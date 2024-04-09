package com.projects.paymentmicroservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePaymentLinkRequestDTO {
    private long orderId;
    private long amount;
}

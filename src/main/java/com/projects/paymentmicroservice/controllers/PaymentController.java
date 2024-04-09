package com.projects.paymentmicroservice.controllers;

import com.projects.paymentmicroservice.dtos.CreatePaymentLinkRequestDTO;
import com.projects.paymentmicroservice.services.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;
    
    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PutMapping("/")
    public String createPaymentLink(@RequestBody CreatePaymentLinkRequestDTO createPaymentLinkRequestDTO) throws RazorpayException, StripeException {
        return paymentService.createPaymentLink(createPaymentLinkRequestDTO.getOrderId(), createPaymentLinkRequestDTO.getAmount());
    }
    
    @GetMapping("/{orderId}")
    public String getOrderStatus(@PathVariable long orderId) {
        return paymentService.getOrderStatus(orderId).toString();
    }
    
    @PostMapping("/")
    public void afterPayment() {
        paymentService.afterPayment();
    }
}

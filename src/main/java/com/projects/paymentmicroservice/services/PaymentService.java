package com.projects.paymentmicroservice.services;

import com.projects.paymentmicroservice.models.OrderStatus;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {
    public String createPaymentLink(long orderId, long amount) throws RazorpayException, StripeException;
    public OrderStatus getOrderStatus(long orderId);
    
    public void afterPayment();
}

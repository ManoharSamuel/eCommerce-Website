package com.projects.paymentmicroservice.services;

import com.projects.paymentmicroservice.adapter.PaymentAdapter;
import com.projects.paymentmicroservice.models.OrderStatus;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final PaymentAdapter paymentAdapter;

    public PaymentServiceImpl(@Qualifier("StripePaymentAdapter") PaymentAdapter paymentAdapter) {
        this.paymentAdapter = paymentAdapter;
    }

    @Override
    public String createPaymentLink(long orderId, long amount) throws RazorpayException, StripeException {
        return paymentAdapter.createPaymentLink(orderId, amount);
    }

    @Override
    public OrderStatus getOrderStatus(long orderId) {
        return null;
    }

    @Override
    public void afterPayment() {

    }
}

package com.projects.paymentmicroservice.adapter;

import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentAdapter {
    public String createPaymentLink(long orderId, long amount) throws RazorpayException, StripeException;
}

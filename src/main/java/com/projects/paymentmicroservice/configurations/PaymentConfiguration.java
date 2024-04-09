package com.projects.paymentmicroservice.configurations;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.StripeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfiguration {
    @Value("${razorpay.key_id}")
    private String razorpayKeyId;
    @Value("${razorpay.secret_key}")
    private String razorpaySecretKey;
    @Value("${stripe.api_key}")
    private String stipeSecretKey;
    
    @Bean
    public RazorpayClient getRazorPayClient() throws RazorpayException {
        return new RazorpayClient(razorpayKeyId, razorpaySecretKey);
    }
    
    @Bean
    public StripeClient getStripeClient() {
        return new StripeClient(stipeSecretKey);
    }
}

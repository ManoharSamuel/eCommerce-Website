package com.projects.paymentmicroservice.adapter;

import com.stripe.Stripe;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("StripePaymentAdapter")
public class StripePaymentAdapter implements PaymentAdapter{
    private final StripeClient stripeClient;
    @Value("${stripe.api_key}")
    private String stripeAPIKey;

    @Autowired
    public StripePaymentAdapter(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @Override
    public String createPaymentLink(long orderId, long amount) throws StripeException {
        Stripe.apiKey = stripeAPIKey;
        PriceCreateParams priceParams = 
                PriceCreateParams.builder()
                        .setUnitAmount(amount)
                        .setCurrency("inr")
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("iPhone 14").build()
                        )
                        .build();

        Price price = Price.create(priceParams);

        PaymentLinkCreateParams paymentLinkParams =
        PaymentLinkCreateParams.builder()
                .addLineItem(
                        PaymentLinkCreateParams.LineItem.builder()
                                .setPrice(price.getId())
                                .setQuantity(1L)
                                .build()
                ).setAfterCompletion(
                        PaymentLinkCreateParams.AfterCompletion.builder()
                                .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                .setRedirect(PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                        .setUrl("https://scaler.com")
                                        .build()
                                ).build()
                )
                .build();
        
        return PaymentLink.create(paymentLinkParams).toString();
    }
}

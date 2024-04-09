package com.projects.paymentmicroservice.controllers;

import com.stripe.model.Event;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class WebHookController {
    
    @PostMapping("/stripe")
    public String stripeWebHook(@RequestBody Event event){
        if (event.getType().equals("payment_link_created")) {
            
        } else if(event.getType().equals("payment_status_change")) {
            
        }
        
        System.out.println("Webhook called");
        return "Webhook called";
    }
}

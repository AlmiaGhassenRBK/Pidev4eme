package com.example.pidev.web;

import com.example.pidev.dto.CreatePayment;
import com.example.pidev.dto.CreatePaymentResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @PostMapping("/create-payment-intent")

    public CreatePaymentResponse createPaymentIntent(@RequestBody CreatePayment createPayment) throws StripeException {

        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("usd")
                .setAmount(15 * 100l)
                .build();
 // Create a paymentintent with the order amount and currency

        PaymentIntent intent = PaymentIntent.create(createParams);

        return new CreatePaymentResponse(intent.getClientSecret());



    }

}

package edu.cmu.bigbowl.Controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.Stripe;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;


@RestController
@RequestMapping("/payment")
public class PaymentController {

    @RequestMapping(method = RequestMethod.POST)
    @JsonSerialize
    public Map<String, String> createIntent()  {

        Stripe.apiKey = "sk_test_PSu1SdTnTDpUwVotxIpNqzXW00Tz6kX2nV";
        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("usd").setAmount(1099L)
                .build();

        PaymentIntent intent = null;
        try {
            intent = PaymentIntent.create(createParams);
            HashMap<String, String> map = new HashMap<>();
//            map.put("public", "pk_test_6F20HBly8SsRgexvz67pwAjq00wjM1KJpM");
            map.put("secretKey", intent.getClientSecret());
            return map;

        } catch (StripeException e) {
            e.printStackTrace();
        }

        return null;
    }

}

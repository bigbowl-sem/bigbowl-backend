package edu.cmu.bigbowl.Controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import edu.cmu.bigbowl.Entity.Account;
import edu.cmu.bigbowl.Entity.Cart;
import edu.cmu.bigbowl.Entity.Cook;
import edu.cmu.bigbowl.Service.AccountService;
import edu.cmu.bigbowl.Service.CartService;
import edu.cmu.bigbowl.Service.CookService;
import edu.cmu.bigbowl.Service.OrderService;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stripe.exception.StripeException;
import edu.cmu.bigbowl.Entity.Order;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;


@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CookService cookService;

    @RequestMapping(method = RequestMethod.POST)
    @JsonSerialize
    public Map<String, String> createIntent(@RequestBody String cartId) {

        Stripe.apiKey = "sk_test_PSu1SdTnTDpUwVotxIpNqzXW00Tz6kX2nV";
        Cart theCart = cartService.getCartById(cartId).orElse(null);
        HashMap<String, String> map = new HashMap<>();

        if (theCart == null) {
            map.put("error with cart", "true");
        }
//        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
//                .setCurrency("usd").setAmount(theCart.getTotalPrice().longValue())
//                .build();

        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("usd").setAmount(4990L)
                .build();
        PaymentIntent intent = null;
        try {
            intent = PaymentIntent.create(createParams);
//            map.put("public", "pk_test_6F20HBly8SsRgexvz67pwAjq00wjM1KJpM");
            map.put("secretKey", intent.getClientSecret());
            return map;

        } catch (StripeException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(path = "/complete", method = RequestMethod.POST)
    @JsonSerialize
    public Order createOrder(@RequestBody Order order) {
        HashMap<String, String> map = new HashMap<>();
        //need some security here, but whatever

        Account account = accountService.getAccountByEaterId(order.getEaterId()).orElse(null);
        if (account == null) {
            map.put("success", "false");
            return null;
        }
        order.setPickUpName(account.getFirstName());
        order.setPickUpContact(account.getPhone());
        order.setDatetime(new Date());
        order.setOrderId(new ObjectId().toString());
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
        order.setPickUpTime(cal.getTime());
        order.setReadyTime(cal.getTime());
        Cook theCook = cookService.getCookById(order.getCookId()).orElse(null);
        order.setCookDisplayName(theCook.getDisplayName());
        orderService.postOrder(order);

        map.put("success", "true");
        return order;
    }

}

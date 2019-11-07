package edu.cmu.bigbowl.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "cart")
public class Cart {
    @Id
    private String cartId;
    @Field("checkoutItems")
    private List<CheckoutItem> checkoutItems;
    @Field("totalPrice")
    private Double totalPrice;

    public Cart(String cartId, List<CheckoutItem> checkoutItems, Double totalPrice) {
        this.cartId = cartId;
        this.checkoutItems = checkoutItems;
        this.totalPrice = totalPrice;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public List<CheckoutItem> getCheckoutItems() {
        return checkoutItems;
    }

    public void setCheckoutItems(List<CheckoutItem> checkoutItems) {
        this.checkoutItems = checkoutItems;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

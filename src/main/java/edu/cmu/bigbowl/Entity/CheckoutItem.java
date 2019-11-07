package edu.cmu.bigbowl.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class CheckoutItem {
    @Id
    private String checkoutItemId;
    @Field("price")
    private Double price;
    @Field("quantity")
    private Integer quantity;
    @Field("itemId")
    private String itemId;

    public CheckoutItem(String checkoutItemId, Double price, Integer quantity, String itemId) {
        this.checkoutItemId = checkoutItemId;
        this.price = price;
        this.quantity = quantity;
        this.itemId = itemId;
    }

    public String getCheckoutItemId() {
        return checkoutItemId;
    }

    public void setCheckoutItemId(String checkoutItemId) {
        this.checkoutItemId = checkoutItemId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}


package edu.cmu.bigbowl.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "order")
public class Order {
    @Id
    private String orderId;
    @Field("eaterId")
    private String eaterId;
    @Field("cookId")
    private String cookId;
    @Field("datetime")
    private Date datetime;
    @Field("tax")
    private Double tax;
    @Field("readyTime")
    private Date readyTime;
    @Field("pickUpName")
    private String pickUpName;
    @Field("pickUpContact")
    private String pickUpContact;
    @Field("pickUpTime")
    private Date pickUpTime;
    @Field("cartId")
    private String cartId;
    @Field("cookDisplayName")
    private String cookDisplayName;
    @Field("eaterConfirmed")
    private boolean eaterConfirmed;
    @Field("cookConfirmed")
    private boolean cookConfirmed;

    public Order(String orderId, String eaterId, String cookId, Date datetime, Double tax, Date readyTime, String pickUpName, String pickUpContact, Date pickUpTime) {
        this.orderId = orderId;
        this.eaterId = eaterId;
        this.cookId = cookId;
        this.datetime = datetime;
        this.tax = tax;
        this.readyTime = readyTime;
        this.pickUpName = pickUpName;
        this.pickUpContact = pickUpContact;
        this.pickUpTime = pickUpTime;
        this.cookDisplayName = "The Cook's Name";
        this.setEaterConfirmed(false);
        this.setCookConfirmed(false);
        this.setCookDisplayName("");
    }

    public void setOrderWithCart(String orderId, String eaterId, String cookId, Date datetime, Double tax, Date readyTime, String pickUpName, String pickUpContact, Date pickUpTime, Cart cart) {
        this.orderId = orderId;
        this.eaterId = eaterId;
        this.cookId = cookId;
        this.datetime = datetime;
        this.tax = tax;
        this.readyTime = readyTime;
        this.pickUpName = pickUpName;
        this.pickUpContact = pickUpContact;
        this.pickUpTime = pickUpTime;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEaterId() {
        return eaterId;
    }

    public void setEaterId(String eaterId) {
        this.eaterId = eaterId;
    }

    public String getCookId() {
        return cookId;
    }

    public void setCookId(String cookId) {
        this.cookId = cookId;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Date getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(Date readyTime) {
        this.readyTime = readyTime;
    }

    public String getPickUpName() {
        return pickUpName;
    }

    public void setPickUpName(String pickUpName) {
        this.pickUpName = pickUpName;
    }

    public String getPickUpContact() {
        return pickUpContact;
    }

    public void setPickUpContact(String pickUpContact) {
        this.pickUpContact = pickUpContact;
    }

    public Date getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(Date pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public boolean isEaterConfirmed() {
        return eaterConfirmed;
    }

    public void setEaterConfirmed(boolean eaterConfirmed) {
        this.eaterConfirmed = eaterConfirmed;
    }

    public boolean isCookConfirmed() {
        return cookConfirmed;
    }

    public void setCookConfirmed(boolean cookConfirmed) {
        this.cookConfirmed = cookConfirmed;
    }

    public String getCookDisplayName() {
        return cookDisplayName;
    }

    public void setCookDisplayName(String cookDisplayName) {
        this.cookDisplayName = cookDisplayName;
    }
}

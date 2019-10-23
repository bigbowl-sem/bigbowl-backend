package edu.cmu.bigbowl.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "review")
public class Review {
    @Id
    private String reviewId;
    @Field("orderID")
    private String orderId;
    @Field("cookId")
    private String cookId;
    @Field("eaterId")
    private String eaterId;

    public Review(String reviewId, String orderId, String cookId, String eaterId) {
        this.reviewId = reviewId;
        this.orderId = orderId;
        this.cookId = cookId;
        this.eaterId = eaterId;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCookId() {
        return cookId;
    }

    public void setCookId(String cookId) {
        this.cookId = cookId;
    }

    public String getEaterId() {
        return eaterId;
    }

    public void setEaterId(String eaterId) {
        this.eaterId = eaterId;
    }
}

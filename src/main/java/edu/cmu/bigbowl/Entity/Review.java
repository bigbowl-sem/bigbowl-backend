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
    @Field("textBody")
    private String textBody;
    @Field("rating")
    private Double rating;

    public Review(String reviewId, String orderId, String cookId, String eaterId, String textBody, Double rating) {
        this.reviewId = reviewId;
        this.orderId = orderId;
        this.cookId = cookId;
        this.eaterId = eaterId;
        this.textBody = textBody;
        this.rating = rating;
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

    public String getTextBody() {
        return textBody;
    }

    public void setTextBody(String textBody) {
        this.textBody = textBody;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}

package edu.cmu.bigbowl.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "eater")
public class Eater {
    @Id
    private String eaterId;
    @Field("rating")
    private Double rating;
    @Field("imgurUrl")
    private String imgurUrl;

    public Eater(String eaterId, Double rating, String imgurUrl) {
        this.eaterId = eaterId;
        this.rating = rating;
        this.imgurUrl = imgurUrl;
    }

    public String getEaterId() {
        return eaterId;
    }

    public void setEaterId(String eaterId) {
        this.eaterId = eaterId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getImgurUrl() {
        return imgurUrl;
    }

    public void setImgurUrl(String imgurUrl) {
        this.imgurUrl = imgurUrl;
    }
}

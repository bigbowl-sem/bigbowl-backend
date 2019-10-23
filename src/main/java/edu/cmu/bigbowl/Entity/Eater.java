package edu.cmu.bigbowl.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "eater")
public class Eater {
    @Id
    private String eaterId;
    @Field("rating")
    private Float rating;

    public Eater(String eaterId, Float rating) {
        this.eaterId = eaterId;
        this.rating = rating;
    }

    public String getEaterId() {
        return eaterId;
    }

    public void setEaterId(String eaterId) {
        this.eaterId = eaterId;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}

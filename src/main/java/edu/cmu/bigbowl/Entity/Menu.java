package edu.cmu.bigbowl.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "menu")
public class Menu {
    @Id
    private String cookId;
    @Field("createTime")
    private Date createTime;
    @Field("cooking")
    private Boolean cooking;
    @Field("cuisine")
    private String cuisine;
    @Field("publish")
    private Boolean publish;
    @Field("itemIds")
    private List<String> itemIds;

    public Menu(String cookId, Date createTime, Boolean cooking, String cuisine, Boolean publish, List<String> itemIds) {
        this.cookId = cookId;
        this.createTime = createTime;
        this.cooking = cooking;
        this.cuisine = cuisine;
        this.publish = publish;
        this.itemIds = itemIds;
    }

    public String getCookId() {
        return cookId;
    }

    public void setCookId(String cookId) {
        this.cookId = cookId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getCooking() {
        return cooking;
    }

    public void setCooking(Boolean cooking) {
        this.cooking = cooking;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public Boolean getPublish() {
        return publish;
    }

    public void setPublish(Boolean publish) {
        this.publish = publish;
    }

    public List<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }

    public void addItemId(String itemId) {
        this.itemIds.add(itemId);
    }
}

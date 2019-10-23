package edu.cmu.bigbowl.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "menu")
public class Menu {
    @Id
    private String menuId;
    @Field("createTime")
    private Date createTime;
    @Field("cooking")
    private Boolean cooking;
    @Field("cuisine")
    private String cuisine;
    @Field("publish")
    private Boolean publish;

    public Menu(String menuId, Date createTime, Boolean cooking, String cuisine, Boolean publish) {
        this.menuId = menuId;
        this.createTime = createTime;
        this.cooking = cooking;
        this.cuisine = cuisine;
        this.publish = publish;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
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
}

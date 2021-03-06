package edu.cmu.bigbowl.Entity;

import net.minidev.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "cook")
public class Cook {
    @Id
    private String cookId;
    @Field("permitNumber")
    private String permitNumber;
    @Field("address1")
    private String address1;
    @Field("address2")
    private String address2;
    @Field("city")
    private String city;
    @Field("state")
    private String state;
    @Field("zipCode")
    private int zipCode;
    @Field("country")
    private String country;
    @Field("orderList")
    private List<String> orderList;
    @Field("rating")
    private Double rating;
    @Field("verified")
    private Boolean verified;
    @Field("about")
    private String about;
    @Field("lat")
    private Double lat;
    @Field("lng")
    private Double lng;
    @Field("location")
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private JSONObject location;
    @Field("menuId")
    private String menuId;
    @Field("displayName")
    private String displayName;
    @Field("cuisine")
    private String cuisine;
    @Field("totalItem")
    private Integer totalItem;
    @Field("avgPrice")
    private Double avgPrice;
    @Field("totalRated")
    private Integer totalRated;
    @Field("imgurUrl")
    private String imgurUrl;


    public Cook(String cookId, String permitNumber, String address1, String address2, String city, String state, int zipCode, String country, List<String> orderList, Double rating, Boolean verified, String about, Double lat, Double lng, String menuId, String displayName, String imgurUrl) {
        this.cookId = cookId;
        this.permitNumber = permitNumber;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.orderList = orderList;
        this.rating = rating;
        this.verified = verified;
        this.about = about;
        this.lat = lat;
        this.lng = lng;
        ArrayList<Double> coordinates = new ArrayList<Double>();
        coordinates.add(lng);
        coordinates.add(lat);
        this.location = new JSONObject();
        this.location.appendField("type", "Point");
        this.location.appendField("coordinates", coordinates);
        this.menuId = menuId;
        this.displayName = displayName;
        this.totalItem = 0;
        this.avgPrice = 0.0;
        this.totalRated = 0;
        this.imgurUrl = imgurUrl;
        this.cuisine = "Thai";
    }

    public String getCookId() {
        return cookId;
    }

    public void setCookId(String cookId) {
        this.cookId = cookId;
    }

    public String getPermitNumber() {
        return permitNumber;
    }

    public void setPermitNumber(String permitNumber) {
        this.permitNumber = permitNumber;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<String> orderList) {
        this.orderList = orderList;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public JSONObject getLocation() {
        return location;
    }

    public void setLocation(JSONObject location) {
        this.location = location;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Integer getTotalRated() {
        return totalRated;
    }

    public void setTotalRated(Integer totalRated) {
        this.totalRated = totalRated;
    }

    public String getImgurUrl() {
        return imgurUrl;
    }

    public void setImgurUrl(String imgurUrl) {
        this.imgurUrl = imgurUrl;
    }
}

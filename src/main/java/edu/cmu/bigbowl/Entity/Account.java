package edu.cmu.bigbowl.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "account")
public class Account {
    @Id
    private String accountId;
    @Field("email")
    private String email;
    @Field("password")
    private String password;
    @Field("firstName")
    private String firstName;
    @Field("lastName")
    private String lastName;
    @Field("phone")
    private String phone;
    @Field("isEater")
    private Boolean isEater;
    @Field("isCook")
    private Boolean isCook;
    @Field("createTime")
    private Date createTime;

    public Account(String accountId, String email, String password, String firstName, String lastName, String phone, Boolean isEater, Boolean isCook) {
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.isEater = isEater;
        this.isCook = isCook;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getEater() {
        return isEater;
    }

    public void setEater(Boolean eater) {
        isEater = eater;
    }

    public Boolean getCook() {
        return isCook;
    }

    public void setCook(Boolean cook) {
        isCook = cook;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

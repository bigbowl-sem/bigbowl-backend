package edu.cmu.bigbowl.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "account")
public class Account {
    private int accountId;
    private String email;
    /*private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private boolean eater;
    private boolean cook;*/

    public Account(int accountId, String email) {
        this.accountId = accountId;
        this.email = email;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

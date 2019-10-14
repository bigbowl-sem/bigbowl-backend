package edu.cmu.bigbowl.Dao;


import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import edu.cmu.bigbowl.Entity.Account;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
@Qualifier("mongoData")
public class MongoAccountDaoImpl implements AccountDao {

    public static MongoAccountDaoImpl _self;
    MongoCollection<Document> accountCollection;

    public MongoAccountDaoImpl() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("bigbowl");
        this.accountCollection = database.getCollection("account");
    }

    public static AccountDao getInstance(){
        if (_self == null)
            _self = new MongoAccountDaoImpl();
        return _self;
    }


    @Override
    public Collection<Account> getAllAccounts() {
        ArrayList<Account> accountList = new ArrayList<>();
        FindIterable<Document> accountDocs = accountCollection.find();
        for(Document accountDoc: accountDocs) {
                Account account = new Account(
                        accountDoc.getInteger("accountId"),
                        accountDoc.getString("email")
                );
                accountList.add(account);
        }
        return accountList;
    }

    @Override
    public Account getAccountById(int id) {
        FindIterable<Document> accountDocs = accountCollection.find(Filters.eq("accountId", id));
        Account account = null;
        for(Document accountDoc: accountDocs) {
             account = new Account(
                    accountDoc.getInteger("accountId"),
                    accountDoc.getString("email")
            );
        }
        return account;
    }

    @Override
    public void removeAccountById(int id) {

    }

    @Override
    public Account updateAccount(Account account) {
        return null;
    }

    @Override
    public void insertAccountToDb(Account account) {

    }
}

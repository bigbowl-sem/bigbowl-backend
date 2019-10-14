package edu.cmu.bigbowl.Dao;

import edu.cmu.bigbowl.Entity.Account;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("fakeData")
public class FakeAccountDaoImpl implements AccountDao {

    //public void createAccount

    private static Map<Integer, Account> accounts;

    static {
        accounts = new HashMap<Integer, Account>(){
            {
                put(1, new Account(1, "aa@aa.aa"));
                put(2, new Account(2, "bb@bb.bb"));
                put(3, new Account(3, "cc@cc.cc"));
            }
        };
    }

    @Override
    public Collection<Account> getAllAccounts(){
        return this.accounts.values();
    }

    @Override
    public Account getAccountById(int id) {
        return this.accounts.get(id);
    }

    @Override
    public void removeAccountById(int id) {
        this.accounts.remove(id);
    }

    @Override
    public Account updateAccount(Account account) {
        Account targetAccount = this.accounts.get(account.getAccountId());
        targetAccount.setAccountId(account.getAccountId());
        targetAccount.setEmail(account.getEmail());
        this.accounts.put(account.getAccountId(), targetAccount);
        return targetAccount;
    }

    @Override
    public void insertAccountToDb(Account account) {
        this.accounts.put(account.getAccountId(), account);
    }
}

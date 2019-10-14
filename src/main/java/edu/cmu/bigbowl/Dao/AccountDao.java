package edu.cmu.bigbowl.Dao;

import edu.cmu.bigbowl.Entity.Account;

import java.util.Collection;

public interface AccountDao {
    Collection<Account> getAllAccounts();

    Account getAccountById(int id);

    void removeAccountById(int id);

    Account updateAccount(Account account);

    void insertAccountToDb(Account account);
}

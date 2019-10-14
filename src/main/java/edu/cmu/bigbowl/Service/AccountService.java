package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.AccountDao;
import edu.cmu.bigbowl.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccountService {

    @Autowired
    @Qualifier("mongoData")
    private AccountDao accountDaoImpl;

    public Collection<Account> getAllAccounts(){
        return accountDaoImpl.getAllAccounts();
    }

    public Account getAccountById(int id){
        return this.accountDaoImpl.getAccountById(id);
    }


    public void removeAccountById(int id) {
        this.accountDaoImpl.removeAccountById(id);
    }

    public void updateAccount(Account account){
        this.accountDaoImpl.updateAccount(account);
    }

    public void insertAccount(Account account) {
        this.accountDaoImpl.insertAccountToDb(account);
    }
}

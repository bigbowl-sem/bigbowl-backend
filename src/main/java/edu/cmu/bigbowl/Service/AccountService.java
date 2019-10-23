package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.AccountDao;
import edu.cmu.bigbowl.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    // Create
    public Account postAccount(Account account) {
        Date now = new Date();
        account.setCreateTime(now);
        return accountDao.save(account);
    }

    // Read
    public Collection<Account> getAllAccounts() {
        return accountDao.findAll();
    }

    public Optional<Account> getAccountById(String id) {
        return accountDao.findById(id);
    }

    // Update
    public Optional<Account> updateAccount(Account account) {
        if (account.getAccountId() != null) {
            return updateAccountById(account.getAccountId(), account);
        }
        else{
            return null;
        }
    }

    public Optional<Account> updateAccountById(String id, Account account) {
        Optional<Account> optAccount = accountDao.findById(id);
        if (account.getEmail() != null) {
            optAccount.ifPresent(theAccount -> theAccount.setEmail(account.getEmail()));
        }
        if (account.getPassword() != null) {
            optAccount.ifPresent(theAccount -> theAccount.setPassword(account.getPassword()));
        }
        if (account.getFirstName() != null) {
            optAccount.ifPresent(theAccount -> theAccount.setFirstName(account.getFirstName()));
        }
        if (account.getLastName() != null) {
            optAccount.ifPresent(theAccount -> theAccount.setLastName(account.getLastName()));
        }
        if (account.getPhone() != null) {
            optAccount.ifPresent(theAccount -> theAccount.setPhone(account.getPhone()));
        }
        if (account.getEater() != null) {
            optAccount.ifPresent(theAccount -> theAccount.setEater(account.getEater()));
        }
        if (account.getCook() != null) {
            optAccount.ifPresent(theAccount -> theAccount.setCook(account.getCook()));
        }
        optAccount.ifPresent(theAccount -> accountDao.save(theAccount));
        return optAccount;
    }

    // Delete
    public Optional<Account> deleteAccount(Account account) {
        Optional<Account> optAccount = accountDao.findById(account.getAccountId());
        optAccount.ifPresent(theAccount -> accountDao.delete(theAccount));
        return optAccount;
    }

    public Optional<Account> deleteAccountById(String id) {
        Optional<Account> optAccount = accountDao.findById(id);
        optAccount.ifPresent(theAccount -> accountDao.delete(theAccount));
        return optAccount;
    }

    public void deleteAccounts() {
        accountDao.deleteAll();
    }

}

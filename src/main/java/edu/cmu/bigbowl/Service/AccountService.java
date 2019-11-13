package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.AccountDao;
import edu.cmu.bigbowl.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.StrictMath.abs;

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

    public void postFakeAccount() {
        Integer numOfAccount = 50;
        ArrayList<String> firstName = new ArrayList<>();
        firstName.add("Jack");
        firstName.add("Jackson");
        firstName.add("Allen");
        firstName.add("Shawn");
        firstName.add("Cyder");
        firstName.add("Bob");
        firstName.add("Evan");
        firstName.add("Frank");
        firstName.add("Gorge");
        firstName.add("Emily");
        firstName.add("Sam");
        firstName.add("Sosa");
        firstName.add("Ivan");

        ArrayList<String> lastName = new ArrayList<>();
        lastName.add("Lynch");
        lastName.add("Smith");
        lastName.add("Jobs");
        lastName.add("Cook");
        lastName.add("Page");
        lastName.add("Tan");
        lastName.add("Show");

        for (Integer cnt = 0; cnt < numOfAccount; cnt += 1) {
            Random r = new Random();
            Integer firstNum = abs(r.nextInt()) % firstName.size();
            Integer lastNum = abs(r.nextInt()) % lastName.size();
            Account account = new Account("Fake" + cnt, null, null, firstName.get(firstNum), lastName.get(lastNum), null, Boolean.TRUE, Boolean.TRUE);
            accountDao.save(account);
        }
    }


    // Read
    public Collection<Account> getAllAccounts() {
        return accountDao.findAll();
    }

    public Optional<Account> getAccountById(String id) {
        return accountDao.findById(id);
    }

    public Optional<Account> getAccountByEaterId(String id) {
        return accountDao.findByEaterId(id);
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
        if (account.getIsEater() != null) {
            optAccount.ifPresent(theAccount -> theAccount.setIsEater(account.getIsEater()));
        }
        if (account.getIsCook() != null) {
            optAccount.ifPresent(theAccount -> theAccount.setIsCook(account.getIsCook()));
        }

        if(account.getCookId() != null) {
            optAccount.ifPresent(theAccount -> theAccount.setCookId(account.getCookId()));
        }

        if(account.getEaterId() != null) {
            optAccount.ifPresent(theAccount -> theAccount.setEaterId(account.getEaterId()));
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

package edu.cmu.bigbowl.Controller;

import edu.cmu.bigbowl.Entity.Account;
import edu.cmu.bigbowl.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable("id") int id){
        return accountService.getAccountById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAccountById(@PathVariable("id") int id){
        accountService.removeAccountById(id);
    }

    @RequestMapping(method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateAccountById(@RequestBody Account account){
        accountService.updateAccount (account);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertAccount(@RequestBody Account account){
        accountService.insertAccount(account);
    }

}

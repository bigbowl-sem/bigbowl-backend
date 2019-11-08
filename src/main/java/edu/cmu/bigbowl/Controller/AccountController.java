package edu.cmu.bigbowl.Controller;

import edu.cmu.bigbowl.Entity.Account;
import edu.cmu.bigbowl.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // GET
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable("id") String id) {
        return accountService.getAccountById(id).orElse(null);
    }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account deleteAccount(@RequestBody Account account) {
        return accountService.deleteAccount(account).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Account deleteAccountById(@PathVariable("id") String id) {
        return accountService.deleteAccountById(id).orElse(null);
    }

    @RequestMapping(value = "/reset", method = RequestMethod.DELETE)
    public void deleteAccounts() {
        accountService.deleteAccounts();
    }

    // PATCH
    @RequestMapping(method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account updateAccount(@RequestBody Account account) {
        return accountService.updateAccount(account).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Account updateAccountById(@PathVariable("id") String id, @RequestBody Account account) {
        return accountService.updateAccountById(id, account).orElse(null);
    }

    // POST
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account insertAccount(@RequestBody Account account) {
        return accountService.postAccount(account);
    }

    @RequestMapping(value = "/fake", method = RequestMethod.POST)
    public Collection<Account> insertAccount() {
        accountService.postFakeAccount();
        return accountService.getAllAccounts();
    }
}

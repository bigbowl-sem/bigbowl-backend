package edu.cmu.bigbowl.Controller;

import edu.cmu.bigbowl.Entity.*;
import edu.cmu.bigbowl.Service.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CookService cookService;

    @Autowired
    private EaterService eaterService;

    @Autowired
    private CartService cartService;

    @Autowired
    private MenuService menuService;

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
    // needs some debugging!
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account insertAccount(@RequestBody Account account) throws IOException {
        Account existingAccount = accountService.getAccountById(account.getAccountId()).orElse(null);
        if(existingAccount != null) {
            if(existingAccount.getIsEater() != account.getIsEater()) {
                account.setIsEater(true);
                account.setEaterId(createEater());
            } else if (existingAccount.getIsCook() != account.getIsCook()){
                account.setIsCook(true);
                account.setCookId(createCook(account));
            }
            accountService.updateAccountById(account.getAccountId(), account).orElse(null);
            return accountService.getAccountById(account.getAccountId()).orElse(null);
        }

        if(account.getIsCook()) {
            account.setCookId(createCook(account));
        } else if (account.getIsEater()) {
            account.setEaterId(createEater());
        }

        return accountService.postAccount(account);
    }

    @RequestMapping(value = "/fake", method = RequestMethod.POST)
    public Collection<Account> insertAccount() {
        accountService.postFakeAccount();
        return accountService.getAllAccounts();
    }

    private String createCook(Account account) throws IOException {
        String id = new ObjectId().toString();
        Cook theCook = new Cook(id, "mypermit",
                "17 Mission Street", "", "San Francisco",
                "CA", 94043, "USA", null, 3.0, false,
                "I'm a cook, man.", 37.414050, -122.077134, id, account.getFirstName(), "");
        cookService.postCook(theCook);
        Menu menu = new Menu(
                id, new Date(), false, null, false, new ArrayList()
        );
        menuService.postMenu(menu);
        return id;
    }

    private String createEater() {
        String id = new ObjectId().toString();
        Eater theEater = new Eater(
                id, 0.0, null
        );
        Cart newCart = new Cart(
                id, new ArrayList(), 0.00
        );
        cartService.postCart(newCart);
        eaterService.postEater(theEater);
        return id;
    }
}

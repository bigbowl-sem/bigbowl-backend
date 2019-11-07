package edu.cmu.bigbowl.Controller;

import edu.cmu.bigbowl.Entity.CheckoutItem;
import edu.cmu.bigbowl.Service.CheckoutItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/checkoutItem")
public class CheckoutItemController {

    @Autowired
    private CheckoutItemService checkoutItemService;

    // GET
    @RequestMapping(method = RequestMethod.GET)
    public Collection<CheckoutItem> getAllCheckoutItems() {
        return checkoutItemService.getAllCheckoutItems();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CheckoutItem getCheckoutItemById(@PathVariable("id") String id) {
        return checkoutItemService.getCheckoutItemById(id).orElse(null);
    }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public CheckoutItem deleteCheckoutItem(@RequestBody CheckoutItem checkoutItem) {
        return checkoutItemService.deleteCheckoutItem(checkoutItem).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public CheckoutItem deleteCheckoutItemById(@PathVariable("id") String id) {
        return checkoutItemService.deleteCheckoutItemById(id).orElse(null);
    }

    @RequestMapping(value = "/reset", method = RequestMethod.DELETE)
    public void deleteAccounts() {
        checkoutItemService.deleteAccounts();
    }

    // PATCH
    @RequestMapping(method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CheckoutItem updateCheckoutItem(@RequestBody CheckoutItem checkoutItem) {
        return checkoutItemService.updateCheckoutItems(checkoutItem).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public CheckoutItem updateCheckoutItemById(@PathVariable("id") String id, @RequestBody CheckoutItem checkoutItem) {
        return checkoutItemService.updateCheckoutItemById(id, checkoutItem).orElse(null);
    }

    // POST
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CheckoutItem insertCheckoutItem(@RequestBody CheckoutItem checkoutItem) {
        return checkoutItemService.postCheckoutItem(checkoutItem);
    }
}

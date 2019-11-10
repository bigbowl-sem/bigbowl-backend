package edu.cmu.bigbowl.Controller;

import edu.cmu.bigbowl.Entity.Cart;
import edu.cmu.bigbowl.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // GET
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Cart getCartById(@PathVariable("id") String id) {
        return cartService.getCartById(id).orElse(null);
    }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cart deleteCart(@RequestBody Cart cart) {
        return cartService.deleteCart(cart).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Cart deleteCartById(@PathVariable("id") String id) {
        return cartService.deleteCartById(id).orElse(null);
    }

    @RequestMapping(value = "/reset", method = RequestMethod.DELETE)
    public void deleteAccounts() {
        cartService.deleteCarts();
    }

    // PATCH
    @RequestMapping(method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cart updateCart(@RequestBody Cart cart) {
        return cartService.updateCarts(cart).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Cart updateCartById(@PathVariable("id") String id, @RequestBody Cart cart) {
        return cartService.updateCartById(id, cart).orElse(null);
    }

    // POST
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cart insertCart(@RequestBody Cart cart) {
        return cartService.postCart(cart);
    }
}

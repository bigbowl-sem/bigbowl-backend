package edu.cmu.bigbowl.Controller;

import edu.cmu.bigbowl.Entity.Cart;
import edu.cmu.bigbowl.Entity.Item;
import edu.cmu.bigbowl.Service.CartService;
import edu.cmu.bigbowl.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ItemService itemService;

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
    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
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
        Cart existingCart = cartService.getCartById(cart.getCartId()).orElse(null);
        if (existingCart == null) {
            return cartService.postCart(cart);
        }

        return cartService.updateCartById(cart.getCartId(), cart).get();
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean addItemToCart(@RequestParam(value = "cartId") String cartId, @RequestParam(value = "itemId") String itemId) {
        Item item = itemService.getItemById(itemId).orElse(null);
        if (item != null) {
            cartService.addItemToCart(cartId, item);
        }
        return true;
    }

    @RequestMapping(path = "/remove", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean removeItemFromCart(@RequestParam(value = "cartId") String cartId, @RequestParam(value = "itemId") String itemId) {
        cartService.removeItemFromCart(cartId, itemId);
        return true;
    }


}

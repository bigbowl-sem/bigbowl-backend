package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.CartDao;
import edu.cmu.bigbowl.Entity.Cart;
import edu.cmu.bigbowl.Entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartDao cartDao;

    // Create
    public Cart postCart(Cart cart) {
        return cartDao.save(cart);
    }

    // Read
    public Collection<Cart> getAllCarts() {
        return cartDao.findAll();
    }

    public Optional<Cart> getCartById(String id) {
        return cartDao.findById(id);
    }

    // Update
    public Optional<Cart> updateCarts(Cart cart) {
        if (cart.getCartId() != null) {
            // TODO: 10/22/19
            // Right now it will save with the latest JSON which it's Id matched. But won't update
            // accordingly.
            return updateCartById(cart.getCartId(), cart);
        } else {
            return null;
        }
    }

    public Optional<Cart> addItemToCart(String cartId, Item item) {
        Cart cart = this.getCartById(cartId).orElse(null);
        if (cart != null) {

            ArrayList<Item> theItems = new ArrayList(cart.getCheckoutItems());
            theItems.add(item);

            cart.setCheckoutItems((List) theItems);

            return updateCartById(cart.getCartId(), cart);
        } else {
            return null;
        }
    }

    public Optional<Cart> removeItemFromCart(String cartId, String itemId) {
        Cart cart = this.getCartById(cartId).orElse(null);
        if (cart != null) {

            ArrayList<Item> theItems = new ArrayList(cart.getCheckoutItems());
            for (Item item : theItems) {
                if (item.getItemId().equals(itemId)) {
                    theItems.remove(item);
                    break;
                }
            }

            cart.setCheckoutItems((List) theItems);

            return updateCartById(cart.getCartId(), cart);
        } else {
            return null;
        }
    }

    public Optional<Cart> updateCartById(String id, Cart cart) {
        Optional<Cart> optCart = cartDao.findById(id);
        // TODO: 10/22/19
        // Right now it will save with the latest JSON which it's Id matched. But won't update
        // accordingly.
        optCart.ifPresent(theCart -> cartDao.save(cart));
        return optCart;
    }

    // Delete
    public Optional<Cart> deleteCart(Cart cart) {
        Optional<Cart> optCart = cartDao.findById(cart.getCartId());
        optCart.ifPresent(theCart -> cartDao.delete(theCart));
        return optCart;
    }

    public Optional<Cart> deleteCartById(String id) {
        Optional<Cart> optCart = cartDao.findById(id);
        optCart.ifPresent(theCart -> cartDao.delete(theCart));
        return optCart;
    }

    public void deleteCarts() {
        cartDao.deleteAll();
    }
}

package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.CartDao;
import edu.cmu.bigbowl.Entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
        }
        else{
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

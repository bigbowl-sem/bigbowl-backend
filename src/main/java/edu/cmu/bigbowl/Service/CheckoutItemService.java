package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.CheckoutItemDao;
import edu.cmu.bigbowl.Entity.CheckoutItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CheckoutItemService {

    @Autowired
    private CheckoutItemDao checkoutItemDao;

    // Create
    public CheckoutItem postCheckoutItem(CheckoutItem checkoutItem) {
        return checkoutItemDao.save(checkoutItem);
    }

    // Read
    public Collection<CheckoutItem> getAllCheckoutItems() {
        return checkoutItemDao.findAll();
    }

    public Optional<CheckoutItem> getCheckoutItemById(String id) {
        return checkoutItemDao.findById(id);
    }

    // Update
    public Optional<CheckoutItem> updateCheckoutItems(CheckoutItem checkoutItem) {
        if (checkoutItem.getCheckoutItemId() != null) {
            // TODO: 10/22/19
            // Right now it will save with the latest JSON which it's Id matched. But won't update
            // accordingly.
            return updateCheckoutItemById(checkoutItem.getCheckoutItemId(), checkoutItem);
        }
        else{
            return null;
        }
    }

    public Optional<CheckoutItem> updateCheckoutItemById(String id, CheckoutItem checkoutItem) {
        Optional<CheckoutItem> optCheckoutItem = checkoutItemDao.findById(id);
        // TODO: 10/22/19
        // Right now it will save with the latest JSON which it's Id matched. But won't update
        // accordingly.
        optCheckoutItem.ifPresent(theCheckoutItem -> checkoutItemDao.save(checkoutItem));
        return optCheckoutItem;
    }

    // Delete
    public Optional<CheckoutItem> deleteCheckoutItem(CheckoutItem checkoutItem) {
        Optional<CheckoutItem> optCheckoutItem = checkoutItemDao.findById(checkoutItem.getCheckoutItemId());
        optCheckoutItem.ifPresent(theCheckoutItem -> checkoutItemDao.delete(theCheckoutItem));
        return optCheckoutItem;
    }

    public Optional<CheckoutItem> deleteCheckoutItemById(String id) {
        Optional<CheckoutItem> optCheckoutItem = checkoutItemDao.findById(id);
        optCheckoutItem.ifPresent(theCheckoutItem -> checkoutItemDao.delete(theCheckoutItem));
        return optCheckoutItem;
    }

    public void deleteAccounts() {
        checkoutItemDao.deleteAll();
    }
}

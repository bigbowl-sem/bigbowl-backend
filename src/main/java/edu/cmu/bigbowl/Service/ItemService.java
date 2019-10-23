package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.ItemDao;
import edu.cmu.bigbowl.Entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemDao itemDao;

    // Create
    public Item postItem(Item item) {
        return itemDao.save(item);
    }

    // Read
    public Collection<Item> getAllItems() {
        return itemDao.findAll();
    }

    public Optional<Item> getItemById(String id) {
        return itemDao.findById(id);
    }

    // Update
    public Optional<Item> updateItems(Item item) {
        if (item.getItemId() != null) {
            // TODO: 10/22/19
            // Right now it will save with the latest JSON which it's Id matched. But won't update
            // accordingly.
            return updateItemById(item.getItemId(), item);
        }
        else{
            return null;
        }
    }

    public Optional<Item> updateItemById(String id, Item item) {
        Optional<Item> optItem = itemDao.findById(id);
        // TODO: 10/22/19
        // Right now it will save with the latest JSON which it's Id matched. But won't update
        // accordingly.
        optItem.ifPresent(theItem -> itemDao.save(item));
        return optItem;
    }

    // Delete
    public Optional<Item> deleteItem(Item item) {
        Optional<Item> optItem = itemDao.findById(item.getItemId());
        optItem.ifPresent(theItem -> itemDao.delete(theItem));
        return optItem;
    }

    public Optional<Item> deleteItemById(String id) {
        Optional<Item> optItem = itemDao.findById(id);
        optItem.ifPresent(theItem -> itemDao.delete(theItem));
        return optItem;
    }

    public void deleteAccounts() {
        itemDao.deleteAll();
    }
}

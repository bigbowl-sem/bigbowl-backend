package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.ItemDao;
import edu.cmu.bigbowl.Entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.Math.abs;

@Service
public class ItemService {

    @Autowired
    private ItemDao itemDao;

    // Create
    public Item postItem(Item item) {
        return itemDao.save(item);
    }

    public void postFakeItem(){
        Integer numOfAccount = 50;
        Double pMin = 0.0;
        Double pMax = 5.0;
        ArrayList<String> cuisines = new ArrayList<>();
        cuisines.add("Thai");
        cuisines.add("Japanese");
        cuisines.add("Chinese");
        cuisines.add("Italian");
        cuisines.add("American");
        cuisines.add("Mexican");

        ArrayList<String> names = new ArrayList<>();
        names.add("Pad Thai");
        names.add("Thai Curry");
        names.add("Udon Noodle");
        names.add("Ramen");
        names.add("Taso's Chicken");
        names.add("Dumpling");
        names.add("Pasta");
        names.add("Bistro");
        names.add("Pizza");
        names.add("Hot Dog");
        names.add("Taco");
        names.add("Burrito");


        for (Integer cnt = 0; cnt < numOfAccount; cnt += 1) {
            Random r = new Random();
            Integer cuisineNum = abs(r.nextInt()) % cuisines.size();
            Integer itemNum = cuisineNum * 2 + abs(r.nextInt()) % 2;
            Double pValue = pMin + (pMax - pMin) * r.nextDouble();
            Item account = new Item("Fake" + cnt, names.get(itemNum),"Nice and Tasty", r.nextInt() % 10, pValue, cuisines.get(cuisineNum));
            itemDao.save(account);
        }
    }
    // Read
    public Collection<Item> getAllItems() {
        return itemDao.findAll();
    }

    public Optional<Item> getItemById(String id) {
        return itemDao.findById(id);
    }

    public List<Item> getItemsByCuisine(String cuisine) {
        return itemDao.findItemsByCuisine(cuisine);
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

    public void deleteitems() {
        itemDao.deleteAll();
    }
}

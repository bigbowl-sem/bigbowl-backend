package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.CookDao;
import edu.cmu.bigbowl.Dao.ItemDao;
import edu.cmu.bigbowl.Dao.MenuDao;
import edu.cmu.bigbowl.Entity.Cook;
import edu.cmu.bigbowl.Entity.Item;
import edu.cmu.bigbowl.Entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.Math.abs;

@Service
public class ItemService {

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private CookDao cookDao;
    @Autowired
    private MenuDao menuDao;

    // Create
    public Item postItem(Item item) {
        String cookId = item.getCookId();
        Optional<Cook> optCook = cookDao.findById(cookId);
        optCook.ifPresent(theCook -> theCook.setCuisine(theCook.getCuisine() + " " + item.getCuisine()));
        optCook.ifPresent(theCook -> cookDao.save(theCook));
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
            String itemId = "FakeItemId" + cnt;

            // update cook cuisine
            Optional<Cook> optCook = cookDao.findById("Fake" + cnt);
            optCook.ifPresent(theCook -> theCook.setCuisine(cuisines.get(cuisineNum)));
            // update cook avgPrice and TotalItem
            optCook.ifPresent(theCook -> theCook.setAvgPrice( ((theCook.getAvgPrice() * theCook.getTotalItem()) + pValue) / (theCook.getTotalItem() + 1)) );
            optCook.ifPresent(theCook -> theCook.setTotalItem(theCook.getTotalItem() + 1));
            optCook.ifPresent(theCook -> cookDao.save(theCook));
            // update menu items
            Optional<Menu> optMenu = menuDao.findById("Fake" + cnt);
            optMenu.ifPresent(theMenu -> theMenu.addItemId(itemId));
            optMenu.ifPresent(theMenu -> menuDao.save(theMenu));

            Item item = new Item(itemId, names.get(itemNum),"Nice and Tasty", r.nextInt() % 10, pValue, cuisines.get(cuisineNum), "Fake" + cnt);
            itemDao.save(item);
        }
    }
    // Read
    public Collection<Item> getAllItems() {
        return itemDao.findAll();
    }

    public Optional<Item> getItemById(String id) {
        return itemDao.findById(id);
    }

    public List<Item> getItemByCookId(String cookId) {
        return itemDao.findItemsByCookId(cookId);
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

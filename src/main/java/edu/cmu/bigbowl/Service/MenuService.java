package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.MenuDao;
import edu.cmu.bigbowl.Entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.StrictMath.abs;

@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    // Create
    public Menu postMenu(Menu menu) {
        return menuDao.save(menu);
    }

    public void postFakeMenu() {
        Integer numOfAccount = 50;
        ArrayList<String> cuisines = new ArrayList<>();
        cuisines.add("Thai");
        cuisines.add("Japanese");
        cuisines.add("Chinese");
        cuisines.add("Italian");
        cuisines.add("American");
        cuisines.add("Mexican");

        for (Integer cnt = 0; cnt < numOfAccount; cnt += 1) {
            Random r = new Random();
            Integer cuisineNum = abs(r.nextInt()) % cuisines.size();
            List<String> itemIds = new ArrayList<>();
            itemIds.add("Fake" + cnt);
            Menu account = new Menu("Fake" + cnt, new Date(), Boolean.TRUE, cuisines.get(cuisineNum), Boolean.TRUE, itemIds);
            menuDao.save(account);
        }
    }

    // Read
    public Collection<Menu> getAllMenus() {
        return menuDao.findAll();
    }

    public Optional<Menu> getMenuById(String id) {
        return menuDao.findById(id);
    }

    // Update
    public Optional<Menu> updateMenus(Menu menu) {
        if (menu.getCookId() != null) {
            // TODO: 10/22/19
            // Right now it will save with the latest JSON which it's Id matched. But won't update
            // accordingly.
            return updateMenuById(menu.getCookId(), menu);
        } else {
            return null;
        }
    }

    public Optional<Menu> updateMenuById(String id, Menu menu) {
        Optional<Menu> optMenu = menuDao.findById(id);
        // TODO: 10/22/19
        // Right now it will save with the latest JSON which it's Id matched. But won't update
        // accordingly.
        optMenu.ifPresent(theMenu -> menuDao.save(menu));
        return optMenu;
    }

    // Delete
    public Optional<Menu> deleteMenu(Menu menu) {
        Optional<Menu> optMenu = menuDao.findById(menu.getCookId());
        optMenu.ifPresent(theMenu -> menuDao.delete(theMenu));
        return optMenu;
    }

    public Optional<Menu> deleteMenuById(String id) {
        Optional<Menu> optMenu = menuDao.findById(id);
        optMenu.ifPresent(theMenu -> menuDao.delete(theMenu));
        return optMenu;
    }

    public void deleteMenus() {
        menuDao.deleteAll();
    }
}

package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.MenuDao;
import edu.cmu.bigbowl.Entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    // Create
    public Menu postMenu(Menu menu) {
        return menuDao.save(menu);
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
        if (menu.getMenuId() != null) {
            // TODO: 10/22/19
            // Right now it will save with the latest JSON which it's Id matched. But won't update
            // accordingly.
            return updateMenuById(menu.getMenuId(), menu);
        }
        else{
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
        Optional<Menu> optMenu = menuDao.findById(menu.getMenuId());
        optMenu.ifPresent(theMenu -> menuDao.delete(theMenu));
        return optMenu;
    }

    public Optional<Menu> deleteMenuById(String id) {
        Optional<Menu> optMenu = menuDao.findById(id);
        optMenu.ifPresent(theMenu -> menuDao.delete(theMenu));
        return optMenu;
    }

    public void deleteAccounts() {
        menuDao.deleteAll();
    }
}

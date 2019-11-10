package edu.cmu.bigbowl.Controller;

import edu.cmu.bigbowl.Entity.Item;
import edu.cmu.bigbowl.Entity.Menu;
import edu.cmu.bigbowl.Service.ItemService;
import edu.cmu.bigbowl.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private ItemService itemService;

    // GET
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<Item> getMenuByCookId(@PathVariable("id") String id) {
        return itemService.getItemByCookId(id);
    }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public Menu deleteMenu(@RequestBody Menu menu) {
        return menuService.deleteMenu(menu).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Menu deleteMenuById(@PathVariable("id") String id) {
        return menuService.deleteMenuById(id).orElse(null);
    }

    @RequestMapping(value = "/reset", method = RequestMethod.DELETE)
    public void deleteAccounts() {
        menuService.deleteMenus();
    }

    // PATCH
    @RequestMapping(method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Menu updateMenu(@RequestBody Menu menu) {
        return menuService.updateMenus(menu).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Menu updateMenuById(@PathVariable("id") String id, @RequestBody Menu menu) {
        return menuService.updateMenuById(id, menu).orElse(null);
    }

    // POST
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Menu insertMenu(@RequestBody Menu menu) {
        return menuService.postMenu(menu);
    }

    @RequestMapping(value = "/fake", method = RequestMethod.POST)
    public Collection<Menu> insertItem() {
        menuService.postFakeMenu();
        return menuService.getAllMenus();
    }
}

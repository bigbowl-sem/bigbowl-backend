package edu.cmu.bigbowl.Controller;

import edu.cmu.bigbowl.Entity.Item;
import edu.cmu.bigbowl.Entity.Menu;
import edu.cmu.bigbowl.Service.ItemService;
import edu.cmu.bigbowl.Service.MenuService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

class AddItemToMenuRequest {
    private String menuId;
    private String name;
    private String description;
    private Integer quantity;
    private Double unitPrice;
    private String cuisine;
    private String imgurUrl;

    public AddItemToMenuRequest(String menuId, String name, String description, Integer quantity, Double unitPrice, String cuisine, String imgurUrl) {
        this.menuId = menuId;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.cuisine = cuisine;
        this.imgurUrl = imgurUrl;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getImgurUrl() {
        return imgurUrl;
    }

    public void setImgurUrl(String imageUrl) {
        this.imgurUrl = imgurUrl;
    }
}

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
    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(path = "/item", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Item> insertItemIntoMenu(@RequestBody AddItemToMenuRequest request) {
        String itemId = new ObjectId().toString();
        Item newItem = new Item(itemId, request.getName(),
                request.getDescription(), request.getQuantity(),
                request.getUnitPrice(), request.getCuisine(), request.getMenuId(), request.getImgurUrl());
        itemService.postItem(newItem);
        Menu menu = menuService.getMenuById(request.getMenuId()).orElse(null);
        menu.addItemId(newItem.getItemId());
        menuService.updateMenuById(request.getMenuId(), menu);
        return itemService.getItemByCookId(request.getMenuId());
    }

    @RequestMapping(value = "/fake", method = RequestMethod.POST)
    public Collection<Menu> insertItem() {
        menuService.postFakeMenu();
        return menuService.getAllMenus();
    }
}

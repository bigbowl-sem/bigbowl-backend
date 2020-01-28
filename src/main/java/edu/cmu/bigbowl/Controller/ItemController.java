package edu.cmu.bigbowl.Controller;

import edu.cmu.bigbowl.Entity.Item;
import edu.cmu.bigbowl.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // GET
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Item getItemById(@PathVariable("id") String id) {
        return itemService.getItemById(id).orElse(null);
    }

    @RequestMapping(value = "/cuisine/{cuisine}", method = RequestMethod.GET)
    public List<Item> getItemsByCuisine(@PathVariable("cuisine") String cuisine) {
        return itemService.getItemsByCuisine(cuisine);
    }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Item deleteItem(@RequestBody Item item) {
        return itemService.deleteItem(item).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Item deleteItemById(@PathVariable("id") String id) {
        return itemService.deleteItemById(id).orElse(null);
    }

    @RequestMapping(value = "/reset", method = RequestMethod.DELETE)
    public void deleteAccounts() {
        itemService.deleteitems();
    }

    // PATCH
    @RequestMapping(method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Item updateItem(@RequestBody Item item) {
        return itemService.updateItems(item).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Item updateItemById(@PathVariable("id") String id, @RequestBody Item item) {
        return itemService.updateItemById(id, item).orElse(null);
    }

    // POST
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Item insertItem(@RequestBody Item item) {
        return itemService.postItem(item);
    }

    @RequestMapping(value = "/fake", method = RequestMethod.POST)
    public Collection<Item> insertItem() {
        itemService.postFakeItem();
        return itemService.getAllItems();
    }
}

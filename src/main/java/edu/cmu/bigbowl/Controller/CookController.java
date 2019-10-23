package edu.cmu.bigbowl.Controller;

import edu.cmu.bigbowl.Entity.Cook;
import edu.cmu.bigbowl.Service.CookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/cook")
public class CookController {

    @Autowired
    private CookService cookService;

    // GET
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Cook> getAllCooks() {
        return cookService.getAllCooks();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Cook getCookById(@PathVariable("id") String id) {
        return cookService.getCookById(id).orElse(null);
    }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cook deleteCook(@RequestBody Cook cook) {
        return cookService.deleteCook(cook).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Cook deleteCookById(@PathVariable("id") String id) {
        return cookService.deleteCookById(id).orElse(null);
    }

    @RequestMapping(value = "/reset", method = RequestMethod.DELETE)
    public void deleteAccounts() {
        cookService.deleteAccounts();
    }

    // PATCH
    @RequestMapping(method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cook updateCook(@RequestBody Cook cook) {
        return cookService.updateCooks(cook).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Cook updateCookById(@PathVariable("id") String id, @RequestBody Cook cook) {
        return cookService.updateCookById(id, cook).orElse(null);
    }

    // POST
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cook insertCook(@RequestBody Cook cook) {
        return cookService.postCook(cook);
    }
}

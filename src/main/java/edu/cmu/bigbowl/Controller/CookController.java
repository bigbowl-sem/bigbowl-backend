package edu.cmu.bigbowl.Controller;

import edu.cmu.bigbowl.Entity.Cook;
import edu.cmu.bigbowl.Service.CookService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.geo.Point;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

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

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Cook> getCookByPoint(@RequestBody JSONObject cookQuery) {
        Double lng = cookQuery.getAsNumber("lng").doubleValue();
        Double lat = cookQuery.getAsNumber("lat").doubleValue();
        Double radius = cookQuery.getAsNumber("radius").doubleValue();
        Double rating = cookQuery.getAsNumber("rating").doubleValue();
        Point point = new Point(lng, lat);
        Distance distance = new Distance(radius, Metrics.MILES);
        if (rating == null){
            return cookService.getCookByPoint(point, distance);
        }
        else {
            List<Cook> cooks = cookService.getCookByPoint(point, distance);
            for(Cook cook:cooks){
                if (cook.getRating() < rating) {
                    cooks.remove(cook);
                }
            }
            return cooks;
        }
    }
    /*
    @RequestMapping(value = "/{lat}/{lng}/{radius}", method = RequestMethod.GET)
    public List<Cook> getCookByPoint(@PathVariable("lat") double lat, @PathVariable("lng") double lng, @PathVariable("radius") double radius) {
        //return cookService.getCookById(id).orElse(null);
        Point point = new Point(lng, lat);
        Distance distance = new Distance(radius, Metrics.MILES);
        return cookService.getCookByPoint(point, distance);
    }
    */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Cook> getCookByPoint(@RequestParam("lat") Double lat, @RequestParam("lng") Double lng, @RequestParam("radius") Double radius) {
        //return cookService.getCookById(id).orElse(null);
        Point point = new Point(lng, lat);
        Distance distance = new Distance(radius, Metrics.MILES);
        return cookService.getCookByPoint(point, distance);
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

    @RequestMapping(value = "/fake", method = RequestMethod.POST)
    public Collection<Cook> insertCook() {
        cookService.postFakeCook();
        return cookService.getAllCooks();
    }
}

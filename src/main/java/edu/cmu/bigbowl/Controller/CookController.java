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

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testCookFunctions(){
        String ans = "";
        // test post and get
        Cook cook = new Cook("TESTING", "T","E","S","T","T",0,
                "E",null, 5.0 ,Boolean.FALSE,"T", 37.376202, -122.101392);
        cookService.postCook(cook);
        Cook getCook = cookService.getCookById("TESTING").orElse(null);
        if (getCook != null){
            ans += "postCook works\r\n";
            ans += "getCookById works\r\n";
        }
        else{
            ans += "postCook Fail\r\n";
            ans += "getCookById Fail\r\n";
        }

        // test patch and get
        cook.setRating(1.0);
        cookService.updateCookById("TESTING", cook);
        getCook = cookService.getCookById("TESTING").orElse(null);
        if (getCook != null && getCook.getRating() == 1.0){
            ans += "updateCookById works\r\n";
        }
        else{
            ans += "updateCookById Fail\r\n";
        }

        // test geoSearch and get
        cookService.updateCookById("TESTING", cook);
        getCook = cookService.getCookById("TESTING").orElse(null);
        Point point = new Point(cook.getLng(), cook.getLat());
        Distance distance = new Distance(10, Metrics.MILES);
        List<Cook> getCooks = cookService.getCookByPoint(point, distance);
        Boolean flag = Boolean.FALSE;
        for (Cook c: getCooks)
        {
            if (c.getCookId().equals("TESTING")){
                flag = Boolean.TRUE;
            }
        }
        if (flag == Boolean.TRUE) {
            ans += "getCookByPoint works\r\n";
        }
        else{
            ans += "getCookByPoint Fail\r\n";
        }

        // test delete and get
        cookService.deleteCookById("TESTING");
        getCook = cookService.getCookById("TESTING").orElse(null);
        if (getCook == null){
            ans += "deleteCookById works\r\n";
        }
        else{
            ans += "deleteCookById Fail\r\n";
        }
        return ans;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Cook getCookById(@PathVariable("id") String id) {
        return cookService.getCookById(id).orElse(null);
    }

    @RequestMapping(value = "/proximity", method = RequestMethod.GET)
    public List<Cook> getCookByPoint(@RequestParam("lng") Double lng, @RequestParam("lat") Double lat, @RequestParam("radius") int radius ) {
        Double rating = null; //cookQuery.getAsNumber("rating").doubleValue();
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

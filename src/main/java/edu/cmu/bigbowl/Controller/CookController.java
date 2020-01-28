package edu.cmu.bigbowl.Controller;

import edu.cmu.bigbowl.Entity.Cook;
import edu.cmu.bigbowl.Service.CookService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.geo.Point;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


class ImageRequestBody {
    private String cookId;
    private String imgurUrl;

    public ImageRequestBody(String cookId, String imgurUrl) {
        this.cookId = cookId;
        this.imgurUrl = imgurUrl;
    }

    public String getCookId() {
        return cookId;
    }

    public void setCookId(String cookId) {
        this.cookId = cookId;
    }

    public String getImgurUrl() {
        return imgurUrl;
    }

    public void setImgurUrl(String imgurUrl) {
        this.imgurUrl = imgurUrl;
    }
}

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

    /*@RequestMapping(value = "/proximity", method = RequestMethod.GET)
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
    }*/
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
    public List<Cook> getCookByPoint(
            @RequestParam(value = "lat") Double lat,
            @RequestParam("lng") Double lng,
            @RequestParam("radius") Double radius,
            @RequestParam(value = "cuisine", required = false) String cuisine,
            @RequestParam(value = "pMin", required = false) String pMin,
            @RequestParam(value = "pMax", required = false) String pMax,
            @RequestParam(value = "rMin", required = false) String rMin,
            @RequestParam(value = "rMax", required = false) String rMax
    ) {
        Point point = new Point(lng, lat);
        Distance distance = new Distance(radius, Metrics.MILES);
        List<Cook> cooks;
        List<Cook> ansCooks = new ArrayList<>();
        if (cuisine == null && pMin == null && pMax == null && rMin == null && rMax == null) {
            return cookService.getCookByPoint(point, distance);
        }
        if (pMin == null && rMin == null) {
            return cookService.getCookByPoint(point, distance, cuisine);
        }
        if (pMin != null && rMin == null) {
            return cookService.getCookByPointWithPrice(point, distance, cuisine, Double.parseDouble(pMin), Double.parseDouble(pMax));
        }
        if (pMin == null && rMin != null) {
            return cookService.getCookByPointWithRating(point, distance, cuisine, Double.parseDouble(rMin), Double.parseDouble(rMax));
        }
        return cookService.getCookByPoint(point, distance, cuisine, Double.parseDouble(pMin), Double.parseDouble(pMax), Double.parseDouble(rMin), Double.parseDouble(rMax));
    }

    // DELETE
    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cook deleteCook(@RequestBody Cook cook) {
        return cookService.deleteCook(cook).orElse(null);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Cook deleteCookById(@PathVariable("id") String id) {
        return cookService.deleteCookById(id).orElse(null);
    }

    @RequestMapping(value = "/reset", method = RequestMethod.DELETE)
    public void deleteAccounts() {
        cookService.deleteCooks();
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


    @RequestMapping(path = "/addImage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cook addCookPhoto(@RequestBody ImageRequestBody body) {
        return cookService.addImage(body.getCookId(), body.getImgurUrl());
    }

    @RequestMapping(value = "/fake", method = RequestMethod.POST)
    public Collection<Cook> insertCook() throws IOException {
        cookService.postFakeCook();
        return cookService.getAllCooks();
    }
}

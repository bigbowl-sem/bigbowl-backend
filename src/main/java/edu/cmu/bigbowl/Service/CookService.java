package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.AccountDao;
import edu.cmu.bigbowl.Dao.CookDao;
import edu.cmu.bigbowl.Dao.ItemDao;
import edu.cmu.bigbowl.Dao.MenuDao;
import edu.cmu.bigbowl.Entity.Account;
import edu.cmu.bigbowl.Entity.Cook;
import edu.cmu.bigbowl.Entity.Item;
import edu.cmu.bigbowl.Entity.Menu;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Distance;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;
import java.util.logging.Logger;

import static java.lang.Math.abs;

@Service
public class CookService {

    @Autowired
    private CookDao cookDao;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private MenuDao menuDao;

    // Create
    public Cook postCook(Cook cook) {
        return cookDao.save(cook);
    }

    public void postFakeCook() throws IOException {
        Integer numOfCook = 50;
        Double latMin, latMax, lngMin, lngMax, ratingMin, ratingMax;
        latMin = 37.376877;
        latMax = 37.427483;
        lngMin = -122.075116;
        lngMax = -122.085842;
        ratingMin = 0.0;
        ratingMax = 5.0;
        /*
        if (fakeCookGen.get("num") != null) {
            numOfCook = fakeCookGen.getAsNumber("num").intValue();
        }
        if (fakeCookGen.get("lat") != null) {
            lat = fakeCookGen.getAsNumber("lat").doubleValue();
        }
        if (fakeCookGen.get("lng") != null) {
            lng = fakeCookGen.getAsNumber("lng").doubleValue();
        }
        if (fakeCookGen.get("radius") != null) {
            radius = fakeCookGen.getAsNumber("radius").doubleValue();
        }
        */
        ArrayList<String> imgurUrlsMale = new ArrayList<>();
        imgurUrlsMale.add("https://i.imgur.com/wWbEEoX.jpg");//m0
        imgurUrlsMale.add("https://i.imgur.com/cPiXJKn.jpg");//m1
        imgurUrlsMale.add("https://i.imgur.com/FAh84zF.jpg");//m2
        imgurUrlsMale.add("https://i.imgur.com/avkzX5m.jpg");//m3
        imgurUrlsMale.add("https://i.imgur.com/HL7uWFf.jpg");//m4
        imgurUrlsMale.add("https://i.imgur.com/OgL2gYD.jpg");//m5
        imgurUrlsMale.add("https://i.imgur.com/xsmz7IX.jpg");//m6

        ArrayList<String> imgurUrlsFemale = new ArrayList<>();
        imgurUrlsFemale.add("https://i.imgur.com/tFlYY0j.jpg");//g7
        imgurUrlsFemale.add("https://i.imgur.com/epDkkj1.jpg");//g
        imgurUrlsFemale.add("https://i.imgur.com/Qzr4TXJ.jpg");//g
        imgurUrlsFemale.add("https://i.imgur.com/pfLc6eH.jpg");//g
        imgurUrlsFemale.add("https://i.imgur.com/mUM1ww3.jpg");//g
        imgurUrlsFemale.add("https://i.imgur.com/iMbpo41.jpg");//g
        imgurUrlsFemale.add("https://i.imgur.com/GmbRLc5.jpg");//g
        imgurUrlsFemale.add("https://i.imgur.com/PG15UCy.jpg");//g
        imgurUrlsFemale.add("https://i.imgur.com/O25v4ZF.jpg");//g
        imgurUrlsFemale.add("https://i.imgur.com/1TBQJlK.jpg");//g

        ArrayList<String> firstNameFemale = new ArrayList<>();
        firstNameFemale.add("Emily");
        firstNameFemale.add("Jen");
        firstNameFemale.add("Martha");
        firstNameFemale.add("Margot");
        firstNameFemale.add("Emma");
        firstNameFemale.add("Britney");
        firstNameFemale.add("Hillary");

        ArrayList<String> firstNameMale = new ArrayList<>();
        firstNameMale.add("Jack");
        firstNameMale.add("Jackson");
        firstNameMale.add("Allen");
        firstNameMale.add("Shawn");
        firstNameMale.add("Bob");
        firstNameMale.add("Evan");
        firstNameMale.add("Frank");
        firstNameMale.add("Gorge");
        firstNameMale.add("Sam");
        firstNameMale.add("Ivan");

        for (Integer cnt = 0; cnt < numOfCook; cnt += 1)
        {
            Random r = new Random();
            Cook cook = null;
            Double latValue = latMin + (latMax - latMin) * r.nextDouble();
            Double lngValue = lngMin + (lngMax - lngMin) * r.nextDouble();
            Double ratingValue = ratingMin + (ratingMax - ratingMin) * r.nextDouble();
            Integer imgurUrlNum = 0;
            Account account = accountDao.findById("Fake" + cnt).get();
            String displayName = account.getFirstName() + " " + account.getLastName();
            if (firstNameFemale.contains(account.getFirstName()))
            {
                imgurUrlNum = abs(r.nextInt()) % imgurUrlsFemale.size();
                cook = new Cook("Fake" + cnt, null, null, null,  null, null, 0, null, null, ratingValue, null, null, latValue, lngValue, "Fake" + cnt, displayName, imgurUrlsFemale.get(imgurUrlNum));
            }
            else if (firstNameMale.contains(account.getFirstName()))
            {
                imgurUrlNum = abs(r.nextInt()) % imgurUrlsMale.size();
                cook = new Cook("Fake" + cnt, null, null, null,  null, null, 0, null, null, ratingValue, null, null, latValue, lngValue, "Fake" + cnt, displayName, imgurUrlsMale.get(imgurUrlNum));
            }

            Menu menu = new Menu("Fake" + cnt, new Date(), Boolean.TRUE, null, Boolean.TRUE, new ArrayList<>());
            menuDao.save(menu);
            cookDao.save(cook);
        }

        return;
    }

    // Read
    public Collection<Cook> getAllCooks() {
        return cookDao.findAll();
    }

    public Optional<Cook> getCookById(String id) {
        return cookDao.findById(id);
    }

    public List<Cook> getCookByPoint(Point point, Distance distance) {
        return cookDao.findByLocationNear(point, distance);
    }

    public List<Cook> getCookByPoint(Point point, Distance distance, String cuisine) {
        return cookDao.findByLocationNearAndCuisineContains(point, distance, cuisine);
    }

    public List<Cook> getCookByPointWithPrice(Point point, Distance distance, String cuisine, Double pMin, Double pMax) {
        return cookDao.findByLocationNearAndCuisineContainsAndAvgPriceBetween(point, distance, cuisine, pMin, pMax);
    }

    public List<Cook> getCookByPointWithRating(Point point, Distance distance, String cuisine, Double rMin, Double rMax) {
        return cookDao.findByLocationNearAndCuisineContainsAndRatingBetween(point, distance, cuisine, rMin, rMax);
    }

    public List<Cook> getCookByPoint(Point point, Distance distance, String cuisine, Double pMin, Double pMax, Double rMin, Double rMax) {
        return cookDao.findByLocationNearAndCuisineContainsAndAvgPriceBetweenAndRatingBetween(point, distance, cuisine, pMin, pMax, rMin, rMax);
    }

    public Cook addImage(String cookId, String url) {
        Cook theCook = this.getCookById(cookId).orElse(null);
        if(theCook != null) {
            theCook.setImgurUrl(url);
            return this.postCook(theCook);

        }
        return null;
    }

    // Update
    public Optional<Cook> updateCooks(Cook cook) {
        if (cook.getCookId() != null) {
            // TODO: 10/22/19
            // Right now it will save with the latest JSON which it's Id matched. But won't update
            // accordingly.
            return updateCookById(cook.getCookId(), cook);
        }
        else{
            return null;
        }
    }

    public Optional<Cook> updateCookById(String id, Cook cook) {
        Optional<Cook> optCook = cookDao.findById(id);
        // TODO: 10/22/19
        // Right now it will save with the latest JSON which it's Id matched. But won't update
        // accordingly.
        if (cook.getRating() != null) {
            optCook.ifPresent(theCook -> theCook.setRating(cook.getRating()));
        }
        optCook.ifPresent(theCook -> cookDao.save(theCook));
        return optCook;
    }

    public void updateCooksByIdWithItem(String id){
        Optional<Cook> optCook = cookDao.findById(id);
        List<Item> items = itemDao.findItemsByCookId(id);
        if (items.size() != 0) {
            Item item = items.get(0);
            optCook.ifPresent(theCook -> theCook.setCuisine(item.getCuisine()));
        }
        optCook.ifPresent(theCook -> cookDao.save(theCook));
    }

    // Delete
    public Optional<Cook> deleteCook(Cook cook) {
        Optional<Cook> optCook = cookDao.findById(cook.getCookId());
        optCook.ifPresent(theCook -> cookDao.delete(theCook));
        return optCook;
    }

    public Optional<Cook> deleteCookById(String id) {
        Optional<Cook> optCook = cookDao.findById(id);
        optCook.ifPresent(theCook -> cookDao.delete(theCook));
        return optCook;
    }

    public void deleteCooks() {
        cookDao.deleteAll();
    }
}

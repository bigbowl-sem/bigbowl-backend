package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.AccountDao;
import edu.cmu.bigbowl.Dao.CookDao;
import edu.cmu.bigbowl.Entity.Account;
import edu.cmu.bigbowl.Entity.Cook;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Distance;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CookService {

    @Autowired
    private CookDao cookDao;
    @Autowired
    private AccountDao accountDao;

    // Create
    public Cook postCook(Cook cook) {
        return cookDao.save(cook);
    }

    public void postFakeCook() {
        Integer numOfCook = 50;
        Double latMin, latMax, lngMin, lngMax, ratingMin, ratingMax;
        latMin = 37.775902;
        latMax = 37.798165;
        lngMin = -122.414496;
        lngMax = -122.401194;
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

        for (Integer cnt = 0; cnt < numOfCook; cnt += 1)
        {

            Random r = new Random();
            Double latValue = latMin + (latMax - latMin) * r.nextDouble();
            Double lngValue = lngMin + (lngMax - lngMin) * r.nextDouble();
            Double ratingValue = ratingMin + (ratingMax - ratingMin) * r.nextDouble();
            Account account = accountDao.findById("Fake" + cnt).get();
            String displayName = account.getFirstName() + " " + account.getLastName() + "\'s";
            Cook cook = new Cook( "Fake" + cnt, null, null, null,  null, null, 0, null, null, ratingValue, null, null, latValue, lngValue, "Fake" + cnt, displayName);
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

    public void deleteAccounts() {
        cookDao.deleteAll();
    }
}

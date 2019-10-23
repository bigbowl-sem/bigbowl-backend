package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.CookDao;
import edu.cmu.bigbowl.Entity.Cook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CookService {

    @Autowired
    private CookDao cookDao;

    // Create
    public Cook postCook(Cook cook) {
        return cookDao.save(cook);
    }

    // Read
    public Collection<Cook> getAllCooks() {
        return cookDao.findAll();
    }

    public Optional<Cook> getCookById(String id) {
        return cookDao.findById(id);
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

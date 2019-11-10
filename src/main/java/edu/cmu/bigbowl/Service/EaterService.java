package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.EaterDao;
import edu.cmu.bigbowl.Entity.Eater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class EaterService {

    @Autowired
    private EaterDao eaterDao;

    // Create
    public Eater postEater(Eater eater) {
        return eaterDao.save(eater);
    }

    // Read
    public Collection<Eater> getAllEaters() {
        return eaterDao.findAll();
    }

    public Optional<Eater> getEaterById(String id) {
        return eaterDao.findById(id);
    }

    // Update
    public Optional<Eater> updateEaters(Eater eater) {
        if (eater.getEaterId() != null) {
            // TODO: 10/22/19
            // Right now it will save with the latest JSON which it's Id matched. But won't update
            // accordingly.
            return updateEaterById(eater.getEaterId(), eater);
        }
        else{
            return null;
        }
    }

    public Optional<Eater> updateEaterById(String id, Eater eater) {
        Optional<Eater> optEater = eaterDao.findById(id);
        // TODO: 10/22/19
        // Right now it will save with the latest JSON which it's Id matched. But won't update
        // accordingly.
        if (eater.getRating() != null) {
            optEater.ifPresent(theEater -> theEater.setRating(eater.getRating()));
        }
        optEater.ifPresent(theEater -> eaterDao.save(theEater));
        return optEater;
    }

    // Delete
    public Optional<Eater> deleteEater(Eater eater) {
        Optional<Eater> optEater = eaterDao.findById(eater.getEaterId());
        optEater.ifPresent(theEater -> eaterDao.delete(theEater));
        return optEater;
    }

    public Optional<Eater> deleteEaterById(String id) {
        Optional<Eater> optEater = eaterDao.findById(id);
        optEater.ifPresent(theEater -> eaterDao.delete(theEater));
        return optEater;
    }

    public void deleteEaters() {
        eaterDao.deleteAll();
    }
}

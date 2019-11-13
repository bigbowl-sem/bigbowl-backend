package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.EaterDao;
import edu.cmu.bigbowl.Entity.Eater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;

import static java.lang.StrictMath.abs;

@Service
public class EaterService {

    @Autowired
    private EaterDao eaterDao;

    // Create
    public Eater postEater(Eater eater) {
        return eaterDao.save(eater);
    }

    public void postFakeEaters() {
        Integer numOfCook = 50;
        Double ratingMin, ratingMax;
        ratingMin = 0.0;
        ratingMax = 5.0;
        ArrayList<String> imgurUrls = new ArrayList<>();
        imgurUrls.add("https://i.imgur.com/Qzr4TXJ.jpg");
        imgurUrls.add("https://i.imgur.com/pfLc6eH.jpg");
        imgurUrls.add("https://i.imgur.com/wWbEEoX.jpg");
        imgurUrls.add("https://i.imgur.com/cPiXJKn.jpg");
        imgurUrls.add("https://i.imgur.com/FAh84zF.jpg");
        imgurUrls.add("https://i.imgur.com/1TBQJlK.jpg");
        imgurUrls.add("https://i.imgur.com/avkzX5m.jpg");
        imgurUrls.add("https://i.imgur.com/PG15UCy.jpg");
        imgurUrls.add("https://i.imgur.com/O25v4ZF.jpg");
        imgurUrls.add("https://i.imgur.com/HL7uWFf.jpg");
        imgurUrls.add("https://i.imgur.com/OgL2gYD.jpg");
        imgurUrls.add("https://i.imgur.com/iMbpo41.jpg");
        imgurUrls.add("https://i.imgur.com/GmbRLc5.jpg");
        imgurUrls.add("https://i.imgur.com/xsmz7IX.jpg");
        imgurUrls.add("https://i.imgur.com/tFlYY0j.jpg");
        imgurUrls.add("https://i.imgur.com/epDkkj1.jpg");


        for (Integer cnt = 0; cnt < numOfCook; cnt += 1)
        {
            Random r = new Random();
            Double ratingValue = ratingMin + (ratingMax - ratingMin) * r.nextDouble();
            Integer imgurUrlNum = abs(r.nextInt()) % imgurUrls.size();
            Eater eater = new Eater("Fake" + cnt, ratingValue, imgurUrls.get(imgurUrlNum));

            eaterDao.save(eater);
        }

        return;
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

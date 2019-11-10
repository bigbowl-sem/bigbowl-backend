package edu.cmu.bigbowl.Dao;


import edu.cmu.bigbowl.Entity.Cook;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CookDao extends MongoRepository<Cook, String>{
    List<Cook> findByLocationNear(Point location, Distance distance);
    List<Cook> findByLocationNearAndCuisineContains(Point location, Distance distance, String cuisine);
    List<Cook> findByLocationNearAndCuisineContainsAndAvgPriceBetween(Point location, Distance distance, String cuisine, Double pMin, Double pMax);
    List<Cook> findByLocationNearAndCuisineContainsAndRatingBetween(Point location, Distance distance, String cuisine, Double rMin, Double rMax);
    List<Cook> findByLocationNearAndCuisineContainsAndAvgPriceBetweenAndRatingBetween(Point location, Distance distance, String cuisine, Double pMin, Double pMax, Double rMin, Double rMax);
}

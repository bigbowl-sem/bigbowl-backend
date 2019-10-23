package edu.cmu.bigbowl.Dao;

import edu.cmu.bigbowl.Entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDao extends MongoRepository<Review, String> {
}

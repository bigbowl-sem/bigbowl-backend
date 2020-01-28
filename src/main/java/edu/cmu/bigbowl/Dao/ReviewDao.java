package edu.cmu.bigbowl.Dao;

import edu.cmu.bigbowl.Entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewDao extends MongoRepository<Review, String> {
    List<Review> findReviewsByEaterId(String eaterId);

    List<Review> findReviewsByCookId(String cookId);

    List<Review> findReviewsByOrderId(String orderId);

}

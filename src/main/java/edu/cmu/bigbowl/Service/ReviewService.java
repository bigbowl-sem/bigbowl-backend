package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.ReviewDao;
import edu.cmu.bigbowl.Entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    // Create
    public Review postReview(Review review) {
        return reviewDao.save(review);
    }

    // Read
    public Collection<Review> getAllReviews() {
        return reviewDao.findAll();
    }

    public Optional<Review> getReviewById(String id) {
        return reviewDao.findById(id);
    }

    // Update
    public Optional<Review> updateReviews(Review review) {
        if (review.getReviewId() != null) {
            return updateReviewById(review.getReviewId(), review);
        }
        else{
            return null;
        }
    }

    public Optional<Review> updateReviewById(String id, Review review) {
        Optional<Review> optEater = reviewDao.findById(id);
        // TODO: 10/22/19
        // Right now it will save with the latest JSON which it's Id matched. But won't update
        // accordingly.
        if (review.getOrderId() != null) {
            optEater.ifPresent(theEater -> theEater.setOrderId(review.getOrderId()));
        }
        optEater.ifPresent(theEater -> reviewDao.save(theEater));
        return optEater;
    }

    // Delete
    public Optional<Review> deleteReview(Review review) {
        Optional<Review> optReview = reviewDao.findById(review.getReviewId());
        optReview.ifPresent(theReview -> reviewDao.delete(theReview));
        return optReview;
    }

    public Optional<Review> deleteReviewById(String id) {
        Optional<Review> optReview = reviewDao.findById(id);
        optReview.ifPresent(theReview -> reviewDao.delete(theReview));
        return optReview;
    }

    public void deleteAccounts() {
        reviewDao.deleteAll();
    }
}

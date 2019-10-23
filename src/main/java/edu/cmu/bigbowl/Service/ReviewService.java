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
        Optional<Review> optReview = reviewDao.findById(id);
        /*if (review.getRating() != null) {
            optReview.ifPresent(theReview -> theReview.setRating(review.getRating()));
        }*/
        optReview.ifPresent(theReview -> reviewDao.save(theReview));
        return optReview;
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

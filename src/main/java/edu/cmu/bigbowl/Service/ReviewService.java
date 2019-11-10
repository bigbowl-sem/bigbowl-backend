package edu.cmu.bigbowl.Service;

import edu.cmu.bigbowl.Dao.CookDao;
import edu.cmu.bigbowl.Dao.ReviewDao;
import edu.cmu.bigbowl.Entity.Cook;
import edu.cmu.bigbowl.Entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ReviewService {

    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private CookDao cookDao;

    // Create
    public Review postReview(Review review) {
        return reviewDao.save(review);
    }

    public void postFakeReview() {
        Random r = new Random();
        Integer numOfReview = 50;
        Double ratingMin = 0.0;
        Double ratingMax = 5.0;
        for (Integer cnt = 0; cnt < numOfReview; cnt += 1)
        {
            Double ratingValue = ratingMin + (ratingMax - ratingMin) * r.nextDouble();
            Review review = new Review( "Fake" + cnt, null, "Fake" + (cnt + 1), "Fake" + cnt, "Fake" + cnt + " is rated " + ratingValue, ratingValue);
            // update cook rating and totalRated
            Optional<Cook> optCook = cookDao.findById("Fake" + cnt);
            optCook.ifPresent(theCook -> theCook.setRating( ((theCook.getRating() * theCook.getTotalRated()) + ratingValue) / (theCook.getTotalRated() + 1)) );
            optCook.ifPresent(theCook -> theCook.setTotalRated(theCook.getTotalRated() + 1));
            optCook.ifPresent(theCook -> cookDao.save(theCook));
            reviewDao.save(review);
        }

        return;
    }

    // Read
    public Collection<Review> getAllReviews() {
        return reviewDao.findAll();
    }

    public Optional<Review> getReviewById(String id) {
        return reviewDao.findById(id);
    }

    public List<Review> getReviewByEaterId(String eaterId) {
        return reviewDao.findReviewsByEaterId(eaterId);
    }

    public List<Review> getReviewByCookId(String cookId) {
        return reviewDao.findReviewsByCookId(cookId);
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

    public void deleteReviews() {
        reviewDao.deleteAll();
    }
}

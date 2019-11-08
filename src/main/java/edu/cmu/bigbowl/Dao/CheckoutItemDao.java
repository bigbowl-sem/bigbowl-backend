package edu.cmu.bigbowl.Dao;


import edu.cmu.bigbowl.Entity.CheckoutItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutItemDao extends MongoRepository<CheckoutItem, String> {
}

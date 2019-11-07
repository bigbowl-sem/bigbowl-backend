package edu.cmu.bigbowl.Dao;


import edu.cmu.bigbowl.Entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends MongoRepository<Cart, String> {
}

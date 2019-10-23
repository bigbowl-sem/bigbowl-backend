package edu.cmu.bigbowl.Dao;


import edu.cmu.bigbowl.Entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends MongoRepository<Order, String> {
}

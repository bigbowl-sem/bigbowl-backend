package edu.cmu.bigbowl.Dao;


import edu.cmu.bigbowl.Entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao extends MongoRepository<Order, String> {
    List<Order> findOrdersByCookId(String cookId);
    List<Order> findOrdersByEaterId(String eaterId);
}

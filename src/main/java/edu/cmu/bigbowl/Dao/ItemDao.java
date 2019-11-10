package edu.cmu.bigbowl.Dao;


import edu.cmu.bigbowl.Entity.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemDao extends MongoRepository<Item, String> {
    List<Item> findItemsByCuisine(String cuisine);
    List<Item> findItemsByCookId(String cookId);
}

package edu.cmu.bigbowl.Dao;


import edu.cmu.bigbowl.Entity.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDao extends MongoRepository<Item, String> {
}

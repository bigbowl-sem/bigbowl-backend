package edu.cmu.bigbowl.Dao;


import edu.cmu.bigbowl.Entity.Menu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao extends MongoRepository<Menu, String> {
}

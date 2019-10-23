package edu.cmu.bigbowl.Dao;


import edu.cmu.bigbowl.Entity.Cook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookDao extends MongoRepository<Cook, String> {
}

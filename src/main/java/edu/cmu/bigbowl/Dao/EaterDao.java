package edu.cmu.bigbowl.Dao;


import edu.cmu.bigbowl.Entity.Eater;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EaterDao extends MongoRepository<Eater, String> {
}

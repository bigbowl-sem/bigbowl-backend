package edu.cmu.bigbowl.Dao;

import edu.cmu.bigbowl.Entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDao extends MongoRepository<Image, String> {
}

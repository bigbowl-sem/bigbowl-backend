package edu.cmu.bigbowl.Dao;

import edu.cmu.bigbowl.Entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountDao extends MongoRepository<Account, String> {
    Optional<Account> findByEaterId(String id);

}

package net.divs.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.divs.journalApp.entity.User;
import java.util.Optional;


public interface UserRepository extends MongoRepository<User,ObjectId> {
    User findByusername(String username);

    void deleteByusername(String username);

    Optional<User> findByEmail(String email);
}

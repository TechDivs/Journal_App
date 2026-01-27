package net.divs.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.divs.journalApp.entity.User;

public interface UserRepository extends MongoRepository<User,ObjectId> {
    User findByusername(String username);
}

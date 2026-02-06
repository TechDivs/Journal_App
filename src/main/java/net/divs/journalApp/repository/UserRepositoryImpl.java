package net.divs.journalApp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import net.divs.journalApp.entity.User;

@Repository
public class UserRepositoryImpl {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserforSA() {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").ne(null));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        return mongoTemplate.find(query, User.class);
    }
}

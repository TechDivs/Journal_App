package net.divs.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.divs.journalApp.entity.ConfigJournalAppEntity;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity,ObjectId>{

}

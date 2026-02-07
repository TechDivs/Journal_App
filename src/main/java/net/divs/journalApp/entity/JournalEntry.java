package net.divs.journalApp.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.Setter;
import net.divs.journalApp.enums.Sentiment;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Getter
@Setter
public class JournalEntry {
    @Id
    private ObjectId id;
    @Nonnull
    private String title;
    private String content;
    private LocalDateTime date;
    private Sentiment sentiment;    
}

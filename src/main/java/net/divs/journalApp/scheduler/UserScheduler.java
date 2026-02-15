package net.divs.journalApp.scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.divs.journalApp.entity.JournalEntry;
import net.divs.journalApp.entity.User;
import net.divs.journalApp.enums.Sentiment;
import net.divs.journalApp.repository.UserRepositoryImpl;
import net.divs.journalApp.service.EmailService;

@Component
public class UserScheduler {
    // private UserRepositoryImpl userRepositoryImpl;
    // private EmailService emailService;

    // public UserScheduler(UserRepositoryImpl userRepositoryImpl,EmailService emailService) {
    //     this.userRepositoryImpl = userRepositoryImpl;
    //     this.emailService = emailService;
    // }


    // @Scheduled(cron = "0 0 9 * * SUN")
    // public void fetchUserAndSendSaMail() {
    //     List<User> users = userRepositoryImpl.getUserforSA();

    //     for(User user:users) {
    //         List<JournalEntry> journalEntries = user.getJournalEntries();
    //         List<Sentiment> sentiments = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment()).collect(Collectors.toList());
    //         Map<Sentiment, Integer> sentimentCounts = new HashMap<>();
    //         for (Sentiment sentiment : sentiments) {
    //             if (sentiment != null)
    //                 sentimentCounts.put(sentiment, sentimentCounts.getOrDefault(sentiment, 0) + 1);
    //         }
    //         Sentiment mostFrequentSentiment = null;
    //         int maxCount = 0;
    //         for (Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet()) {
    //             if (entry.getValue() > maxCount) {
    //                 maxCount = entry.getValue();
    //                 mostFrequentSentiment = entry.getKey();
    //             }
    //         }
    //         if (mostFrequentSentiment != null) {
    //             emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days ", mostFrequentSentiment.toString());
    //         }
    //     }
    // }
}

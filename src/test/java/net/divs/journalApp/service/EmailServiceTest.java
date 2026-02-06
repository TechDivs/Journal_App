package net.divs.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @Test
    void testMail() {
        emailService.sendEmail("mrunmayik1920@gmail.com", "Learning SpringBoot", "Hi! Bade log bane gae bhai. SpringBoot se mail 🥳");
    }
}

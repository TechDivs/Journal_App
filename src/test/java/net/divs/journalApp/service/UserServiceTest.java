package net.divs.journalApp.service;


import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.divs.journalApp.repository.UserRepository;



@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    
    @ParameterizedTest
    @ValueSource(strings = {
        "Divyansh",
        "Divs",
        "TEMP"
    })
    public void testFindByUserName(String name) {
        assertNotNull(userRepository.findByusername(name));
    }
}

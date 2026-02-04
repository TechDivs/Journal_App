package net.divs.journalApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.divs.journalApp.entity.User;
import net.divs.journalApp.repository.UserRepository;
import net.divs.journalApp.service.QuoteService;
import net.divs.journalApp.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private QuoteService quoteService;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // @GetMapping("")    
    // public List<User> getAllUsers() {
    //     return userService.getAll();
    // }
    

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUserName(username);

        if(userInDb!=null) {
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
        }

        return new ResponseEntity<>(userInDb,HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByusername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();


        return new ResponseEntity<>("Hi " + username + ", Quote is: " + quoteService.getQuote(), HttpStatus.OK);
    }
    
}

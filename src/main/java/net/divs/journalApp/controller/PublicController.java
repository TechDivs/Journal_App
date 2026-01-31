package net.divs.journalApp.controller;

import org.springframework.web.bind.annotation.RestController;

import net.divs.journalApp.entity.User;
import net.divs.journalApp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/public")
public class PublicController {
    
    @GetMapping("/health-check")
    public String healthCheck() {
        return "Ok";
    }

    @Autowired
    private UserService userService;
    
    @PostMapping("/create-user")
    public void createUser(@RequestBody User entity) {
        userService.saveEntry(entity);
    }
}

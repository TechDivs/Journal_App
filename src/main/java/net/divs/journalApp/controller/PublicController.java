package net.divs.journalApp.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.divs.journalApp.entity.User;
import net.divs.journalApp.service.UserDetailsServiceImpl;
import net.divs.journalApp.service.UserService;
import net.divs.journalApp.utils.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {
    
    @GetMapping("/health-check")
    public String healthCheck() {
        return "Ok";
    }

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/signup")
    public void signup(@RequestBody User entity) {
        userService.saveNewUser(entity);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody User entity) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(entity.getUsername(), entity.getPassword()));
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(entity.getUsername());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt,HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occured: ",e);
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}

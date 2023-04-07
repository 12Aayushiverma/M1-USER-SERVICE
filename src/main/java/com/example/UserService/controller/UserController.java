package com.example.UserService.controller;

import com.example.UserService.entities.User;
import com.example.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;
    

    private final static Logger log = LoggerFactory.getLogger(UserController.class);


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        log.info("UserController::createUser===START ");
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }


    @GetMapping("/{userId}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Integer userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }
}

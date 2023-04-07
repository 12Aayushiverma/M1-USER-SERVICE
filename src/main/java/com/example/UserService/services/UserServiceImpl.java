package com.example.UserService.services;

import com.example.UserService.controller.UserController;
import com.example.UserService.entities.Ratings;
import com.example.UserService.entities.User;
import com.example.UserService.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {
        return userRepository.save(user) ;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(Integer userId) {
        User use = new User();
        Optional<User> user = userRepository.findById(userId);
        ArrayList<Ratings> ratingOfUser = restTemplate.getForObject("http://localhost:8087/rating/users/5", ArrayList.class);
       log.info("{} ",ratingOfUser);
       use.setRatings(ratingOfUser);
        return user;
    }

    @Override
    public Object updateUser(User user) {
        return userRepository.save(user);
    }
}

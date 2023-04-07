package com.example.UserService.services;

import com.example.UserService.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService<T> {



    User saveUser(User user);

    List<User> getAllUsers();

    Optional<User> getUser(Integer userId);

    public T updateUser(User user);

}

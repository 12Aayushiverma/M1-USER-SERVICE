package com.example.UserService.services;

import com.example.UserService.entities.User;
import com.example.UserService.model.response.CommonResponse;

import java.util.List;

public interface UserService<T> {



    CommonResponse saveUser(User user);

    List<User> getAllUsers();

    CommonResponse getUser(Integer userId);

    CommonResponse updateUser(User user);


}

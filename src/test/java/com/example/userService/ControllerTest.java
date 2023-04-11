package com.example.userService;

import com.example.UserService.controller.UserController;
import com.example.UserService.entities.User;
import com.example.UserService.model.response.CommonResponse;
import com.example.UserService.services.UserService;
import com.example.UserService.utils.Constants;
import com.example.UserService.utils.Messages;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class ControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;

     @Test
    @Order(1)
    public void createUserTest() {
        CommonResponse cmn = new CommonResponse();
        cmn.setMessage(Messages.SUCCESS_MSG);
        cmn.setStatusCode(Constants.SUCCESS_CD);
       User user = new User();
       user.setId(25);
       user.setFirstName(" Aayush");
       user.setLastName("Joshi");
       user.setMobileNumber("5123456231");
       user.setEmail("dgvcb45@gmail.com");
       user.setAbout("I am doctor");

        Mockito.when(userService.saveUser(user)).thenReturn(cmn);
        ResponseEntity<CommonResponse> responseEntity = userController.createUser(user);
        CommonResponse result = (CommonResponse) responseEntity.getBody();
        Assert.assertEquals(Constants.SUCCESS_CD, result.getStatusCode());


    }

    @Test
    @Order(2)
    public  void getUserTest(){


        CommonResponse cmn = new CommonResponse();
        List<User> list = new ArrayList<>();
        list.add(new User(4,"Aayushi","Verma","4512351262","ajhbb@gmail.com","I am user"));
        cmn.setData(list);
        cmn.setMessage(Messages.SUCCESS_MSG);
        cmn.setStatusCode(Constants.SUCCESS_CD);

        Mockito.when(userService.getUser(4)).thenReturn( cmn);
        ResponseEntity<CommonResponse> responseEntity = userController.getAllUsers();
        CommonResponse result = (CommonResponse) responseEntity.getBody();
        List<User> resultList = new ArrayList<>();
        resultList = (List) result.getData();
        Assert.assertEquals(Messages.SUCCESS_MSG, result.getMessage());


    }

    @Test
    @Order(3)
    public  void getAllUserTest(){
        CommonResponse cmn = new CommonResponse();
        List<User> userList = new ArrayList<>();
        userList.add(new User(4,"Aayushi","Verma","4512351262","ajhbb@gmail.com","I am user"));
        userList.add(new User(5,"Aayush","sharma","6412351262","dgfbb@gmail.com","I am second user"));


        cmn.setData(userList);
        cmn.setMessage(Messages.SUCCESS_MSG);
        cmn.setStatusCode(Constants.SUCCESS_CD);
        Mockito.when(userService.getAllUsers()).thenReturn((List) cmn);
        ResponseEntity<CommonResponse> responseEntity = userController.getAllUsers();
        CommonResponse result = (CommonResponse) responseEntity.getBody();
        List<User> resultList = new ArrayList<>();
        resultList = (List) result.getData();
        Assert.assertEquals(Messages.SUCCESS_MSG, result.getMessage());

    }

    }



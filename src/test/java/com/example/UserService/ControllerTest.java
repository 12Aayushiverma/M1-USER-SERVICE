package com.example.UserService;

import com.example.UserService.controller.UserController;
import com.example.UserService.entities.User;
import com.example.UserService.services.UserService;
import org.junit.jupiter.api.Order;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class ControllerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;

    // @Test
    @Order(1)
    public void createUserTest() {


       User user = new User();
       user.setId(25);
       user.setFirstName("Ayush");
       user.setLastName("Joshi");
       user.setMobileNumber("5123456231");
       user.setEmail("dgvcb45@gmail.com");
       user.setAbout("I am doctor");


        //Mockito.when(userService.saveUser(user)).thenReturn();
        ResponseEntity<Object> responseEntity = userController.createUser(user);



    }
}

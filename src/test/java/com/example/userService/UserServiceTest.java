package com.example.userService;

import com.example.UserService.entities.User;
import com.example.UserService.model.response.CommonResponse;
import com.example.UserService.repositories.UserRepository;
import com.example.UserService.services.UserService;
import com.example.UserService.utils.Constants;
import com.example.UserService.utils.Messages;
import com.netflix.discovery.converters.Auto;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void saveUserTest(){

 User user = new User();
        CommonResponse cmn = new CommonResponse();
        user.setId(25);
        user.setFirstName(" Aayush");
        user.setLastName("Joshi");
        user.setMobileNumber("5123456231");
        user.setEmail("dgvcb45@gmail.com");
        user.setAbout("I am doctor");

        cmn.setMessage(Messages.SUCCESS_MSG);
        cmn.setStatusCode(Constants.ERROR_CD);

        Mockito.when(userRepository.save(user)).thenReturn((User) cmn);
        Assert.assertEquals(Constants.SUCCESS_CD, cmn.getStatusCode());






    }
}

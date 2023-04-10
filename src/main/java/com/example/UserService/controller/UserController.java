package com.example.UserService.controller;

import com.example.UserService.entities.Ratings;
import com.example.UserService.entities.User;
import com.example.UserService.exception.ResourseNotFoundException;
import com.example.UserService.model.HelperClass;
import com.example.UserService.model.response.CommonResponse;
import com.example.UserService.services.UserService;
import com.example.UserService.utils.Constants;
import com.example.UserService.utils.Messages;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController<T> {


    @Autowired
    private UserService userService;
    

    private final static Logger log = LoggerFactory.getLogger(UserController.class);


    @PostMapping
    public ResponseEntity<CommonResponse> createUser(@RequestBody User user) {
        log.info("UserController::createUser===START ");
        CommonResponse cmn = new CommonResponse();
        try {

            log.info("UserController::createUser::getUsers() ");
            cmn = (CommonResponse) userService.saveUser(user);

            log.info("UserController::createUser===END ");
            return ResponseEntity.status(HttpStatus.OK).body(cmn);
        } catch (Exception e) {

            log.error("UserController::createUser===EXCEPTION= {} ", e.getStackTrace());
            cmn.setMessage(Messages.SOMETHING_WENT_WRONG);
            cmn.setStatusCode(Constants.ERROR_CD);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmn);}




    }

    @GetMapping("/{userId}")
    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<T> getUser(@PathVariable Integer userId) {
        log.info("UserController::getUser===START ");
        CommonResponse cmn = new CommonResponse();
        try {

            log.info("UserController::getUser::getUser() ");
            cmn = (CommonResponse) userService.getUser(userId);

            log.info("UserController::getUser===END ");
            return ResponseEntity.status(HttpStatus.OK).body((T) cmn);
        } catch (Exception e) {

            log.error("UserController::getUser===EXCEPTION= {} ", e.getStackTrace());
            cmn = HelperClass.getCommonExceptionResoponse();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((T) cmn);
        }

    }


   public ResponseEntity<User> ratingHotelFallback(Integer userId, Exception ex){
        log.info("fallback is executed because service is down : ", ex.getMessage());

        User user = new User();
        user.setId(112);
        user.setFirstName("Dummy");
        user.setLastName("name");
        user.setEmail("dummy@gmail.com");
        user.setMobileNumber("0000000000");
        user.setAbout("I am dummy user");
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<CommonResponse> getAllUsers(){
        log.info("UserController::getAllUsers===START ");
        CommonResponse cmn = new CommonResponse();
        try {

            log.info("UserController::getAllUsers::getUsers() ");
            cmn = (CommonResponse) userService.getAllUsers();

            log.info("UserController::getAllUser===END ");
            return  ResponseEntity.status(HttpStatus.OK).body(cmn);
        } catch (Exception e) {

            log.error("UserController::getAllUser===EXCEPTION= {} ", e.getStackTrace());
            cmn = HelperClass.getCommonExceptionResoponse();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((CommonResponse) cmn);
        }

    }

    public static void main(String[] args) {

        List<Ratings> list = Arrays.asList(new Ratings(15,4,5,8,"Nice envirnoment"));
       CommonResponse cmn = new CommonResponse();
         cmn.setData(list);
//      List<Ratings> list2 = (List<Ratings>) cmn.getData();
//      System.out.println(list2);

       List<Ratings> rr = (List<Ratings>) cmn.getData().toString();
    }
}

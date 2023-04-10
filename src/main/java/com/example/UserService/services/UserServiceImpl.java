package com.example.UserService.services;

import com.example.UserService.entities.Ratings;
import com.example.UserService.entities.User;
import com.example.UserService.exception.ResourseNotFoundException;
import com.example.UserService.external.HotelService;
import com.example.UserService.model.HelperClass;
import com.example.UserService.model.response.CommonResponse;
import com.example.UserService.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserServiceImpl<T> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public CommonResponse saveUser(User user) {
        log.info("UserServiceImpl::saveUser::Start");
        CommonResponse cmn = new CommonResponse();
        try {
            log.info("UserServiceImpl::saveUser()::save()");
            userRepository.save(user);
            cmn.setMessage("User Sved Successfully");
            cmn.setStatusCode(200);
            log.info("UserServiceImpl::saveUser()::save()");
            return cmn;
        }
        catch (Exception e) {
            log.error("UserServiceImpl::saveUser():: Exception={}", e.getStackTrace());
            return HelperClass.getCommonExceptionResoponse();

        }

    }

    @Override
    public List<User> getAllUsers() {
        log.info("UserServiceImpl::getAllUsers()=== START");
        CommonResponse cmn = new CommonResponse();

        try {


            log.info("UserServiceImpl::getAllUsers()::findAll()");
            List<User> users = userRepository.findAll();
            cmn.setData(users);
            log.info("UserServiceImpl::getUsers()=== END");
            return (List<User>) cmn;
        } catch (Exception e) {
            log.error("UserServiceImpl::getUsers():: Exception={}", e.getStackTrace());
            return (List<User>) HelperClass.getCommonExceptionResoponse();

        }
    }

    @Override
    public CommonResponse getUser(Integer userId) {
        log.info("UserServiceImpl::getUser()=== START");
        CommonResponse cmn = new CommonResponse();

        try {

            User user = new User();
            user = userRepository.findById(userId).orElseThrow(() -> new ResourseNotFoundException("user not found with given id" + userId));
            cmn = restTemplate.getForObject("http://localhost:8087/rating/users/5", CommonResponse.class);
            List<Ratings> ratings = (List<Ratings>) cmn.getData();
            user.setRatings(ratings);
            cmn.setData(user);
            cmn.setMessage("User found successfully");
            cmn.setStatusCode(200);
            return cmn;

        }catch (Exception ex){
            log.error("UserServiceImpl::getUsers():: Exception={}", ex.getStackTrace());
            return (CommonResponse) HelperClass.getCommonExceptionResoponse();
        }
      /*  log.info("{} ",ratingOfUser);
        List<Ratings> ratings = Arrays.stream(ratingOfUser).toList();
        List<Ratings> ratingList = ratings.stream().map(rating-> {
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;m
        }).collect(Collectors.toList());
*/

    }

    @Override
    public CommonResponse updateUser(User user) {

        log.info("UserServiceImpl::updateUser()=== START");
        CommonResponse cmn = new CommonResponse();
       cmn= userRepository.save(user);
       return cmn;
    }
}

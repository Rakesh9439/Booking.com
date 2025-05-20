package com.Booking.com.controller;


import com.Booking.com.dto.UserSignupDto;
import com.Booking.com.entity.UserSignup;
import com.Booking.com.service.UserSignupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserSignupController {

    private UserSignupService userSignupService;

    public UserSignupController(UserSignupService userSignupService) {
        this.userSignupService = userSignupService;
    }


    // Endpoint for user signup
    // Base URL: localhost:8083/api/v1/signup

    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(@RequestBody UserSignupDto userSignupDto){

        UserSignup userSignup = userSignupService.createUserSignup(userSignupDto);
        if (userSignup != null){
            return new ResponseEntity("Registration is successful", HttpStatus.CREATED);
        }
        return new ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

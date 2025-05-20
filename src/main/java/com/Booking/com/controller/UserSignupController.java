package com.Booking.com.controller;


import com.Booking.com.dto.LoginDto;
import com.Booking.com.dto.TokenResponse;
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
    // Base URL: http://localhost:8083/api/v1/signup

    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(@RequestBody UserSignupDto userSignupDto){

        UserSignup userSignup = userSignupService.createUserSignup(userSignupDto);
        if (userSignup != null){
            return new ResponseEntity("Registration is successful", HttpStatus.CREATED);
        }
        return new ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }





    // Endpoint for user login
    // Base URL: http://localhost:8083/api/v1/login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        String token = userSignupService.verifyLogin(loginDto);
        if (token!= null){
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(token);
            return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid crdentials", HttpStatus.UNAUTHORIZED);
    }
}

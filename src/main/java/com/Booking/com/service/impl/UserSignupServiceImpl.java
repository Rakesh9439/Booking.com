package com.Booking.com.service.impl;

import com.Booking.com.component.UserSignupMapper;
import com.Booking.com.dto.LoginDto;
import com.Booking.com.dto.UserSignupDto;
import com.Booking.com.entity.UserSignup;
import com.Booking.com.repository.UserSignupRepository;
import com.Booking.com.service.JWTService;
import com.Booking.com.service.UserSignupService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSignupServiceImpl implements UserSignupService {


    private UserSignupRepository userSignupRepository;
    private UserSignupMapper userSignupMapper;
    private JWTService jwtService;



    public UserSignupServiceImpl(UserSignupRepository userSignupRepository, UserSignupMapper userSignupMapper, JWTService jwtService) {
        this.userSignupRepository = userSignupRepository;
        this.userSignupMapper = userSignupMapper;
        this.jwtService = jwtService;
    }

    @Override
    public UserSignup createUserSignup(UserSignupDto userSignupDto) {

        // Convert UserSignupDto to UserSignup entity
        UserSignup userSignup = new UserSignup();
        userSignup.setFirstName(userSignupDto.getFirstName());
        userSignup.setLastName(userSignupDto.getLastName());
        userSignup.setUsername(userSignupDto.getUsername());
        userSignup.setEmail(userSignupDto.getEmail());
        userSignup.setCountry(userSignupDto.getCountry());
        userSignup.setCity(userSignupDto.getCity());
        userSignup.setAddress(userSignupDto.getAddress());
        userSignup.setPostalCode(userSignupDto.getPostalCode());
        userSignup.setPhone(userSignupDto.getPhone());
        userSignup.setUserRole(userSignupDto.getUserRole());
        userSignup.setPassword(BCrypt.hashpw(userSignupDto.getPassword(), BCrypt.gensalt(10)));

        //   Save the user signup entity
        UserSignup savedUserSignup = userSignupRepository.save(userSignup);
        return savedUserSignup;


    }

    @Override
    public String verifyLogin(LoginDto loginDto) {
        Optional<UserSignup> opUser = userSignupRepository.findByUsername(loginDto.getUsername());
        if (opUser.isPresent()){
            UserSignup userSignup = opUser.get();
            if (BCrypt.checkpw(loginDto.getPassword(), userSignup.getPassword())){
                return jwtService.generateToken(userSignup);
            }
        }
        return null;
    }
}

package com.Booking.com.service.impl;

import com.Booking.com.component.UserSignupMapper;
import com.Booking.com.dto.UserSignupDto;
import com.Booking.com.entity.UserSignup;
import com.Booking.com.repository.UserSignupRepository;
import com.Booking.com.service.UserSignupService;
import org.springframework.stereotype.Service;

@Service
public class UserSignupServiceImpl implements UserSignupService {


    private UserSignupRepository userSignupRepository;
    private UserSignupMapper userSignupMapper;

    public UserSignupServiceImpl(UserSignupRepository userSignupRepository, UserSignupMapper userSignupMapper) {
        this.userSignupRepository = userSignupRepository;
        this.userSignupMapper = userSignupMapper;
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
        userSignup.setPassword(userSignupDto.getPassword());

        //   Save the user signup entity
        UserSignup savedUserSignup = userSignupRepository.save(userSignup);
        return savedUserSignup;


    }
}

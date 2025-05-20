package com.Booking.com.service;

import com.Booking.com.dto.LoginDto;
import com.Booking.com.dto.UserSignupDto;
import com.Booking.com.entity.UserSignup;

public interface UserSignupService {

    UserSignup createUserSignup(UserSignupDto userSignupDto);

    String verifyLogin(LoginDto loginDto);
}

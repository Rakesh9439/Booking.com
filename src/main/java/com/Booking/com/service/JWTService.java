package com.Booking.com.service;

import com.Booking.com.entity.UserSignup;

public interface JWTService {

    String generateToken(UserSignup userSignup);

    String getUsername(String token);
}

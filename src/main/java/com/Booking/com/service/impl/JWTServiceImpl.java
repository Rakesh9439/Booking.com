package com.Booking.com.service.impl;

import com.Booking.com.entity.UserSignup;
import com.Booking.com.service.JWTService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTServiceImpl implements JWTService {


    @Value("${jwt.algorithms.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiryTime;

    private Algorithm algorithm;
    private static final String USER_NAME = "userName";

    @PostConstruct
    public void postConstruct() {
        algorithm = Algorithm.HMAC256(algorithmKey);
    }
    @Override
    public String generateToken(UserSignup userSignup) {

        return JWT.create()
                .withClaim(USER_NAME, userSignup.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);



    }

    @Override
    public String getUsername(String token) {
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token);

        return decodedJWT.getClaim(USER_NAME).asString();
    }
}

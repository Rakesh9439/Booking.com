package com.Booking.com.repository;

import com.Booking.com.entity.UserSignup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSignupRepository extends JpaRepository<UserSignup, Long> {
}
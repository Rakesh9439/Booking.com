package com.Booking.com.component;

import com.Booking.com.entity.UserSignup;
import com.Booking.com.repository.UserSignupRepository;
import com.Booking.com.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private UserSignupRepository userSignupRepository;

    public JWTRequestFilter(JWTService jwtService, UserSignupRepository userSignupRepository) {
        this.jwtService = jwtService;
        this.userSignupRepository = userSignupRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            String token = tokenHeader.substring(8, tokenHeader.length() - 1);
            String username = jwtService.getUsername(token);
            Optional<UserSignup> opUser = userSignupRepository.findByUsername(username);
            if (opUser.isPresent()) {
                UserSignup user = opUser.get();

                System.out.println(user);
                // To keep track of current user logged in


                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
                        Collections.singleton(new SimpleGrantedAuthority(user.getUserRole())));
                authentication.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
            System.out.println(token);
        }
        filterChain.doFilter(request, response);
    }
}

package com.dev.Laboratory.security.sign.service;



import com.dev.Laboratory.Repo.UserRepo;
import com.dev.Laboratory.model.User;
import com.dev.Laboratory.security.jwt.JwtUtils;
import com.dev.Laboratory.security.jwt.UserDetailsImpl;
import com.dev.Laboratory.security.sign.dto.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    public boolean register(User user) {
        if(userRepo.existsByUsername(user.getUsername()))return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return true;
    }


    public String authenticate(LoginRequest loginRequest) {

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        if (authentication == null) {
            throw new BadCredentialsException("Invalid UserName or password");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails=(UserDetailsImpl) authentication.getPrincipal();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTJAZ21haWwuY29tIiwicm9sZXMiOiJST0xFX1VTRVIiLCJpYXQiOjE3NDcxNTIwNzYsImV4cCI6MTc0NzE1NTY3Nn0.PM23Eq_QkKHeiyH94BgbQD4Nvf7hfzPWg6cgF4HwbcI";
        boolean isValid = jwtUtils.validateToken(token);
        System.out.println(isValid ? "Valid JWT Token" : "Invalid JWT Token");

        return   jwtUtils.generateToken(userDetails);


    }
}

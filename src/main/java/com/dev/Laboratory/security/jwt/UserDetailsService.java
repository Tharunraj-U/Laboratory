package com.dev.Laboratory.security.jwt;


import com.dev.Laboratory.Repo.UserRepo;
import com.dev.Laboratory.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

     @Autowired
     UserRepo userRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=  userRepo.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User Not Found"));

        return UserDetailsImpl.build(user);
    }
}

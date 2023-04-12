package com.example.user.location.problem.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
/**
 * Implementation of the UserDetailsService interface that provides hard-coded User objects for authentication.
 */
public class UserImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user1=new User("admin","$2a$10$bQIKynPFLnItFqXdZoK8P.0l0oDpzXtwPBM0vH54IUKDDma0R4SES",List.of("ADMIN"));
        User user2=new User("reader","$2a$10$bQIKynPFLnItFqXdZoK8P.0l0oDpzXtwPBM0vH54IUKDDma0R4SES",List.of("READER"));

        UserDetails userDetails=null;
        if(username.equals("admin")){
            userDetails=user1;
        }

        if(username.equals("reader")){
            userDetails=user2;
        }

        if(userDetails == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return userDetails;
    }
}


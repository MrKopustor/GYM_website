package com.teachmeskills.gym_website.service;

import com.teachmeskills.gym_website.config.UserDetailsConfig;
import com.teachmeskills.gym_website.entity.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.teachmeskills.gym_website.request.dataBase.request.general.GeneralRequest.findUserByUsername;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(findUserByUsername(email));
        return user.map(UserDetailsConfig::new)
                .orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
    }
}

package com.security.security.service;

import com.security.security.config.UserInfoUserDetails;
import com.security.security.entity.UserInfo;
import com.security.security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
   private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repo.findByName(username);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("User Not Found"));
    }
}

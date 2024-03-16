package com.LoginPage.LoginPage;

import com.LoginPage.LoginPage._1Entitiy.User;
import com.LoginPage.LoginPage._2Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//  it loads user based on given username / email

public class _7UserDetailServiceimpl implements UserDetailsService {
    // 8.

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.getUserByEmail(username);

        if(user == null){
            throw new UsernameNotFoundException("Could not found user");
        }

        _6CustomUserDetails customUserDetails = new _6CustomUserDetails(user);

        return customUserDetails;
    }
}

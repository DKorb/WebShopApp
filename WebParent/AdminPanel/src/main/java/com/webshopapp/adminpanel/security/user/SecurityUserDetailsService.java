package com.webshopapp.adminpanel.security.user;


import com.webshopapp.common.entity.user.User;
import com.webshopapp.adminpanel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class SecurityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        if (user != null) {
            return new SecurityUserDetails(user);
        }
        throw new UsernameNotFoundException("Could not find user with email  " + email);
    }
}

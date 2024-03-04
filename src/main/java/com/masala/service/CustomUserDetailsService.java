package com.masala.service;

import com.masala.enums.UserRoles;
import com.masala.repository.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.masala.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public CustomUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username);
        if(Objects.isNull(user)) {
            throw new UsernameNotFoundException("user not find with email " + username);
        }

        UserRoles userRoles = user.getRole();
        if(Objects.isNull(userRoles)) userRoles = UserRoles.CUSTOMER;
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(userRoles.name()));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
}

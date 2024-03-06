package com.masala.controllers;

import com.masala.config.JwtProvider;
import com.masala.dto.AuthResponse;
import com.masala.dto.LoginRequest;
import com.masala.entity.Cart;
import com.masala.entity.User;
import com.masala.enums.UserRoles;
import com.masala.exception.UserException;
import com.masala.repository.CartRepo;
import com.masala.repository.UserRepo;
import com.masala.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    CartRepo cartRepo;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserhandler(@RequestBody User user) {

        System.out.println("here u go !!");
        User userExist = userRepo.findByEmail(user.getEmail());
        if(Objects.nonNull(userExist)) {
            throw new UserException("User already exist!!!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        Cart cart = new Cart();
        cart.setCustomer(user);
        cartRepo.save(cart);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generrateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setRole(user.getRole());
        authResponse.setMessage("Register user success!!!");
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);

    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signIn(@RequestBody LoginRequest loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();


        Authentication authentication = authenticate(email,password);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roles = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();
        String jwt = jwtProvider.generrateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setRole(UserRoles.valueOf(roles));

//        authResponse.setRole(user.getRole());
        authResponse.setMessage("Register user success!!!");
        return new ResponseEntity<>(authResponse,HttpStatus.OK);

    }

    private Authentication authenticate(String email, String password) {

        UserDetails userDetails =customUserDetailsService.loadUserByUsername(email);

        if(Objects.isNull(userDetails)) {
            throw new BadCredentialsException("Bad Creadentials !!");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())) {
            throw new BadCredentialsException("Password doesn't Match !!");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}

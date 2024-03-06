package com.masala.controllers;

import com.masala.entity.User;
import com.masala.service.Impl.IUserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserServiceImpl userService;

    public UserController(IUserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<User> getUser(@RequestHeader("Authorization") String jwt) {

        return new ResponseEntity<>(userService.findUserByJwt(jwt), HttpStatus.OK);
    }
}

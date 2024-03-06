package com.masala.service;

import com.masala.entity.User;

public interface IUserService {

    User findUserByJwt(String jwt);

    User findUserByEmail(String email);
}

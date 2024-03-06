package com.masala.service.Impl;

import com.masala.config.JwtProvider;
import com.masala.entity.User;
import com.masala.repository.UserRepo;
import com.masala.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class IUserServiceImpl implements IUserService {

    private final UserRepo userRepo;
    private final JwtProvider jwtProvider;

    public IUserServiceImpl(UserRepo userRepo, JwtProvider jwtProvider) {
        this.userRepo = userRepo;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = jwtProvider.getEmaiFromJwt(jwt);
        return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}

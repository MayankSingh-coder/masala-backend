package com.masala.dto;

import com.masala.enums.UserRoles;
import com.masala.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String jwt;
    private String message;
    private UserRoles role;
}

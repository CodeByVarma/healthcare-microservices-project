package com.healthcare.authservice.mapper;

import com.healthcare.authservice.dto.AuthResponseDTO;
import com.healthcare.authservice.dto.RegisterRequestDTO;
import com.healthcare.authservice.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AccountMapper {


    public static User toUserEntity(RegisterRequestDTO registerRequestDTO, PasswordEncoder passwordEncoder) {
        User userEntity = new User();
        userEntity.setUsername(registerRequestDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        userEntity.setRole("PATIENT");
        return userEntity;
    }

    public static AuthResponseDTO toAuthRespDTO(String token) {
        AuthResponseDTO authResponseDTO=new AuthResponseDTO();
        authResponseDTO.setToken(token);
        return authResponseDTO;
    }
}

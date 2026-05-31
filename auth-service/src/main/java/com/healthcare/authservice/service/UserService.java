package com.healthcare.authservice.service;

import com.healthcare.authservice.dto.AuthResponseDTO;
import com.healthcare.authservice.dto.LoginRequestDTO;
import com.healthcare.authservice.dto.RegisterRequestDTO;
import com.healthcare.authservice.entity.User;
import com.healthcare.authservice.exception.ResourceNotFoundException;
import com.healthcare.authservice.mapper.AccountMapper;
import com.healthcare.authservice.repository.UserRepository;
import com.healthcare.authservice.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;


    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public String createAccount(RegisterRequestDTO registerRequestDTO) {
        User userEntity = AccountMapper.toUserEntity(registerRequestDTO, passwordEncoder);
        userRepo.save(userEntity);
        return "Account Registered Successfully";
    }

    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        } catch (AuthenticationException e) {
            throw new ResourceNotFoundException("Invalid Password");
        }
        User userEntity = userRepo.findByUsername(loginRequestDTO.getUsername()).orElseThrow(() ->
                new ResourceNotFoundException("Invalid Username"));
        String token = jwtUtil.generateToken(userEntity.getUsername(), userEntity.getRole());
        return AccountMapper.toAuthRespDTO(token);

    }
}

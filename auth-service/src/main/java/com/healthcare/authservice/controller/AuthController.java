package com.healthcare.authservice.controller;

import com.healthcare.authservice.dto.AuthResponseDTO;
import com.healthcare.authservice.dto.LoginRequestDTO;
import com.healthcare.authservice.dto.RegisterRequestDTO;
import com.healthcare.authservice.service.UserService;
import com.healthcare.authservice.util.JwtUtil;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    UserService userService;

    public AuthController(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return userService.createAccount(registerRequestDTO);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return userService.login(loginRequestDTO);
    }

    @GetMapping("/test")
    public String test(){
        return "Testing";
    }
}


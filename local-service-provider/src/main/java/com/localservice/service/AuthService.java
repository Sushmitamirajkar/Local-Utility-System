package com.localservice.service;

import com.localservice.config.JwtUtil;
import com.localservice.dto.DTOs.*;
import com.localservice.model.User;
import com.localservice.repository.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil, AuthenticationManager authManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
    }

    public AuthResponse register(@NonNull RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail()))
            throw new RuntimeException("Email already registered");

        User user = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .phone(req.getPhone())
                .address(req.getAddress())
                .role(req.getRole() != null ? req.getRole() : User.Role.USER)
                .build();

        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token, user.getRole().name(), user.getName(), user.getId());
    }

    public AuthResponse login(@NonNull LoginRequest req) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token, user.getRole().name(), user.getName(), user.getId());
    }
}

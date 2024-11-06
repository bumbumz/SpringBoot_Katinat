package com.phamcongvinh.testusser.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.phamcongvinh.testusser.dto.UserDto;
import com.phamcongvinh.testusser.enity.Cart;
import com.phamcongvinh.testusser.enity.Role;
import com.phamcongvinh.testusser.enity.Token;
import com.phamcongvinh.testusser.enity.User;
import com.phamcongvinh.testusser.mapper.UserMapper;
import com.phamcongvinh.testusser.model.AuthenticationRequest;
import com.phamcongvinh.testusser.model.AuthenticationResponse;
import com.phamcongvinh.testusser.model.RegisterRequest;
import com.phamcongvinh.testusser.repository.CartRepository;
import com.phamcongvinh.testusser.repository.TokenRepository;
import com.phamcongvinh.testusser.repository.UserRepository;
import com.phamcongvinh.testusser.service.AuthenticationService;

import lombok.RequiredArgsConstructor;
import java.util.Arrays;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    private final CartRepository cartRepository;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setEmail(request.getEmail());
        
        // Xử lý vai trò
        if (!isValidRole(request.getRole())) {
            throw new IllegalArgumentException("Invalid role");
        }
        
        Role userRole = Role.valueOf(request.getRole().toUpperCase());
        newUser.setRole(userRole);
        
        User createdUser = userRepository.save(newUser);
        String jwtToken = jwtService.generateToken(createdUser);
        Token token = Token.builder()
                .userId(createdUser.getUserId())
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);

        Cart cart =new Cart();
        cart.setCreatedAt(LocalDateTime.now()); 
        cart.setUser(createdUser);
        cartRepository.save(cart);
        
        return AuthenticationResponse.builder()
                .userDto(UserMapper.mapToUserDto(createdUser))
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        Token token = Token.builder()
                .userId(user.getUserId())
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
        UserDto userDto = UserMapper.mapToUserDto(user);
        return AuthenticationResponse.builder()
                .userDto(userDto)
                .token(jwtToken)
                .build();
    }

    private boolean isValidRole(String role) {
        return Arrays.stream(Role.values()).anyMatch(r -> r.name().equalsIgnoreCase(role));
    }
}


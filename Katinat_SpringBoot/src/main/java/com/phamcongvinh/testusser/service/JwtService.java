package com.phamcongvinh.testusser.service;

import com.phamcongvinh.testusser.enity.User;

public interface JwtService {
    String extractUsername(String token);
    String generateToken(User user);
}

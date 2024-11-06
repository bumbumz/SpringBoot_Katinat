package com.phamcongvinh.testusser.service;

import com.phamcongvinh.testusser.model.AuthenticationRequest;
import com.phamcongvinh.testusser.model.AuthenticationResponse;
import com.phamcongvinh.testusser.model.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(AuthenticationRequest request);
}
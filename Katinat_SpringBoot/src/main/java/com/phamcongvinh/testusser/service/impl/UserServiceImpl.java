package com.phamcongvinh.testusser.service.impl;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// import com.phamcongvinh.testusser.enity.Cart;
import com.phamcongvinh.testusser.enity.User;
// import com.phamcongvinh.testusser.repository.CartRepository;
import com.phamcongvinh.testusser.repository.UserRepository;
import com.phamcongvinh.testusser.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    // private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    // @Override
    // public User updateUser(Long userId, User user) {
    // User existingUser = userRepository.findById(userId).orElse(null);
    // if (existingUser != null) {
    // existingUser.setUsername(user.getUsername());
    // existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
    // existingUser.setEmail(user.getEmail());
    // existingUser.setFirstName(user.getFirstName());
    // existingUser.setLastName(user.getLastName());
    // return userRepository.save(existingUser);
    // }
    // return null;
    // }
    @Override
    public User updateUser(Long userId, User user) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            if (user.getUsername() != null) {
                existingUser.setUsername(user.getUsername());
            }
            if (user.getPassword() != null) {
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getFirstName() != null) {
                existingUser.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                existingUser.setLastName(user.getLastName());
            }
            return userRepository.save(existingUser);
        }
        return null;
    }

    @Override
    public Long getUserIdByEmailAndUsername(String email, String username) {
        User user = userRepository.findByEmailAndUsername(email, username);
        return user != null ? user.getUserId() : null;
    }

}

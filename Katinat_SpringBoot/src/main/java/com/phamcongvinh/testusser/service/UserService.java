package com.phamcongvinh.testusser.service;

import java.util.List;

import com.phamcongvinh.testusser.enity.User;

public interface UserService {
    User createUser(User user);

    

    List<User> getUsers();

    User getUserById(Long userId);

    User updateUser(Long userId, User user);

    Long getUserIdByEmailAndUsername(String email, String username);
}

package com.user.service.Services;

import com.user.service.Models.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();

    User getUserById(String userId);
}

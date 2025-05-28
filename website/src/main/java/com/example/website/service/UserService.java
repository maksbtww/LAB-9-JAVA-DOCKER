package com.example.website.service;

import com.example.website.entity.User;
import com.example.website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> authenticate(String username, String password) {
        Optional<User> user = userRepository.findOneByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }

    public void registerUser(String username, String password) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRole("user");
        userRepository.save(newUser);
    }

    public java.util.List<User> getAllActiveUsers() {
        return userRepository.findAllByDeletedFalse();
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id).filter(u -> !u.isDeleted());
    }

    public User updateUser(int id, String username, String password, String role) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (username != null) user.setUsername(username);
            if (password != null) user.setPassword(password);
            if (role != null) user.setRole(role);
            return userRepository.save(user);
        }
        throw new RuntimeException("User not found");
    }

    public void softDeleteUser(int id) {
        Optional<User> userOpt = userRepository.findById(id);
        userOpt.ifPresent(user -> {
            user.setDeleted(true);
            userRepository.save(user);
        });
    }

    public User addUser(String username, String password, String role) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRole(role);
        return userRepository.save(newUser);
    }
}
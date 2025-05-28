package com.example.website.api;

import com.example.website.entity.User;
import com.example.website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    @Autowired
    private UserRepository userRepository;
    //Все пользователи
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    //Добавить
    @PostMapping
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }
    //Удалить по ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }
}

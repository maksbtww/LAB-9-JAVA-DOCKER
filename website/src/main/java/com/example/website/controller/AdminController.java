package com.example.website.controller;

import com.example.website.entity.User;
import com.example.website.service.LibraryService;
import com.example.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private LibraryService libraryService;

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String adminPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails == null || userDetails.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("admin"))) {
            return "redirect:/login";
        }

        model.addAttribute("books", libraryService.getAllBooks());
        model.addAttribute("users", userService.getAllActiveUsers());
        return "admin";
    }

    @PostMapping("/admin/user/add")
    public String addUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        userService.addUser(username, password, role);
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/edit")
    public String editUser(@RequestParam int id, @RequestParam String username, @RequestParam String password, @RequestParam String role) {
        userService.updateUser(id, username, password, role);
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/delete")
    public String deleteUser(@RequestParam int id) {
        userService.softDeleteUser(id);
        return "redirect:/admin";
    }
}

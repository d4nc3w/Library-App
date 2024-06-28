package org.example.tpo_12.controller;

import org.example.tpo_12.auth.UserRegisterDTO;
import org.example.tpo_12.auth.UserRole;
import org.example.tpo_12.auth.UserService;
import org.example.tpo_12.model.Book;
import org.example.tpo_12.model.BookDTO;
import org.example.tpo_12.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/adminPage")
public class AdminController {

    UserService userService;
    BookService bookService;

    public AdminController(UserService userService, BookService bookService){
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("userEmails", userService.findAllUsers());
        return "admin";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam String email){
        userService.deleteUserByEmail(email);
        return "redirect:/adminPage";
    }

    @GetMapping("/editUser")
    public String editUser(@RequestParam String email, Model model){
//        model.addAttribute("user", userService.findUserCredentialsByEmail(email));
//        model.addAttribute("roles", userService.findRolesByEmail(email));
        userService.findUserCredentialsByEmail(email).ifPresent(user -> model.addAttribute("user", user));
        Set<String> roles = userService.findRolesByEmail(email);
        model.addAttribute("roles", roles);
        return "edit-user";
    }

    @PostMapping("/editUser")
    public String editUser(@RequestParam String email, @RequestParam Set<String> roles){
        userService.updateUserRoles(email, roles);
        return "redirect:/adminPage";
    }

    @GetMapping("/addUser")
    public String addUserForm(Model model) {
        model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        return "add-user";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("userRegisterDTO") UserRegisterDTO userDTO) {
        userService.registerUser(userDTO);
        return "redirect:/adminPage";
    }
}

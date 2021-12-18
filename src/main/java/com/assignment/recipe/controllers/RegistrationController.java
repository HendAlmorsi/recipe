package com.assignment.recipe.controllers;

import com.assignment.recipe.dto.UserDto;
import com.assignment.recipe.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void add(@RequestBody UserDto user) {
        userService.registerNewUserAccount(user);
    }
}

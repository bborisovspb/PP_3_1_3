package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import javax.validation.Valid;


@Qualifier("userService")
@Controller
@RequestMapping("/")
public class UsersController {

    private final UserServiceImpl userService;

    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String gerUserPage(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute(user);
        return "userInfo";
    }

}

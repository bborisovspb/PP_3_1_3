package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @GetMapping("")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/index";
    }

    @GetMapping("/new")
    public String getViewForCreateUsers(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/id")
    public String userInfo(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.userInfo(id));
        return "userInfo";
    }

    @GetMapping("/edit")
    public String getViewForEditUser(Model model, @RequestParam("edit") int id) {
        model.addAttribute("user", userService.userInfo(id));
        return "edit";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("delete") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}

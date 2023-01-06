package org.example.pp.controller;

import org.example.pp.model.User;
import org.example.pp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "main";
    }

    @RequestMapping(value = "/new")
    public String getNewUserForm() {
        return "newUser";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam String email) {

        userService.saveUser(new User(firstName, lastName, email));
        return "redirect:/";
    }

    @RequestMapping(value = "/delete")
    public String deleteUser(@RequestParam long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{user}")
    public String getUserEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user",  user);
        return "editUser";
    }

    @PostMapping(value = "/edit/{user}")
    public String updateUser(@RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam String email,
                            //@RequestParam(name = "id") User user
                             @RequestParam Long id) {
        User user = userService.findUserById(id).get();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userService.saveUser(user);
        return "redirect:/";
    }
}

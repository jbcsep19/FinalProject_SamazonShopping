package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class SecurityController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;



    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {

        return "login";
    }
//modification
    @RequestMapping("/logout")
    public String logout(){
        return "/";
    }       //return to index on logout

    @RequestMapping("/secure")
    public String secure(Principal principal, Model model) {
        String username = principal.getName();
        model.addAttribute("user", userRepository.findByUsername(username));
        return "secure";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, @RequestParam String role) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "registration";
        }
        // ***** authentication for administrator and user *********
        if (role.equalsIgnoreCase("admin")){
            userService.saveAdmin(user);
            model.addAttribute("message", "Admin Account Created");
            return "index";
        }
        else if (role.equalsIgnoreCase("user")){
            userService.saveUser(user);
            model.addAttribute("message", "User Account Created");
        }
            return "index";
    }

    @RequestMapping("/userlist")
    public String getUsers(Model model){
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @RequestMapping("/updateUser/{id}")
    public String getUser(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userRepository.findById(id).get());
        return "registration";
    }

    @RequestMapping("/deleteUser/{id}")
    public String delUser(@PathVariable("id") Long id, Model model){
        userRepository.deleteById(id);
        return "userList";
    }
}
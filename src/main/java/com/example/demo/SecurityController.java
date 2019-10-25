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


//    @RequestMapping("/")
//    public String index() {
//        return "index";
//    }

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
        else {

 //           userService.saveUser(user);

            if ((user.getPosition()).equalsIgnoreCase("administrator")) {
                userService.saveAdmin(user);
            } else {
                userService.saveUser(user);
            }
            model.addAttribute("message", "User Account Created");
        }
            return "index";
    }

}
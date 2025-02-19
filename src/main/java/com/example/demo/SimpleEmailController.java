package com.example.demo;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleEmailController {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    UserService userService;

    @Autowired
    SimpleEmailService simpleEmailService;

    @RequestMapping("/simpleemail")
    @ResponseBody
    String home() {
        try {
            simpleEmailService.sendEmail();
            return "Email Sent!";
        }catch(Exception ex) {
            return "Error in sending email: "+ex;
        }
    }
}


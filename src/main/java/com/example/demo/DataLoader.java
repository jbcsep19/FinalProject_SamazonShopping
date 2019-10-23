package com.example.demo;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception{
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));
        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("mark@user.com", "password", "Mark", "User", true, "mark");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("admin@admin.com", "password", "Admin", "User", true, "admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);
    }

    public void loadFromFile(){
       //File file = new File("\\resources\\ Magneto_Megafood.txt");

        try {
            FileInputStream fis = new FileInputStream("src/main/resources/Magneto_Megafood.txt");
            String data = IOUtils.toString(fis, "UTF-8");
            ArrayList<String> dataLines = new ArrayList(Arrays.asList(data.split("\n")));
            for (String line : dataLines) {
                String[] product_data = line.split("\t");
                boolean active = Boolean.parseBoolean(product_data[4]);
                Product product = new Product(product_data[0], product_data[1], product_data[2],
                        product_data[3], active);        //name, description, price, URL, active
            }
        }catch(Exception IOException){
            System.out.println("IO Exception Found");
        }
    }
}

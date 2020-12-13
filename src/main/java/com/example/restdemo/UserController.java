package com.example.restdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/users")
    public Iterable<User> getUsers() {

        User user1 = new User();
        user1.setEmail("test@mail.pl");
        user1.setName("Janusz");
        userRepository.save(user1);

        User user2 = new User();
        user2.setEmail("testmail@mail.pl");
        user2.setName("Marian");
        userRepository.save(user2);

        return userRepository.findAll();
    }

    @GetMapping("/api/test")
    public String getTest() {
        return "test lol";
    }
}

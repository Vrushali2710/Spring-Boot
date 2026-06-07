package com.example.demo.Controllers;

import com.example.demo.Model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/api")
public class WebController {
    @GetMapping("/user")
    public User getSingleUser() {
        return new User("Alice Smith", "Developer");
    }

    // API 2: Returns a JSON array of objects
    // URL: http://localhost:8080/api/users
    @GetMapping("/users")
    public List<User> getAllUsers() {
       List<User> users = Arrays.asList(
                new User("Alice Smith", "Developer"),
                new User("Bob Jones", "Manager"),
                new User("Charlie Brown", "Designer")
        );
        if (users.isEmpty()) {
           throw new RuntimeException("No User Found");

        }
        return users;
    }

}

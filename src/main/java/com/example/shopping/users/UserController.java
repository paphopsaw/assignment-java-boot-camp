package com.example.shopping.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/api/user/{id}")
    public UserResponse getOneUser(@PathVariable int id) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUser(userService.getById(id));
        return userResponse;
    }
}

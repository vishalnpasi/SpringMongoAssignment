package com.Assignment.controller;

import com.Assignment.models.UserModel;
import com.Assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/user")
    public ResponseEntity<String> RegisterUser(@RequestBody UserModel userModel){
        return userService.registerUser(userModel);
    }
}

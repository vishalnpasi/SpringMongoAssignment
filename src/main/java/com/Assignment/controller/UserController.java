package com.Assignment.controller;

import com.Assignment.exception.UserException;
import com.Assignment.models.UserModel;
import com.Assignment.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/user")
    public ResponseEntity<String> RegisterUser(@RequestBody @Valid UserModel userModel)throws Exception{
        return userService.registerUser(userModel);
    }
    @PutMapping("/user/{userName}")
    public ResponseEntity<?> UpdateUser(@PathVariable String userName) throws Exception {
        return userService.updateUser(userName);
    }
    @DeleteMapping("/user/{username}")
    public ResponseEntity<?> DeleteUser(@PathVariable String username)throws Exception{
        return userService.deleteUser(username);
    }
}

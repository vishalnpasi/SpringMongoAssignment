package com.Assignment.controller;

import com.Assignment.models.UserModel;
import com.Assignment.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody @Valid UserModel userModel)throws Exception{
        return new ResponseEntity<>(userService.createUser(userModel).getBody(),HttpStatus.CREATED);
    }
    @GetMapping("/user")
    public ResponseEntity<?> findUsers(){
        return new ResponseEntity<>(userService.getUsers().getBody(), HttpStatus.OK);
    }
    @PutMapping("/user/{userName}")
    public ResponseEntity<String> UpdateUser(@PathVariable String userName) throws Exception {
        return userService.updateUser(userName);
//        return new ResponseEntity<>(userService.updateUser(userName),HttpStatus.OK);
    }
    @DeleteMapping("/user/{username}")
    public ResponseEntity<?> DeleteUser(@PathVariable String username)throws Exception{
        return userService.deleteByUserName(username);
    }
}

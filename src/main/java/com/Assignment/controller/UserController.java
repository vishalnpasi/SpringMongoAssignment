package com.Assignment.controller;

import com.Assignment.models.UserModel;
import com.Assignment.repository.UserRepository;
import com.Assignment.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserController.class);
    @GetMapping("/demo")
    public String getApi(){
        logger.error("FATAL ERROR");
        logger.info("Entered in get(/demo) api");
        logger.debug("Debug");
        logger.warn("Warning");
        logger.trace("Trace");
        return userService.getTest();
    }
    @PostMapping("/user")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserModel userModel)throws Exception{
        logger.info("Entered in post(/user) api");
        UserModel savedUser = userService.createUser(userModel);
//        if(savedUser==null)
        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }
    @GetMapping("/user")
    public ResponseEntity<List<UserModel>> findUsers(){

        return new ResponseEntity<>(userService.getUsers(),HttpStatus.OK);
    }
    @PutMapping("/user/{userName}")
    public ResponseEntity<String> UpdateUser(@PathVariable String userName) throws Exception {
        return new ResponseEntity<>(userService.updateUser(userName),HttpStatus.OK);
    }
    @DeleteMapping("/user/{username}")
    public ResponseEntity<String> DeleteUser(@PathVariable String username)throws Exception{
        return new ResponseEntity<>(userService.deleteByUserName(username),HttpStatus.OK);
    }
    @GetMapping("/users/{userName}")
    public ResponseEntity<?> getByUserName(@PathVariable String userName){
//        List<UserModel> user=userRepository.findByUserName(userName);
        System.out.println("enter");
        UserModel user = userService.findByUserName(userName);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}

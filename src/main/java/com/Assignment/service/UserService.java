package com.Assignment.service;

import com.Assignment.models.UserModel;
import com.Assignment.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public ResponseEntity<String> registerUser(UserModel userModel){
        try {

            String email = userModel.getEmail();
            List<UserModel> list = userRepository.findDuplicateEmail(email);
            if(list.isEmpty()==false)
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is Already Exist");

            userRepository.save(userModel);
            return ResponseEntity.status(HttpStatus.OK).body(" User Register Successfully with id:" + userModel.getId());
        }
        catch (Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(" Server Error");

        }
    }
    public ResponseEntity<?> updateUser(String userName)throws Exception{
        List<UserModel> user = userRepository.findUser(userName);
        if(user.isEmpty()) return new ResponseEntity<>("User Name Not Found",HttpStatus.NOT_FOUND);
        UserModel u = user.get(0);
        String fullName = u.getFullName();
        String str = "";

        for(int i=0;i<fullName.length();i++){
            if(fullName.charAt(i)=='a' || fullName.charAt(i)=='e' || fullName.charAt(i)=='i'||
            fullName.charAt(i)=='o' || fullName.charAt(i)=='u'){

                str+=(char)generateRandom(Math.round((int)(Math.random()*3)));
            }
            else str+=fullName.charAt(i);
        }
        u.setFullName(str);

        userRepository.save(u);
        return new ResponseEntity<>("Data Updated Successfully...",HttpStatus.OK);
    }
    public ResponseEntity<?> deleteUser(String userName)throws Exception{

        List<UserModel> list = userRepository.findUser(userName);
        if(list.isEmpty())
            return new ResponseEntity<>("DATA NOT FOUND",HttpStatus.NOT_FOUND);

        userRepository.deleteById(list.get(0).getId());
        return new ResponseEntity<>("Deleted successfull..",HttpStatus.OK);
    }
    public static char generateRandom(int i ){
        switch (i){
            case 0:
                return (char)(Math.round(Math.random()*47));

            case 1:
                return (char)((Math.round(Math.random()*(64-58))+58));

            case 2:
                return (char)((Math.round(Math.random()*(96-91))+91));

            case 3:
                return (char)((Math.round(Math.random()*(128-123))+123));

        }
        return '*';
    }
}



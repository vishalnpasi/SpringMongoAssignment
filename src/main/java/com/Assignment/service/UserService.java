package com.Assignment.service;

import com.Assignment.models.UserModel;
import com.Assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
        public ResponseEntity<UserModel> createUser(UserModel userModel){
        try {

//            String email = userModel.getEmail();
//            List<UserModel> list = userRepository.findDuplicateEmail(email);
//            if(list.isEmpty()==false)
//                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is Already Exist");

//            return userRepository.save(userModel);
//            return ResponseEntity.status(HttpStatus.OK).body(" User Register Successfully with id:" + userModel.getId());
            return new ResponseEntity<>(userRepository.save(userModel),HttpStatus.CREATED);
        }
        catch (Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserModel());
        }
    }
    public ResponseEntity<List<UserModel>> getUsers(){
        List<UserModel> list = userRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    public ResponseEntity<String> updateUser(String userName)throws Exception{
        List<UserModel> users = userRepository.findByUserName(userName);
        if(users.isEmpty()) return new ResponseEntity<>("Data Not Found",HttpStatus.NOT_FOUND);
        UserModel u = users.get(0);
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
        UserModel savedData = userRepository.save(u);
//        return new ResponseEntity<>(savedData,HttpStatus.OK);
//        return savedData;
        return new ResponseEntity<>("Updated Successfully",HttpStatus.OK);
    }
    public ResponseEntity<String> deleteByUserName(String userName)throws Exception{

        List<UserModel> list = userRepository.findByUserName(userName);
        if(list.isEmpty())
            return new ResponseEntity<>("DATA NOT FOUND",HttpStatus.NOT_FOUND);

        userRepository.deleteById(list.get(0).getId());
        return new ResponseEntity<>("Deleted successfull",HttpStatus.OK);
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



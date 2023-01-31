package com.Assignment.service;

import com.Assignment.models.UserModel;
import com.Assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
        public UserModel createUser(UserModel userModel){
        try {
//            String email = userModel.getEmail();
//            List<UserModel> list = userRepository.findDuplicateEmail(email);
//            if(list.isEmpty()==false || userModel) return null;
            return userRepository.save(userModel);
        }
        catch (Exception err){
            return new UserModel();
        }
    }
    public List<UserModel> getUsers(){
        List<UserModel> list = userRepository.findAll();
        return list;
    }
    public String updateUser(String userName)throws Exception{
        List<UserModel> users = userRepository.findByUserName(userName);
        if(users.isEmpty()) return "Data Not Found";
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
        userRepository.save(u);
        return "Updated Successfully";
    }
    public String deleteByUserName(String userName)throws Exception{

        List<UserModel> list = userRepository.findByUserName(userName);
        if(list.isEmpty()) return "DATA NOT FOUND";

        userRepository.deleteById(list.get(0).getId());
        return "Deleted Successfully";
    }
    public String getTest(){
            return "hello";
    }
    public UserModel findByUserName(String userName){
            return userRepository.findByUserName(userName).get(0);
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



package com.Assignment.service;

import com.Assignment.models.UserModel;
import com.Assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private String id,userName,fullName,email,mobile,currentOrganization;
    public ResponseEntity<String> registerUser(UserModel userModel){
        try {

            String userName = userModel.getUserName();
            if(userName==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Name is Mandatory");

            String fullName = userModel.getFullName();
            if(fullName==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Full Name is Mandatory");

            String email = userModel.getEmail();
            if(email==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is Mandatory");

//            Query query = new Query();
//            query.addCriteria(Criteria.where("email").exists(true));
//            boolean val = userRepository.findOne(query,UserModel.class);
            List<UserModel> list = userRepository.findDuplicateEmail(email);
            if(list.isEmpty()==false)
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is Already Exist");

            String mobile = userModel.getMobile();
            if(mobile==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mobile is Mandatory");

            String currentOrganization = userModel.getCurrentOrganization();
            if(currentOrganization==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Current Organization is Mandatory");

            userRepository.save(userModel);
            return ResponseEntity.status(HttpStatus.OK).body(" User Register Successfully with id:" + userModel.getId());
        }
        catch (Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(" Server Error");

        }
    }
}

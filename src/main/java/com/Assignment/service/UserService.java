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
            try {
                List<UserModel> ll = userRepository.findDuplicateEmail(email);
//                System.out.print(ll + " " + ll.get(0)+""+ll.isEmpty());
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is Already Exist");
            }catch (Exception err){}
            String mobile = userModel.getMobile();
            if(mobile==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mobile is Mandatory");

            String currentOrganization = userModel.getCurrentOrganization();
            if(currentOrganization==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Current Organization is Mandatory");
//            String arr[] = {"userName","fullName","email","mobile","currentOrganization"};
//            for(int i = 0;i<arr.length;i++)
//                if(arr[i])
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Full Name Mandatory");
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Full Name Mandatory");
//            userRepository.save(userModel);
//            userModel.destructure(id,userName,fullName,email,mobile,currentOrganization);
//            System.out.print(this.id+this.userName+this.fullName+this.email+this.mobile+this.currentOrganization);
            return ResponseEntity.status(HttpStatus.OK).body(" User Register Successfully with id:" + userModel.getId());
        }
        catch (Exception err){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(" Server Error");

        }
    }
}

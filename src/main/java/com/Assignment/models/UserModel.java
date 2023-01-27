package com.Assignment.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "userDB")
public class UserModel {
    @Id
    private String id;
    private String userName;
    private String fullName;
    private String email;
    private String mobile;
    private String currentOrganization;

//    public void destructure(String id,String userName,String fullName,String email ,String mobile,String currentOrganization){
//        id=this.id;
//        userName = this.userName;
//        fullName = this.fullName;
//        email = this.mobile;
//        mobile = this.mobile;
//        currentOrganization = this.currentOrganization;
//    }

}

package com.Assignment.models;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    @Id
    private String id;

    @NotBlank(message = " User Name Shouldn't be null")
    @Size(min = 2 ,message = "User Name min length should be 2")
    private String userName;
    @NotNull(message = "Full Name Shouldn't be null")
    @Size(min=2 , message = " Full Name min length should be 2")
    private String fullName;
    @NotNull(message = "Email Shouldn't be null")
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+@[A-Za-z0-9-]+(\\.)*([A-Za-z]{2,})$" ,message = " Plz Enter Valid Email")
    private String email;
    @NotNull(message = "Phone Shouldn't be null")
    @Pattern(regexp = "^\\d{10}$" ,message = "invalid Mobile Number Entered")
    private String mobile;
    @NotNull(message = "Current Organization Shouldn't be null")
    @Size(min = 2 ,message = "Current Organization min length should be 2")
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

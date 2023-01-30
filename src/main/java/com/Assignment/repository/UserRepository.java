package com.Assignment.repository;

import com.Assignment.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<UserModel,String> {
    @Query("{email:?0}")
    List<UserModel> findDuplicateEmail(String email);

    @Query("{userName:?0}")
    List<UserModel> findByUserName(String userName);

}

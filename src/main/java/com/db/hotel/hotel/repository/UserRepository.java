package com.db.hotel.hotel.repository;

import com.db.hotel.hotel.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {

    @Query("{username:'?0'}")
    User findByUsername(String username);

    public long count();

}
package com.tweet.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tweet.app.entity.User;


public interface UserRepository extends MongoRepository<User, String>{

}

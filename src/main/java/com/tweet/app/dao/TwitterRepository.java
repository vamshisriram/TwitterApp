package com.tweet.app.dao;

import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tweet.app.entity.Twitter;

public interface TwitterRepository extends MongoRepository<Twitter, String>{
	
	@Query("{'userid': ?0 }")
	public Stream<Twitter> getTweetsbyuser(String userid);
}

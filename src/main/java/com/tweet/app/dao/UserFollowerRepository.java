package com.tweet.app.dao;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tweet.app.entity.Userfollower;

public interface UserFollowerRepository extends CrudRepository<Userfollower, Long>{
	
	@Query("select f from Userfollower f where f.user_id = :id")
	public Stream<Userfollower> getFollows(@Param("id")String id);
	
	@Query("select f from Userfollower f where f.follower = :id")
	public Stream<Userfollower> getFollowers(@Param("id")String id);

}

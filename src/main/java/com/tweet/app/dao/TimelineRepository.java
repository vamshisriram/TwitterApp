package com.tweet.app.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.tweet.app.entity.Twitter;

@Repository
public class TimelineRepository {
	
	@Autowired
    private final RedisTemplate redisTemplate;
	
	private ListOperations<String, Twitter> listOps;
	
	@Autowired
    public TimelineRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
       listOps = redisTemplate.opsForList();
    }
	
	@SuppressWarnings("unchecked")
	public void pushTweettoTimeline(String userId, Twitter twitter){		
		listOps.leftPush(userId, twitter);		
	}
	
	public List<Twitter> getTweetTimeline(String userId){
		List<Twitter> tweetTimeline = listOps.range(userId, 0, 100);
		return tweetTimeline;
	}

	

}

package com.tweet.app.services;

import java.util.List;

import com.tweet.app.dto.TwitterDTO;
import com.tweet.app.error.UserNotFoundException;


public interface TwitterService {
	
	public TwitterDTO postTweet(TwitterDTO twitterDTO) throws UserNotFoundException;
	
	public TwitterDTO getTweetbyid(String id);
	
	public List<TwitterDTO> listTweetsbyuser(String userId) throws UserNotFoundException;
	
	public List<TwitterDTO> tweetTimelinebyuser(String userId);
	
	

}

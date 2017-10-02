package com.tweet.app.services;

import java.util.List;

import com.tweet.app.dto.TwitterDTO;
import com.tweet.app.entity.Twitter;



public interface TimelineService {
	
	public void pushTofollowertimeline(String userid, Twitter twitter);
	
	public List<TwitterDTO> getUsertimeline(String userid);

}

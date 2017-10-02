package com.tweet.app.services;

import java.util.List;

import com.tweet.app.dto.FollowDTO;
import com.tweet.app.error.UserNotFoundException;


public interface UserFollowerService {
	
	public void updateFollower(FollowDTO followDTO) throws UserNotFoundException;
	
	public List<FollowDTO> getUserFollows(String id) throws UserNotFoundException;
	
	public List<FollowDTO> getUserFollowers(String id) throws UserNotFoundException;

}

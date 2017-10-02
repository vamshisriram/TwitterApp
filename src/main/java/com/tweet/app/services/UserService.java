package com.tweet.app.services;

import com.tweet.app.dto.UserDTO;
import com.tweet.app.error.UserNotFoundException;

public interface UserService {
	
	
	public UserDTO createUser(UserDTO userdto);
	
	public UserDTO getUser(String id) throws UserNotFoundException;
	
	public Boolean checkUserexists(String id);
	

}

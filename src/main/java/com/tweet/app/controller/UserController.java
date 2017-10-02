package com.tweet.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tweet.app.dto.FollowDTO;
import com.tweet.app.dto.UserDTO;
import com.tweet.app.error.UserNotFoundException;
import com.tweet.app.services.UserFollowerService;
import com.tweet.app.services.UserService;


@RestController
@RequestMapping("/v1")
public class UserController {
	
	
	private UserService userService;
	private UserFollowerService userFollowerService;
	
	@Autowired
	public UserController(UserService userService, UserFollowerService userFollowerService){
		this.userService = userService;
		this.userFollowerService = userFollowerService;
	}
	
	/*
	 * method to create user
	 * POST request
	 */	
	@RequestMapping(method=RequestMethod.POST, value="/user")
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO create(@RequestBody @Valid UserDTO userdto){				
		UserDTO user = userService.createUser(userdto);
		return user;		
	}
	
	/*
	 * method to get user details by id
	 * GET request
	 */	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public UserDTO getUser(@PathVariable("id") String id) throws UserNotFoundException {
		UserDTO user = userService.getUser(id);
		return user;
	}
	
	/*
	 * user can follow other users. Method to update user follows list
	 * POST request
	 */	
	@RequestMapping(value = "/updatefollow", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void updateFollows(@RequestBody FollowDTO followDTO) throws UserNotFoundException{
		userFollowerService.updateFollower(followDTO);
	}
	
	/*
	 * method to get user follows
	 * GET
	 */	
	@RequestMapping(value = "/user/{id}/follows", method = RequestMethod.GET)
	public List<FollowDTO> getUserFollows(@PathVariable("id") String id) throws UserNotFoundException {
		List<FollowDTO> userFollows = userFollowerService.getUserFollows(id);
		return userFollows;
	}
	
	/*
	 * method to get user followers list
	 * GET
	 */	
	@RequestMapping(value = "/user/{id}/followers", method = RequestMethod.GET)
	public List<FollowDTO> getUserFollowers(@PathVariable("id") String id) throws UserNotFoundException{
		List<FollowDTO> userFollows = userFollowerService.getUserFollowers(id);
		return userFollows;
	}

}

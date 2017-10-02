package com.tweet.app.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweet.app.dao.UserRepository;
import com.tweet.app.dto.UserDTO;
import com.tweet.app.entity.User;
import com.tweet.app.error.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	

	@Autowired
	UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDTO createUser(UserDTO userdto) {

		User user = User.getBuilder().userid(userdto.getId()).firstName(userdto.getFirstName())
				.lastName(userdto.getFirstName()).build();
		user.setCreated(new Date());
		user = userRepository.save(user);
		return convertUser(user);
	}

	@Override
	public UserDTO getUser(String id) throws UserNotFoundException{
		User user = userRepository.findOne(id);
		if(user == null){
			throw new UserNotFoundException("No User for :" + id);
		}
		return convertUser(user);
	}
	
	

	private UserDTO convertUser(User user) {
		UserDTO userdto = new UserDTO();
		userdto.setId(user.getUser_id());
		userdto.setFirstName(user.getFirstName());
		userdto.setLastName(user.getLastName());
		userdto.setCreated(user.getCreated());

		return userdto;
	}

	@Override
	public Boolean checkUserexists(String id) {
		User user = userRepository.findOne(id);
		if(user!=null){
			return true;
		}else{
			return false;
		}
	}

	

}

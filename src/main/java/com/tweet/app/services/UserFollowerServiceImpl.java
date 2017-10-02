package com.tweet.app.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweet.app.dao.UserFollowerRepository;
import com.tweet.app.dto.FollowDTO;
import com.tweet.app.entity.Userfollower;
import com.tweet.app.error.UserNotFoundException;

@Service
public class UserFollowerServiceImpl implements UserFollowerService {
	
	private final UserFollowerRepository userFollowerRepository;
	private UserService userService;
	
	@Autowired
	UserFollowerServiceImpl(UserFollowerRepository userFollowerRepository, UserService userService) {
		this.userFollowerRepository = userFollowerRepository;
		this.userService = userService;
	}

	@Override
	public void updateFollower(FollowDTO followDTO) throws UserNotFoundException{
		Userfollower userFollower = new Userfollower();
		userFollower.setUser_id(followDTO.getUser_id());
		userFollower.setFollower(followDTO.getFollower());
		userFollower.setStarted(new Date());
		if(followDTO.getActive()){
			userFollower.setActive(1);
		}else{
			userFollower.setActive(0);
		}
		if(userService.checkUserexists(userFollower.getUser_id()) && userService.checkUserexists(userFollower.getFollower())){
			userFollowerRepository.save(userFollower);
		}else{
			throw new UserNotFoundException("user or follower doesnt exist");
		}
		
		

	}

	@Override
	@Transactional
	public List<FollowDTO> getUserFollows(String id) throws UserNotFoundException{
		List<FollowDTO> flist = new ArrayList<FollowDTO>();	
		if(! userService.checkUserexists(id)){
			throw new UserNotFoundException("No user for id :" + id);
		}
		Stream<Userfollower> followList = userFollowerRepository.getFollows(id);
		followList.forEach((uf) ->{
			FollowDTO fdto = new FollowDTO();
			fdto.setUser_id(uf.getUser_id());
			fdto.setFollower(uf.getFollower());
			fdto.setStarted(uf.getStarted());
			if(uf.getActive().equals(1)){
				fdto.setActive(true);
			}else{
				fdto.setActive(false);
			}
			flist.add(fdto);
		});
		
		
		return flist;
	}
	
	

	@Override
	@Transactional
	public List<FollowDTO> getUserFollowers(String id) throws UserNotFoundException{
		List<FollowDTO> flist = new ArrayList<FollowDTO>();	
		if(! userService.checkUserexists(id)){
			throw new UserNotFoundException("No user for id :" + id);
		}
		Stream<Userfollower> followersList = userFollowerRepository.getFollowers(id);
		followersList.forEach((uf) ->{
			FollowDTO fdto = new FollowDTO();
			fdto.setUser_id(uf.getUser_id());
			fdto.setFollower(uf.getFollower());
			fdto.setStarted(uf.getStarted());
			if(uf.getActive().equals(1)){
				fdto.setActive(true);
			}else{
				fdto.setActive(false);
			}
			flist.add(fdto);
		});
		
		
		return flist;
	}
	
	
	
	

}

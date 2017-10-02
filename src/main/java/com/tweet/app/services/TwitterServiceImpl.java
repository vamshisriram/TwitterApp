package com.tweet.app.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweet.app.dao.TwitterRepository;
import com.tweet.app.dto.TwitterDTO;
import com.tweet.app.entity.Twitter;
import com.tweet.app.error.UserNotFoundException;

@Service
public class TwitterServiceImpl implements TwitterService {
	
	private TwitterRepository twitterRepository;
	private TimelineService timelineService;
	private UserFollowerService userFollowerService;
	private UserService userService;
	
	private Random rand;
	
	@Autowired
	public TwitterServiceImpl(TwitterRepository twitterRepository, TimelineService timelineService,UserFollowerService userFollowerService, UserService userService){
		this.twitterRepository = twitterRepository;
		this.timelineService = timelineService;
		this.userFollowerService = userFollowerService;
		this.userService = userService;
		this.rand = new Random();
	}
	
	 
	@Override
	public TwitterDTO postTweet(TwitterDTO twitterDTO) throws UserNotFoundException{
		
		Twitter twitter = new Twitter();
		Integer  n = rand.nextInt(Integer.MAX_VALUE) + 1;
		twitter.setTid(twitterDTO.getUserid()+n);
		twitter.setData(twitterDTO.getData());
		twitter.setCreated(new Date());
		twitter.setUserid(twitterDTO.getUserid());
		if(!userService.checkUserexists(twitter.getUserid())){
			throw new UserNotFoundException("No user for id "+twitter.getUserid());
		}
		twitter = twitterRepository.save(twitter);
		timelineService.pushTofollowertimeline(twitter.getUserid(), twitter);			
		return convertTwitter(twitter);
	}

	@Override
	public TwitterDTO getTweetbyid(String id) {
		Twitter twitter = twitterRepository.findOne(id);
		return convertTwitter(twitter);
	}

	@Override
	public List<TwitterDTO> listTweetsbyuser(String userId) throws UserNotFoundException {
		List<TwitterDTO> tweetDTOlist = new ArrayList<TwitterDTO>();
		if(!userService.checkUserexists(userId)){
			throw new UserNotFoundException("No user for id "+userId);
		}
		Stream<Twitter> tweetsList = twitterRepository.getTweetsbyuser(userId);
		tweetsList.forEach((twitter) ->{
			TwitterDTO tw = new TwitterDTO();
			tw.setTid(twitter.getTid());
			tw.setCreated(twitter.getCreated());
			tw.setData(twitter.getData());
			tw.setUserid(twitter.getUserid());
			tweetDTOlist.add(tw);
		});
		return tweetDTOlist;
	}
	
	@Override
	public List<TwitterDTO> tweetTimelinebyuser(String userId) {
		
		List<TwitterDTO> tweetTimeline = timelineService.getUsertimeline(userId);		
		return tweetTimeline;
	}
	
	private TwitterDTO convertTwitter(Twitter twitter){
		TwitterDTO tw = new TwitterDTO();
		tw.setTid(twitter.getTid());
		tw.setCreated(twitter.getCreated());
		tw.setData(twitter.getData());
		tw.setUserid(twitter.getUserid());
		
		return tw;
	}
	
	

}

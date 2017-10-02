package com.tweet.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweet.app.dao.TimelineRepository;
import com.tweet.app.dto.FollowDTO;
import com.tweet.app.dto.TwitterDTO;
import com.tweet.app.entity.Twitter;
import com.tweet.app.error.UserNotFoundException;

@Service
public class TimelineServiceImpl implements TimelineService {

	private TimelineRepository timelineRepository;
	private UserFollowerService userFollowerService;

	@Autowired
	public TimelineServiceImpl(TimelineRepository timelineRepository, UserFollowerService userFollowerService) {
		this.timelineRepository = timelineRepository;
		this.userFollowerService = userFollowerService;
	}

	@Override
	public void pushTofollowertimeline(String userid, Twitter twitter) {

		timelineRepository.pushTweettoTimeline(userid, twitter);
		List<String> followers = getFollowers(userid);
		followers.stream().forEach((f)->{
			System.out.println("user is " + f);
			timelineRepository.pushTweettoTimeline(f, twitter);
		});
	}

	@Override
	public List<TwitterDTO> getUsertimeline(String userid) {
		List<TwitterDTO> tweetTimeline = new ArrayList<TwitterDTO>();
		List<Twitter> tweetlist = timelineRepository.getTweetTimeline(userid);
		tweetlist.stream().forEach((twitter)->{
			TwitterDTO tw = new TwitterDTO();
			tw.setTid(twitter.getTid());
			tw.setCreated(twitter.getCreated());
			tw.setData(twitter.getData());
			tw.setUserid(twitter.getUserid());
			tweetTimeline.add(tw);
		});
			
		return tweetTimeline;

	}

	private List<String> getFollowers(String userId) {
		List<String> userfollowers = new ArrayList<String>();
		List<FollowDTO> followers;
		try {
			followers = userFollowerService.getUserFollowers(userId);
			if (followers != null || followers.size() > 0)
				followers.stream().forEach((f) -> {
					userfollowers.add(f.getUser_id());
				});
		} catch (UserNotFoundException e) {
			
		}
		
		return userfollowers;

	}

}

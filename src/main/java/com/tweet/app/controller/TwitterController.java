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

import com.tweet.app.dto.TwitterDTO;
import com.tweet.app.error.UserNotFoundException;
import com.tweet.app.services.TwitterService;

@RestController
@RequestMapping("/v1/tweets")
public class TwitterController {
	
	private TwitterService twitterService;
	
	@Autowired
	public TwitterController(TwitterService twitterService){
		this.twitterService = twitterService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public TwitterDTO create(@RequestBody @Valid TwitterDTO twitterDTO) throws UserNotFoundException{				
		TwitterDTO tDTO = twitterService.postTweet(twitterDTO);		
		return tDTO;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	public TwitterDTO getTweetbyid(@PathVariable("id") String id){
		TwitterDTO twitterDTO = twitterService.getTweetbyid(id);
		return twitterDTO;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/user/{id}")
	public List<TwitterDTO> getTweetbyuserid(@PathVariable("id") String id) throws UserNotFoundException{
		List<TwitterDTO> twitterDTO = twitterService.listTweetsbyuser(id);
		return twitterDTO;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/timeline/{id}")
	public List<TwitterDTO> getTweettimelinebyuserid(@PathVariable("id") String id){
		List<TwitterDTO> twitterDTO = twitterService.tweetTimelinebyuser(id);
		return twitterDTO;
	}

}

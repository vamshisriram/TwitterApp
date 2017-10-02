package com.tweet.app.dto;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class FollowDTO {
	
	@NotEmpty
	private String user_id;
	@NotEmpty
	private String follows;
	private Date started;
	@NotEmpty
	private Boolean active;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getFollower() {
		return follows;
	}
	public void setFollower(String follower) {
		this.follows = follower;
	}
	public Date getStarted() {
		return started;
	}
	public void setStarted(Date started) {
		this.started = started;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

}

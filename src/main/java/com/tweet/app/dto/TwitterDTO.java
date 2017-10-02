package com.tweet.app.dto;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class TwitterDTO {
	
	
	private String tid;
	@NotEmpty
	private String data;
	
	private Date created;
	@NotEmpty
	private String userid;
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

}

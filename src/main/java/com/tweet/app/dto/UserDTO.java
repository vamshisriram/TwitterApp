package com.tweet.app.dto;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

public class UserDTO {
	
	@NotEmpty
	private String id;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	
	private Date created;
	
	private List<UserDTO> follows;
	private List<UserDTO> followers;
	
	
	public List<UserDTO> getFollows() {
		return follows;
	}

	public void setFollows(List<UserDTO> follows) {
		this.follows = follows;
	}

	public List<UserDTO> getFollowers() {
		return followers;
	}

	public void setFollowers(List<UserDTO> followers) {
		this.followers = followers;
	}

	public UserDTO(){
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString(){
		return String.format(
                "UserDTO[id=%s, firstName=%s, lastName=%s]",
                this.id,
                this.firstName,
                this.lastName
        );
	}

}

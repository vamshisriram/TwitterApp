package com.tweet.app.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "User")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String _id;
	private String firstName;
	private String lastName;
	private Date created;
	
	
	public User(){}
	
	public User(UserBuilder userBuilder){
		this._id = userBuilder.user_id;
		this.firstName = userBuilder.firstName;
		this.lastName = userBuilder.lastName;
	}
	
	public static UserBuilder getBuilder(){
		return new UserBuilder();
	}
	
	
	public String getUser_id() {
		return _id;
	}
	public void setUser_id(String user_id) {
		this._id = user_id;
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
	
	
	public static class UserBuilder{
		
		private String user_id;
		private String firstName;
		private String lastName;
		
		private UserBuilder(){}
		
		public UserBuilder userid(String user_id){
			this.user_id = user_id;
			return this;
		}
		public UserBuilder firstName(String firstName){
			this.firstName = firstName;
			return this;
		}
		public UserBuilder lastName(String lastName){
			this.lastName = lastName;
			return this;
		}
		
		public User build() {
            User build = new User(this);
            build.validateUser(build.getUser_id(), build.getFirstName(), build.getLastName());

            return build;
        }
				
	}
	
	private void validateUser(String user_id, String firstName, String lastName){
		if (user_id == null || firstName == null || lastName == null) {
            throw new NullPointerException("fields are null");
        }
		if (user_id.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            throw new NullPointerException("fields are empty");
        }
		
		
	}
	
}

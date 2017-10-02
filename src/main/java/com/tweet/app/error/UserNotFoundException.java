package com.tweet.app.error;

public class UserNotFoundException extends Exception{
	
	public UserNotFoundException(String message) {
     //   super(String.format("No User for : <%s>", id));
        super(String.format(message));
    }

}

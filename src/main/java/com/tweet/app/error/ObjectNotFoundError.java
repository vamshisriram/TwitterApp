package com.tweet.app.error;

public class ObjectNotFoundError {
	
	private final String status;

    private final String message;

	public String getStatus() {
		return status;
	}

	public ObjectNotFoundError(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	

	

}

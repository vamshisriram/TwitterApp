package com.tweet.app.error;

public class PropertyError {
	
	private final String property;

    private final String message;

	public PropertyError(String property, String message) {
		super();
		this.property = property;
		this.message = message;
	}

	public String getProperty() {
		return property;
	}

	public String getMessage() {
		return message;
	}

}

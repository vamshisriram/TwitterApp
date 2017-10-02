package com.tweet.app.error;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ValidationError {
	
	
	private final List<PropertyError> propertyErrors = new ArrayList<>();

    public ValidationError() {

    }

    public void addPropertyError(String path, String message) {
    	PropertyError error = new PropertyError(path, message);
    	propertyErrors.add(error);
    }

    public List<PropertyError> getPropertyErrors() {
        return Collections.unmodifiableList(propertyErrors);
    }

}

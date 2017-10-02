package com.tweet.app.error;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ErrorHandler {
	
	private final MessageSource messageSource;

    @Autowired
    public ErrorHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationError processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> propertyErrors = result.getFieldErrors();

        return processPropertyrrors(propertyErrors);
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ObjectNotFoundError processNotFoundError(UserNotFoundException ex){
    	return new ObjectNotFoundError("400", ex.getLocalizedMessage());
    	
    }
    
    private ValidationError processPropertyrrors(List<FieldError> propertyErrors) {
        ValidationError validationError = new ValidationError();

        for (FieldError propertyError: propertyErrors) {
            String errorMessage = resolveErrorMessage(propertyError);
            validationError.addPropertyError(propertyError.getField(), errorMessage);
        }

        return validationError;
    }
    
    private String resolveErrorMessage(FieldError propertyError) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(propertyError, currentLocale);

        if (localizedErrorMessage.equals(propertyError.getDefaultMessage())) {
            String[] fieldErrorCodes = propertyError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }

        return localizedErrorMessage;
    }

}

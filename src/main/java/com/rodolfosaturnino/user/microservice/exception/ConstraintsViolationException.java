package com.rodolfosaturnino.user.microservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rodolfosaturnino.user.microservice.errors.ErrorMessage;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = ErrorMessage.ERROR_CONSTRAINTS_VIOLATION )
public class ConstraintsViolationException extends Exception
{

    static final long serialVersionUID = -3387516993224229948L;


    public ConstraintsViolationException(String message)
    {
        super(message);
    }

}

package com.learn2earn.todo.centralerrorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

	private static final long serialVersionUID = -5918101536055270524L;

	public ConflictException(String exception) {
        super(exception);
    }
}

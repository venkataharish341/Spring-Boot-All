package com.learn2earn.todo.centralerrorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException {

	private static final long serialVersionUID = 27715226258007L;

	public NoContentException(String exception) {
        super(exception);
    }
}

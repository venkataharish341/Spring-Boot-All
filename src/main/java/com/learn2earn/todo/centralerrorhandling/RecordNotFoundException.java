package com.learn2earn.todo.centralerrorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// We can directly throw RuntimeException but it shows 500 Server error.
// So, to set the status as 404 Not Found we are extending and setting the status. 
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -801720924034343363L;

	public RecordNotFoundException(String exception) {
        super(exception);
    }
}

package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserMarkerNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserMarkerNotFound(String message) {
		super(message);
	}

	public UserMarkerNotFound() {
	}

}
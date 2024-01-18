package com.crud.FullStack.in.exception;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(Long id) {
		super("Could not found the user with this id " +id);
	}

}

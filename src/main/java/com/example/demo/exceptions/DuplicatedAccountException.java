package com.example.demo.exceptions;

public class DuplicatedAccountException extends RuntimeException {
	private static final String ERROR = "There is an account in our database with give document number: ";

	public DuplicatedAccountException(String message) {
		super( ERROR + message );
	}
}

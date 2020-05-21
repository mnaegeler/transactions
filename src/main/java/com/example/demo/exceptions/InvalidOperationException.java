package com.example.demo.exceptions;

public class InvalidOperationException extends RuntimeException{
	private static final String MESSAGE = "Saldo insuficiente para realizar a operação";
	public InvalidOperationException (){super(MESSAGE);}
}

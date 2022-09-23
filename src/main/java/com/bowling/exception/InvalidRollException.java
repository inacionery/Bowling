package com.bowling.exception;

public class InvalidRollException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidRollException() {
		super("The roll is invalid.");
	}
}

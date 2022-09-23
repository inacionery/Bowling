package com.bowling.exception;

public class InvalidGameException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidGameException() {
		super("The game is invalid.");
	}
}

package com.bowling.exception;

public class GameEndedException extends Exception {
	private static final long serialVersionUID = 1L;

	public GameEndedException() {
		super("The game is ended.");
	}
}

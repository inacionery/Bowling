package com.bowling.exception;

public class FrameCompletedException extends Exception {
	private static final long serialVersionUID = 1L;

	public FrameCompletedException() {
		super("The frame is completed.");
	}
}

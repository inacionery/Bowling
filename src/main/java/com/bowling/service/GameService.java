package com.bowling.service;

import com.bowling.exception.FrameCompletedException;
import com.bowling.exception.GameEndedException;
import com.bowling.exception.InvalidFileException;
import com.bowling.exception.InvalidGameException;
import com.bowling.exception.InvalidRollException;

public interface GameService {

	String play(String fileName) throws FrameCompletedException, GameEndedException, InvalidFileException,
			InvalidGameException, InvalidRollException;
}

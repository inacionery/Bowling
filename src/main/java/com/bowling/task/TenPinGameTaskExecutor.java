package com.bowling.task;

import com.bowling.exception.FrameCompletedException;
import com.bowling.exception.GameEndedException;
import com.bowling.exception.InvalidFileException;
import com.bowling.exception.InvalidGameException;
import com.bowling.exception.InvalidRollException;
import com.bowling.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TenPinGameTaskExecutor implements CommandLineRunner {

	private static final Logger _logger = LoggerFactory.getLogger(TenPinGameTaskExecutor.class);

	@Autowired
	@Qualifier("TenPinGameService")
	private transient GameService gameService;

	@Override
	public void run(String... args) throws InvalidFileException, GameEndedException, FrameCompletedException,
			InvalidRollException, InvalidGameException {

		if (args.length != 1) {
			throw new InvalidFileException("File is required.");
		}

		String results = gameService.play(args[0]);

		_logger.info("\n" + results);
	}
}

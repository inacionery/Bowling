package com.bowling.service.impl;

import com.bowling.entity.Frame;
import com.bowling.entity.Player;
import com.bowling.entity.Roll;
import com.bowling.exception.FrameCompletedException;
import com.bowling.exception.GameEndedException;
import com.bowling.exception.InvalidFileException;
import com.bowling.exception.InvalidGameException;
import com.bowling.exception.InvalidRollException;
import com.bowling.service.GameService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service(value = "TenPinGameService")
public class TenPinGameServiceImpl implements GameService {

	private static final int LAST_FRAME = 9;
	private static final int MAX_FRAMES = 10;

	@Override
	public String play(String fileName) throws FrameCompletedException, GameEndedException, InvalidFileException,
			InvalidGameException, InvalidRollException {

		Collection<Player> players = getPlayers(fileName);

		for (Player player : players) {
			List<Frame> frames = new ArrayList<>();

			Frame frame = new Frame();

			frames.add(frame);

			for (Roll roll : player.getRolls()) {
				if (frame.isCompleted()) {
					if (frames.size() == MAX_FRAMES) {
						throw new GameEndedException();
					}
					if (frames.size() == LAST_FRAME) {
						frame = new Frame(true);
					} else {
						frame = new Frame();
					}

					frames.add(frame);
				}

				frame.addRoll(roll);
			}

			if (frames.size() != MAX_FRAMES || !frames.get(LAST_FRAME).isCompleted()) {
				throw new InvalidGameException();
			}

			calculateScores(frames);

			player.setFrames(frames);
		}

		return getResults(players);
	}

	private void calculateScores(List<Frame> frames) {
		Integer totalScore = 0;

		for (int i = 0; i < frames.size(); i++) {
			Frame frame = frames.get(i);

			totalScore += frame.getRollOne().getPins();

			if (frame.isStrike() && i != LAST_FRAME) {
				if (i + 1 < MAX_FRAMES) {
					Frame nextTrame = frames.get(i + 1);

					totalScore += nextTrame.getRollOne().getPins();

					if (nextTrame.isStrike() && i != 8) {
						if (i + 2 < MAX_FRAMES) {
							nextTrame = frames.get(i + 2);

							totalScore += nextTrame.getRollOne().getPins();
						}
					} else {
						totalScore += nextTrame.getRollTwo().getPins();
					}
				}
			} else {
				totalScore += frame.getRollTwo().getPins();

				if (frame.isSpare() && i + 1 < MAX_FRAMES) {
					Frame nextTrame = frames.get(i + 1);

					totalScore += nextTrame.getRollOne().getPins();
				} else if (i == LAST_FRAME) {
					totalScore += frame.getRollThree().getPins();
				}
			}

			frame.setScore(totalScore);
		}
	}

	private Collection<Player> getPlayers(String fileName) throws InvalidFileException, InvalidRollException {
		Map<String, Player> players = new LinkedHashMap<>();

		try {
			List<String> lines = Files.readAllLines(Paths.get(fileName));

			if (lines.isEmpty()) {
				throw new InvalidFileException("Empty file.");
			}

			for (String line : lines) {
				String[] split = line.split("\t");

				if (split.length != 2) {
					throw new InvalidFileException("Bad file.");
				}

				Player player = players.computeIfAbsent(split[0], v -> new Player(split[0]));

				player.addRoll(new Roll(split[1]));
			}
		} catch (IOException e) {
			throw new InvalidFileException(e.getMessage());
		}

		return players.values();
	}

	private String getResults(Collection<Player> players) {
		StringBuilder stringBuilder = new StringBuilder("Frame\t\t");
		stringBuilder.append(String.join("\t\t", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
		stringBuilder.append("\n");
		for (Player player : players) {
			stringBuilder.append(player.toString());
			stringBuilder.append("\n");
		}

		stringBuilder.deleteCharAt(stringBuilder.length() - 1);

		return stringBuilder.toString();
	}
}

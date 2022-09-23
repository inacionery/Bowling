package com.bowling.entity;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private List<Frame> frames;

	private final String playerName;
	private final List<Roll> rolls = new ArrayList<>();

	public Player(String playerName) {
		this.playerName = playerName;
	}

	public void addRoll(Roll roll) {
		rolls.add(roll);
	}

	public List<Roll> getRolls() {
		return rolls;
	}

	public void setFrames(List<Frame> frames) {
		this.frames = frames;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder(playerName);
		stringBuilder.append("\n");

		StringBuilder stringBuilderPinfalls = new StringBuilder("Pinfalls");

		StringBuilder stringBuilderScore = new StringBuilder("Score");

		for (Frame frame : frames) {
			stringBuilderPinfalls.append("\t");
			stringBuilderPinfalls.append(frame.toString());

			stringBuilderScore.append("\t\t");
			stringBuilderScore.append(frame.getScore());
		}

		stringBuilder.append(stringBuilderPinfalls.toString());
		stringBuilder.append("\n");
		stringBuilder.append(stringBuilderScore.toString());
		stringBuilder.append("\n");
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);

		return stringBuilder.toString();
	}
}

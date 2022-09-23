package com.bowling.entity;

import com.bowling.exception.FrameCompletedException;

public class Frame {
	private final transient boolean lastFrame;
	private transient Roll rollOne;
	private transient Roll rollThree;
	private transient Roll rollTwo;
	private Integer score;

	public Frame() {
		this(false);
	}

	public Frame(boolean lastFrame) {
		this.lastFrame = lastFrame;
	}

	public void addRoll(Roll roll) throws FrameCompletedException {
		if (isCompleted()) {
			throw new FrameCompletedException();
		}

		if (rollOne == null) {
			rollOne = roll;
		} else if (rollTwo == null) {
			rollTwo = roll;
		} else if (lastFrame && rollThree == null) {
			rollThree = roll;
		}
	}

	public Roll getRollOne() {
		return rollOne;
	}

	public Roll getRollThree() {
		return rollThree;
	}

	public Roll getRollTwo() {
		return rollTwo;
	}

	public Integer getScore() {
		return score;
	}

	public boolean isCompleted() {
		if (lastFrame) {
			return rollThree != null;
		}

		return rollOne != null && (rollTwo != null || rollOne.isStrike());
	}

	public boolean isSpare() {
		return rollOne != null && rollTwo != null && rollOne.getPins() + rollTwo.getPins() == 10;
	}

	public boolean isStrike() {
		return rollOne != null && rollOne.isStrike();
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		if (lastFrame) {
			stringBuilder.append(rollOne.toString());
			stringBuilder.append("\t");
			stringBuilder.append(rollTwo.toString());
			stringBuilder.append("\t");
			stringBuilder.append(rollThree.toString());
		} else if (rollOne.isStrike()) {
			stringBuilder.append("\t");
			stringBuilder.append(rollOne.toString());
		} else if (isSpare()) {
			stringBuilder.append(rollOne.toString());
			stringBuilder.append("\t");
			stringBuilder.append("/");
		} else {
			stringBuilder.append(rollOne.toString());
			stringBuilder.append("\t");
			stringBuilder.append(rollTwo.toString());
		}

		return stringBuilder.toString();
	}
}

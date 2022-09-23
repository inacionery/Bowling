package com.bowling.entity;

import com.bowling.exception.InvalidRollException;
import org.apache.commons.lang3.math.NumberUtils;

public class Roll {
	private final transient boolean foul;
	private final transient int pins;

	public Roll(String pins) throws InvalidRollException {
		if ("F".equals(pins)) {
			foul = true;
			this.pins = 0;
		} else if (NumberUtils.isCreatable(pins) && Integer.parseInt(pins) >= 0 && Integer.parseInt(pins) <= 10) {
			foul = false;
			this.pins = Integer.parseInt(pins);
		} else {
			throw new InvalidRollException();
		}
	}

	public Integer getPins() {
		return pins;
	}

	public boolean isStrike() {
		return pins == 10;
	}

	@Override
	public String toString() {
		if (foul) {
			return "F";
		}

		if (isStrike()) {
			return "X";
		}

		return String.valueOf(pins);
	}
}

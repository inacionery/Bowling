package com.bowling.entity;

import com.bowling.exception.FrameCompletedException;
import com.bowling.exception.InvalidRollException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RollTest {

	@Test
	public void testCreateRollFoul() throws FrameCompletedException, InvalidRollException {
		Roll roll = new Roll("F");

		Assertions.assertNotNull(roll);
	}

	@Test
	public void testCreateRollInvalid() throws FrameCompletedException, InvalidRollException {
		Assertions.assertThrowsExactly(InvalidRollException.class, () -> new Roll("-1"));

		Assertions.assertThrowsExactly(InvalidRollException.class, () -> new Roll("11"));
	}

	@Test
	public void testCreateRollValid() throws FrameCompletedException, InvalidRollException {
		Assertions.assertNotNull(new Roll("0"));
		Assertions.assertNotNull(new Roll("10"));
	}
}

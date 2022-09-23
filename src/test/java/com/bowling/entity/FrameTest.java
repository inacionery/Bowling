package com.bowling.entity;

import com.bowling.exception.FrameCompletedException;
import com.bowling.exception.InvalidRollException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FrameTest {

	@Test
	public void testAddRollInvalid() throws FrameCompletedException, InvalidRollException {
		Frame frame = new Frame();

		Roll roll = new Roll("1");

		frame.addRoll(roll);
		frame.addRoll(roll);

		Assertions.assertThrowsExactly(FrameCompletedException.class, () -> frame.addRoll(roll));
	}

	@Test
	public void testAddRollLastFrameInvalid() throws FrameCompletedException, InvalidRollException {
		Frame frame = new Frame(true);

		Roll roll = new Roll("1");

		frame.addRoll(roll);
		frame.addRoll(roll);
		frame.addRoll(roll);

		Assertions.assertThrowsExactly(FrameCompletedException.class, () -> frame.addRoll(roll));
	}

	@Test
	public void testAddRollLastFrameValid() throws FrameCompletedException, InvalidRollException {
		Frame frame = new Frame(true);

		Roll roll = new Roll("1");

		frame.addRoll(roll);
		frame.addRoll(roll);
		frame.addRoll(roll);

		Assertions.assertEquals(roll, frame.getRollOne());
		Assertions.assertEquals(roll, frame.getRollTwo());
		Assertions.assertEquals(roll, frame.getRollThree());
	}

	@Test
	public void testAddRollStrikeInvalid() throws FrameCompletedException, InvalidRollException {
		Frame frame = new Frame();

		frame.addRoll(new Roll("10"));

		Assertions.assertThrowsExactly(FrameCompletedException.class, () -> frame.addRoll(new Roll("1")));
	}

	@Test
	public void testAddRollValid() throws FrameCompletedException, InvalidRollException {
		Frame frame = new Frame();

		Roll roll = new Roll("1");

		frame.addRoll(roll);
		frame.addRoll(roll);

		Assertions.assertNotNull(frame.getRollOne());
		Assertions.assertNotNull(frame.getRollTwo());
		Assertions.assertNull(frame.getRollThree());
	}

	@Test
	public void testIsSpare() throws FrameCompletedException, InvalidRollException {
		Frame frame = new Frame();

		Assertions.assertFalse(frame.isSpare());

		frame.addRoll(new Roll("10"));

		Assertions.assertFalse(frame.isSpare());

		frame = new Frame();

		frame.addRoll(new Roll("5"));
		frame.addRoll(new Roll("4"));

		Assertions.assertFalse(frame.isSpare());

		frame = new Frame();

		frame.addRoll(new Roll("5"));
		frame.addRoll(new Roll("5"));

		Assertions.assertTrue(frame.isSpare());
	}

	@Test
	public void testIsStrike() throws FrameCompletedException, InvalidRollException {
		Frame frame = new Frame();

		Assertions.assertFalse(frame.isStrike());

		frame.addRoll(new Roll("10"));

		Assertions.assertTrue(frame.isStrike());

		frame = new Frame();

		frame.addRoll(new Roll("5"));
		frame.addRoll(new Roll("5"));

		Assertions.assertFalse(frame.isStrike());
	}
}

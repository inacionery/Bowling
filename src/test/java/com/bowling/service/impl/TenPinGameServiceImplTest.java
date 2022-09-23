package com.bowling.service.impl;

import com.bowling.BowlingApplication;
import com.bowling.exception.FrameCompletedException;
import com.bowling.exception.GameEndedException;
import com.bowling.exception.InvalidFileException;
import com.bowling.exception.InvalidGameException;
import com.bowling.exception.InvalidRollException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
		BowlingApplication.class}, initializers = ConfigDataApplicationContextInitializer.class)
public class TenPinGameServiceImplTest {

	@Autowired
	private TenPinGameServiceImpl tenPinGameServiceImpl;

	@Test
	public void testPlayEmptyFile() {
		Assertions.assertThrowsExactly(InvalidFileException.class,
				() -> tenPinGameServiceImpl.play("src/test/resources/negative/empty.txt"));
	}

	@Test
	public void testPlayExtraScoreFile() {
		Assertions.assertThrowsExactly(GameEndedException.class,
				() -> tenPinGameServiceImpl.play("src/test/resources/negative/extra-score.txt"));
	}

	@Test
	public void testPlayFreeTextFile() {
		Assertions.assertThrowsExactly(InvalidFileException.class,
				() -> tenPinGameServiceImpl.play("src/test/resources/negative/free-text.txt"));
	}

	@Test
	public void testPlayIncompleteFile() {
		Assertions.assertThrowsExactly(InvalidGameException.class,
				() -> tenPinGameServiceImpl.play("src/test/resources/negative/incomplete.txt"));
	}

	@Test
	public void testPlayInvalidScoreFile() {
		Assertions.assertThrowsExactly(InvalidRollException.class,
				() -> tenPinGameServiceImpl.play("src/test/resources/negative/invalid-score.txt"));
	}

	@Test
	public void testPlayNegativeFile() {
		Assertions.assertThrowsExactly(InvalidRollException.class,
				() -> tenPinGameServiceImpl.play("src/test/resources/negative/negative.txt"));
	}

	@Test
	public void testPlayPerfectFile() throws FrameCompletedException, GameEndedException, InvalidFileException,
			InvalidGameException, InvalidRollException {
		String actualResult = tenPinGameServiceImpl.play("src/test/resources/positive/perfect.txt");

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n");
		stringBuilder.append("Mike\n");
		stringBuilder.append("Pinfalls\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\tX\tX\n");
		stringBuilder.append("Score\t\t30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300");

		Assertions.assertEquals(stringBuilder.toString(), actualResult);
	}

	@Test
	public void testPlayScoresFile() throws FrameCompletedException, GameEndedException, InvalidFileException,
			InvalidGameException, InvalidRollException {
		String actualResult = tenPinGameServiceImpl.play("src/test/resources/positive/scores.txt");

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n");
		stringBuilder.append("Brian\n");
		stringBuilder.append("Pinfalls\t\tX\t7\t/\t9\t0\t\tX\t0\t8\t8\t/\tF\t6\t\tX\t\tX\tX\t8\t1\n");
		stringBuilder.append("Score\t\t20\t\t39\t\t48\t\t66\t\t74\t\t84\t\t90\t\t120\t\t148\t\t167\n");
		stringBuilder.append("Mike\n");
		stringBuilder.append("Pinfalls\t3\t/\t6\t3\t\tX\t8\t1\t\tX\t\tX\t9\t0\t7\t/\t4\t4\tX\t9\t0\n");
		stringBuilder.append("Score\t\t16\t\t25\t\t44\t\t53\t\t82\t\t101\t\t110\t\t124\t\t132\t\t151");

		Assertions.assertEquals(stringBuilder.toString(), actualResult);
	}

	@Test
	public void testPlayZeroScoreFile() throws FrameCompletedException, GameEndedException, InvalidFileException,
			InvalidGameException, InvalidRollException {
		String actualResult = tenPinGameServiceImpl.play("src/test/resources/positive/zero-score.txt");

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\n");
		stringBuilder.append("Mike\n");
		stringBuilder.append("Pinfalls\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\n");
		stringBuilder.append("Score\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0");

		Assertions.assertEquals(stringBuilder.toString(), actualResult);
	}
}

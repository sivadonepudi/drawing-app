package com.sample.application.drawing.util;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.sample.application.drawing.exception.InvalidInputException;

public class DrawingUtilsTest {
	@Rule
	public final ExpectedException expectedException = ExpectedException.none();

	@Test
	public void toPositiveInt() throws Exception {
		assertEquals(DrawingUtils.toPositiveInt("11"), 11);
	}

	@Test(expected = InvalidInputException.class)
	public void toPositiveInt2() throws Exception {
		DrawingUtils.toPositiveInt("0");
	}

	@Test(expected = InvalidInputException.class)
	public void toPositiveInt3() throws Exception {
		DrawingUtils.toPositiveInt("aa");
	}

	@Test
	public void shouldAllPositive() throws Exception {
		DrawingUtils.shouldAllPositive(1, 2, 3, 4);
	}

	@Test(expected = InvalidInputException.class)
	public void shouldAllPositive2() throws Exception {
		DrawingUtils.shouldAllPositive(1, -2, 3, 4);
	}

	@Test(expected = InvalidInputException.class)
	public void shouldAllPositive3() throws Exception {
		DrawingUtils.shouldAllPositive(1, 2, 0, 4);
	}

	@Test
	public void shouldAllNonNegative2() throws Exception {
		DrawingUtils.shouldAllPositive(1, 2, 3, 4);
	}

	public void shouldAllNonNegative() throws Exception {
		DrawingUtils.shouldAllNonNegative(1, 2, 0, 4);
	}

	@Test(expected = InvalidInputException.class)
	public void shouldAllNonNegative3() throws Exception {
		DrawingUtils.shouldAllPositive(1, -2, 3, 4);
	}

}
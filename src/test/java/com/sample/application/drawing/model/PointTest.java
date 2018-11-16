package com.sample.application.drawing.model;

import org.junit.Test;

import com.sample.application.drawing.exception.InvalidInputException;

public class PointTest {
	@Test
	public void create() throws Exception {
		new Point(1, 2);
	}

	@Test(expected = InvalidInputException.class)
	public void create3() throws Exception {
		new Point(-1, 2);
	}

	@Test(expected = InvalidInputException.class)
	public void create4() throws Exception {
		new Point(1, -2);
	}
}
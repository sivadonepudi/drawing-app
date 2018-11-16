package com.sample.application.drawing.model;

import org.junit.Test;

import com.sample.application.drawing.exception.InvalidInputException;
import com.sample.application.drawing.model.Line;

public class LineTest {
	@Test
	public void testCreate() throws Exception {
		new Line("1", "1", "1", "2");
	}

	@Test(expected = InvalidInputException.class)
	public void testCreate1() throws Exception {
		new Line("-1", "1", "1", "2");
	}

	@Test(expected = InvalidInputException.class)
	public void testCreate2() throws Exception {
		new Line("1", "-1", "1", "2");
	}

	@Test(expected = InvalidInputException.class)
	public void testCreate3() throws Exception {
		new Line("1", "1");
	}

	@Test(expected = InvalidInputException.class)
	public void testCreate4() throws Exception {
		new Line("1");
	}

	@Test(expected = InvalidInputException.class)
	public void testCreate6() throws Exception {
		new Line();
	}
}
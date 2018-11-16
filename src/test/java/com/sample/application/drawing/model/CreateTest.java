package com.sample.application.drawing.model;

import org.junit.Test;

import com.sample.application.drawing.exception.InvalidInputException;
import com.sample.application.drawing.model.Create;

public class CreateTest {
	@Test
	public void testCreate() throws Exception {
		new Create("1", "1");
	}

	@Test(expected = InvalidInputException.class)
	public void testCreate2() throws Exception {
		new Create("-11", "1");
	}

	@Test(expected = InvalidInputException.class)
	public void testCreate3() throws Exception {
		new Create("1", "-1");
	}
	
	@Test(expected = InvalidInputException.class)
	public void testCreateW() throws Exception {
		new Create("W", "1");
	}

	@Test(expected = InvalidInputException.class)
	public void testCreate4() throws Exception {
		new Create("1");
	}

	@Test(expected = InvalidInputException.class)
	public void testCreate6() throws Exception {
		new Create();
	}
}
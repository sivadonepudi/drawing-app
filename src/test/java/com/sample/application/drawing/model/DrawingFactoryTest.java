package com.sample.application.drawing.model;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.sample.application.drawing.exception.InvalidInputException;
import com.sample.application.drawing.impl.Command;
import com.sample.application.drawing.impl.DrawingFactory;
import com.sample.application.drawing.model.Create;
import com.sample.application.drawing.model.Filler;
import com.sample.application.drawing.model.Line;
import com.sample.application.drawing.model.Rectangle;

public class DrawingFactoryTest {

	@Test(expected = InvalidInputException.class)
	public void getCommand2() throws Exception {
		DrawingFactory.getInstance().getCommand("A");
	}

	@Test
	public void getCommand3() throws Exception {
		Command command = DrawingFactory.getInstance().getCommand("C 20 4");
		Assert.assertThat(command, CoreMatchers.instanceOf(Create.class));
	}

	@Test(expected = InvalidInputException.class)
	public void getCommand32() throws Exception {
		DrawingFactory.getInstance().getCommand("C 20 -4");
	}

	@Test
	public void getCommand4() throws Exception {
		Command command = DrawingFactory.getInstance().getCommand("L 1 2 1 22");
		Assert.assertThat(command, CoreMatchers.instanceOf(Line.class));
	}

	@Test(expected = InvalidInputException.class)
	public void getCommand42() throws Exception {
		DrawingFactory.getInstance().getCommand("L 1 2 1 -22");
	}

	@Test
	public void getCommand5() throws Exception {
		Command command = DrawingFactory.getInstance().getCommand("R 14 1 18 3");
		Assert.assertThat(command, CoreMatchers.instanceOf(Rectangle.class));
	}

	@Test(expected = InvalidInputException.class)
	public void getCommand52() throws Exception {
		DrawingFactory.getInstance().getCommand("R 14 1 18 -3");
	}

	@Test
	public void getCommand6() throws Exception {
		Command command = DrawingFactory.getInstance().getCommand("B 1 3 o");
		Assert.assertThat(command, CoreMatchers.instanceOf(Filler.class));
	}

}
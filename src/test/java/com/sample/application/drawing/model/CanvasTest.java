package com.sample.application.drawing.model;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.sample.application.drawing.Canvas;
import com.sample.application.drawing.exception.InvalidInputException;
import com.sample.application.drawing.impl.CanvasImpl;

public class CanvasTest {
    private Canvas canvas;

	@Rule
	public final ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		String cmd = "C 20 4";
		String[] split = cmd.split(" ");
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Create create = new Create(params);
		canvas = new CanvasImpl();
		canvas.execute(create);
	}

    // empty canvas
    @Test
    public void create() throws Exception {

        Assert.assertEquals(canvas.render(),
                "----------------------\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------");
    }

    // add vertical line
    @Test
	public void addEntity() throws Exception {
		String cmd = "L 1 2 1 3";
		String[] split = cmd.split(" ");
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Line drawLineCommand = new Line(params);

		canvas.execute(drawLineCommand);

        Assert.assertEquals(canvas.render(),
                "----------------------\n" +
                        "|                    |\n" +
                        "|x                   |\n" +
                        "|x                   |\n" +
                        "|                    |\n" +
                        "----------------------");

    }

    // add vertical line that is trimmed
	@Test
	public void addEntity2() throws Exception {
		String cmd = "L 1 2 1 22";
		String[] split = cmd.split(" ");
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Line drawLineCommand = new Line(params);

		canvas.execute(drawLineCommand);

        Assert.assertEquals(canvas.render(),
                "----------------------\n" +
                        "|                    |\n" +
                        "|x                   |\n" +
                        "|x                   |\n" +
                        "|x                   |\n" +
                        "----------------------");

    }

    // add horizontal line
	@Test
	public void addEntity3() throws Exception {
		String cmd = "L 2 2 4 2";
		String[] split = cmd.split(" ");
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Line drawLineCommand = new Line(params);
		canvas.execute(drawLineCommand);

        Assert.assertEquals(canvas.render(),
                "----------------------\n" +
                        "|                    |\n" +
                        "| xxx                |\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------");
    }

    // add horizontal line that is trimmed
	@Test
	public void addEntity4() throws Exception {
		String cmd = "L 1 2 30 2";
		String[] split = cmd.split(" ");
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Line drawLineCommand = new Line(params);
		canvas.execute(drawLineCommand);

        Assert.assertEquals(canvas.render(),
                "----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxxxxxxxxxxxxxxxx|\n" +
                        "|                    |\n" +
                        "|                    |\n" +
                        "----------------------");
    }

    // line outside of canvas
    @Test()
    public void addEntity6() throws Exception {
		String cmd = "L 100 20 100 22";
		String[] split = cmd.split(" ");
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Line drawLineCommand = new Line(params);
        expectedException.expect(InvalidInputException.class);
        canvas.execute(drawLineCommand);
    }

    // add rectangle
    @Test()
    public void addEntity7() throws Exception {
        
        String cmd = "R 100 20 100 22";
		String[] split = cmd.split(" ");
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Rectangle rectangleCommand = new Rectangle(params);
        expectedException.expect(InvalidInputException.class);
        canvas.execute(rectangleCommand);
    }

    // add rectangle
	@Test()
	public void addEntity8() throws Exception {
		String cmd = "R 14 1 18 3";
		String[] split = cmd.split(" ");
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Rectangle rectangleCommand = new Rectangle(params);
		canvas.execute(rectangleCommand);
        Assert.assertEquals(canvas.render(),
                "----------------------\n" +
                        "|             xxxxx  |\n" +
                        "|             x   x  |\n" +
                        "|             xxxxx  |\n" +
                        "|                    |\n" +
                        "----------------------");
    }

    // add rectangle that exceed the canvas height
    @Test()
    public void addEntity9() throws Exception {
        String cmd = "R 2 1 4 30";
		String[] split = cmd.split(" ");
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Rectangle rectangleCommand = new Rectangle(params);
		canvas.execute(rectangleCommand);

        Assert.assertEquals(canvas.render(),
                "----------------------\n" +
                        "| xxx                |\n" +
                        "| x x                |\n" +
                        "| x x                |\n" +
                        "| x x                |\n" +
                        "----------------------");
    }

    // add rectangle that exceed the canvas width
    @Test()
    public void addEntity10() throws Exception {
        String cmd = "R 2 1 40 3";
		String[] split = cmd.split(" ");
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Rectangle rectangleCommand = new Rectangle(params);
		canvas.execute(rectangleCommand);

        Assert.assertEquals(canvas.render(),
                "----------------------\n" +
                        "| xxxxxxxxxxxxxxxxxxx|\n" +
                        "| x                  |\n" +
                        "| xxxxxxxxxxxxxxxxxxx|\n" +
                        "|                    |\n" +
                        "----------------------");
    }

    // add rectangle that exceed both the canvas width and height
	@Test()
	public void addEntity11() throws Exception {
		String cmd = "R 2 1 40 30";
		String[] split = cmd.split(" ");
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Rectangle rectangleCommand = new Rectangle(params);
		canvas.execute(rectangleCommand);

        Assert.assertEquals(canvas.render(),
                "----------------------\n" +
                        "| xxxxxxxxxxxxxxxxxxx|\n" +
                        "| x                  |\n" +
                        "| x                  |\n" +
                        "| x                  |\n" +
                        "----------------------");
    }

    // add rectangle that is outside
    @Test()
    public void addEntity111() throws Exception {
        expectedException.expect(InvalidInputException.class);
        String cmd = "R 20 100 40 102";
		String[] split = cmd.split(" ");
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Rectangle rectangleCommand = new Rectangle(params);
		canvas.execute(rectangleCommand);
    }

    // bucket fill and empty canvas
    @Test()
    public void addEntity12() throws Exception {
        String cmd = "B 2 1 o";
		String[] split = cmd.split(" ");
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Filler rectangleCommand = new Filler(params);
		canvas.execute(rectangleCommand);

        Assert.assertEquals(canvas.render(),
                "----------------------\n" +
                        "|oooooooooooooooooooo|\n" +
                        "|oooooooooooooooooooo|\n" +
                        "|oooooooooooooooooooo|\n" +
                        "|oooooooooooooooooooo|\n" +
                        "----------------------");
    }

    // fill a vertical line
	@Test
	public void addEntity13() throws Exception {

		String cmd = "L 1 2 1 3";
		String[] split = cmd.split(" ");
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Line drawLineCommand = new Line(params);
		canvas.execute(drawLineCommand);

		cmd = "B 1 2 o";
		split = cmd.split(" ");
		params = Arrays.copyOfRange(split, 1, split.length);
		Filler bucketFillCommand = new Filler(params);
		canvas.execute(bucketFillCommand);

        Assert.assertEquals(canvas.render(),
                "----------------------\n" +
                        "|                    |\n" +
                        "|o                   |\n" +
                        "|o                   |\n" +
                        "|                    |\n" +
                        "----------------------");

    }

    // fill a rectangle edge
    @Test
    public void addEntity14() throws Exception {
        String cmd = "R 14 1 18 3";
		String[] split = cmd.split(" ");
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Rectangle rectangleCommand = new Rectangle(params);
		canvas.execute(rectangleCommand);
        
        cmd = "B 14 1 o";
		split = cmd.split(" ");
		params = Arrays.copyOfRange(split, 1, split.length);
		Filler bucketFillCommand = new Filler(params);
		canvas.execute(bucketFillCommand);

        Assert.assertEquals(canvas.render(),
                "----------------------\n" +
                        "|             ooooo  |\n" +
                        "|             o   o  |\n" +
                        "|             ooooo  |\n" +
                        "|                    |\n" +
                        "----------------------");

    }

    // fill a rectangle content
    @Test
    public void addEntity15() throws Exception {
        
        String cmd = "R 14 1 18 4";
		String[] split = cmd.split(" ");
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Rectangle rectangleCommand = new Rectangle(params);
		canvas.execute(rectangleCommand);
        
		cmd = "B 15 2 o";
		split = cmd.split(" ");
		params = Arrays.copyOfRange(split, 1, split.length);
		Filler bucketFillCommand = new Filler(params);
		canvas.execute(bucketFillCommand);
		
        Assert.assertEquals(canvas.render(),
                "----------------------\n" +
                        "|             xxxxx  |\n" +
                        "|             xooox  |\n" +
                        "|             xooox  |\n" +
                        "|             xxxxx  |\n" +
                        "----------------------");

    }

    //fill blank space
    @Test
    public void addEntity16() throws Exception {
        
        String cmd = "R 14 1 18 4";
		String[] split = cmd.split(" ");
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Rectangle rectangleCommand = new Rectangle(params);
		canvas.execute(rectangleCommand);
        
		cmd = "B 10 1 o";
		split = cmd.split(" ");
		params = Arrays.copyOfRange(split, 1, split.length);
		Filler bucketFillCommand = new Filler(params);
		canvas.execute(bucketFillCommand);

        Assert.assertEquals(canvas.render(),
                "----------------------\n" +
                        "|oooooooooooooxxxxx  |\n" +
                        "|ooooooooooooox   x  |\n" +
                        "|ooooooooooooox   x  |\n" +
                        "|oooooooooooooxxxxx  |\n" +
                        "----------------------");
    }

    /*L 1 2 1 22
    L 6 3 166 3
    R 14 1 18 3
    B 1 3 o*/


    // Add multiple model to canvas
	@Test
	public void addEntity5() throws Exception {

		String cmd = "L 1 2 6 2";
		String[] split = cmd.split(" ");
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		Line drawLineCommand = new Line(params);
		canvas.execute(drawLineCommand);

		cmd = "L 6 3 6 4";
		split = cmd.split(" ");
		params = Arrays.copyOfRange(split, 1, split.length);
		drawLineCommand = new Line(params);
		canvas.execute(drawLineCommand);

        Assert.assertEquals(canvas.render(),
                "----------------------\n" +
                        "|                    |\n" +
                        "|xxxxxx              |\n" +
                        "|     x              |\n" +
                        "|     x              |\n" +
                        "----------------------");
        
        cmd = "R 14 1 18 3";
		split = cmd.split(" ");
		params = Arrays.copyOfRange(split, 1, split.length);
		Rectangle rectangleCommand = new Rectangle(params);
		canvas.execute(rectangleCommand);

       
        Assert.assertEquals(canvas.render(),
                "----------------------\n" +
                        "|             xxxxx  |\n" +
                        "|xxxxxx       x   x  |\n" +
                        "|     x       xxxxx  |\n" +
                        "|     x              |\n" +
                        "----------------------");
        
        cmd = "B 10 3 o";
		split = cmd.split(" ");
		params = Arrays.copyOfRange(split, 1, split.length);
		Filler bucketFillCommand = new Filler(params);
		canvas.execute(bucketFillCommand);

        Assert.assertEquals(canvas.render(),
                "----------------------\n" +
                        "|oooooooooooooxxxxxoo|\n" +
                        "|xxxxxxooooooox   xoo|\n" +
                        "|     xoooooooxxxxxoo|\n" +
                        "|     xoooooooooooooo|\n" +
                        "----------------------");
    }
}
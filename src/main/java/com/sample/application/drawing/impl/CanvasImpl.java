package com.sample.application.drawing.impl;

/**
 * Provides the business implementation for various operations
 * @author sidonepudi
 */
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sample.application.drawing.Canvas;
import com.sample.application.drawing.exception.InvalidInputException;
import com.sample.application.drawing.model.Create;
import com.sample.application.drawing.model.Filler;
import com.sample.application.drawing.model.Line;
import com.sample.application.drawing.model.Point;
import com.sample.application.drawing.model.Quit;
import com.sample.application.drawing.model.Rectangle;
import com.sample.application.drawing.util.Constants;

public class CanvasImpl implements Canvas {

	private char[][] canvasArray;
	private int width;
	private int height;
	private String horizontalEdge;
	private boolean isShutdown = false;

	public CanvasImpl() {

	}

	private void init(Create createCommand) {
		width = createCommand.getWidth();
		height = createCommand.getHeight();

		canvasArray = new char[this.height][this.width];
		Arrays.stream(canvasArray).forEach(chars -> Arrays.fill(chars, Constants.SPACE_CHAR));

		horizontalEdge = Stream.generate(() -> String.valueOf(Constants.HORIZONTAL_EDGE_CHAR)).limit(width + 2)
				.collect(Collectors.joining());
	}

	@Override
	public void execute(Command command) throws InvalidInputException {
		if (command instanceof Quit) {
			isShutdown = true;
		} else if (command instanceof Create) {
			init((Create) command);
		} else if (canvasArray == null) {
			throw new InvalidInputException("create canvas first");
		} else if (command instanceof Line) {
			addLine((Line) command);
		} else if (command instanceof Rectangle) {
			addRectangle((Rectangle) command);
		} else if (command instanceof Filler) {
			addBucketFill((Filler) command);
		} else {
			throw new InvalidInputException("Processing error");
		}
	}

	@Override
	public String render() {
		StringBuilder builder = new StringBuilder();
		builder.append(horizontalEdge).append("\n");
		for (int i = 0; i < this.height; i++) {
			builder.append(Constants.VERTICAL_EDGE_CHAR);
			for (int j = 0; j < this.width; j++) {
				builder.append(canvasArray[i][j]);
			}
			builder.append(Constants.VERTICAL_EDGE_CHAR);
			builder.append("\n");
		}
		builder.append(horizontalEdge);
		return builder.toString();
	}

	private void addBucketFill(Filler bucketFill) throws InvalidInputException {
		if (isOutside(bucketFill.getX(), bucketFill.getY())) {
			throw new InvalidInputException("Bucket fill point is outside of canvas");
		}
		fillBucket(bucketFill.getX(), bucketFill.getY(), bucketFill.getCharacter());
	}

	private void addRectangle(Rectangle rec) throws InvalidInputException {
		if (isOutside(rec.getX1(), rec.getY1())) {
			throw new InvalidInputException("Rectangle is outside of canvas");
		}
		drawRectangle(rec.getX1(), rec.getY1(), rec.getX2(), rec.getY2());
	}

	private void addLine(Line line) throws InvalidInputException {
		if (isOutside(line.getX1(), line.getY1())) {
			throw new InvalidInputException("Line is outside of canvas");
		}

		// trim the outside edges
		if (line.getX2() >= width) {
			line.setX2(width);
		}
		if (line.getY2() >= height) {
			line.setY2(height);
		}
		drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
	}

	private void drawLine(int x1, int y1, int x2, int y2) {
		// row by row
		for (int row = y1 - 1; row <= y2 - 1 && row < height; row++) {
			// col by col
			for (int col = x1 - 1; col <= x2 - 1 && col < width; col++) {
				canvasArray[row][col] = Constants.LINE_CHAR;
			}
		}
	}

	private void fillBucket(int x, int y, char mchar) throws InvalidInputException {
		char originalChar = canvasArray[y - 1][x - 1];
		Stack<Point> stack = new Stack<>();
		stack.add(new Point(y - 1, x - 1));
		while (!stack.isEmpty()) {
			Point pop = stack.pop();
			if (canvasArray[pop.getX()][pop.getY()] == originalChar) {
				canvasArray[pop.getX()][pop.getY()] = mchar;
			}
			if (pop.getX() - 1 >= 0 && canvasArray[pop.getX() - 1][pop.getY()] == originalChar) {
				stack.add(new Point(pop.getX() - 1, pop.getY()));
			}
			if (pop.getX() + 1 < height && canvasArray[pop.getX() + 1][pop.getY()] == originalChar) {
				stack.add(new Point(pop.getX() + 1, pop.getY()));
			}
			if (pop.getY() - 1 >= 0 && canvasArray[pop.getX()][pop.getY() - 1] == originalChar) {
				stack.add(new Point(pop.getX(), pop.getY() - 1));
			}
			if (pop.getY() + 1 < width && canvasArray[pop.getX()][pop.getY() + 1] == originalChar) {
				stack.add(new Point(pop.getX(), pop.getY() + 1));
			}
		}
	}

	private void drawRectangle(int x1, int y1, int x2, int y2) {
		// top
		drawLine(x1, y1, x2, y1);
		// right
		drawLine(x1, y1, x1, y2);
		// bottom
		drawLine(x2, y1, x2, y2);
		// right
		drawLine(x1, y2, x2, y2);
	}

	private boolean isOutside(int x, int y) {
		return x > width || y > height;
	}

	@Override
	public boolean shutdown() {
		return isShutdown;
	}
}

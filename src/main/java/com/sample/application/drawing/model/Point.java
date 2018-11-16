package com.sample.application.drawing.model;

/**
 * Validates & holds the input  data
 * @author sidonepudi
 */
import com.sample.application.drawing.exception.InvalidInputException;
import com.sample.application.drawing.util.DrawingUtils;

public class Point {
	private int x;
	private int y;

	public Point(int x, int y) throws InvalidInputException {
		DrawingUtils.shouldAllNonNegative(x, y);
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
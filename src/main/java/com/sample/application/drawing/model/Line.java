package com.sample.application.drawing.model;

/**
 * Validates & holds the input  data
 * @author sidonepudi
 */
import com.sample.application.drawing.exception.InvalidInputException;
import com.sample.application.drawing.impl.Command;
import com.sample.application.drawing.util.Constants;
import com.sample.application.drawing.util.DrawingUtils;

public class Line implements Command {

	private int x1;
	private int y1;
	private int x2;
	private int y2;

	public Line(String... params) throws InvalidInputException {
		if (params.length != 4)
			throw new InvalidInputException("Draw line command expects 4 params" + "\n" + Constants.LINE_HELP);
		try {
			x1 = DrawingUtils.toPositiveInt(params[0]);
			y1 = DrawingUtils.toPositiveInt(params[1]);
			x2 = DrawingUtils.toPositiveInt(params[2]);
			y2 = DrawingUtils.toPositiveInt(params[3]);
		} catch (InvalidInputException e) {
			throw new InvalidInputException(e.getMessage() + "\n" + Constants.LINE_HELP);
		}
		if (x1 != x2 && y1 != y2) {
			throw new InvalidInputException(
					"Draw line does not support diagonal line at the moment" + "\n" + Constants.LINE_HELP);
		}
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x1;
		result = prime * result + x2;
		result = prime * result + y1;
		result = prime * result + y2;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Line other = (Line) obj;
		if (x1 != other.x1)
			return false;
		if (x2 != other.x2)
			return false;
		if (y1 != other.y1)
			return false;
		if (y2 != other.y2)
			return false;
		return true;
	}

}
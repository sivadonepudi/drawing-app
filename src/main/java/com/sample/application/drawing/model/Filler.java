package com.sample.application.drawing.model;

/**
 * Validates & holds the input  data
 * @author sidonepudi
 */
import com.sample.application.drawing.exception.InvalidInputException;
import com.sample.application.drawing.impl.Command;
import com.sample.application.drawing.util.Constants;
import com.sample.application.drawing.util.DrawingUtils;

public class Filler implements Command {

	private int x;
	private int y;
	private char character;

	public Filler(String... params) throws InvalidInputException {
		if (params.length != 3) {
			throw new InvalidInputException("Bucket fill expects 3 params" + "\n" + Constants.FILLER_HELP);
		}
		try {
			x = DrawingUtils.toPositiveInt(params[0]);
			y = DrawingUtils.toPositiveInt(params[1]);
			character = params[2].charAt(0);
		} catch (InvalidInputException e) {
			throw new InvalidInputException(e.getMessage() + "\n" + Constants.FILLER_HELP);
		}
	}

	public int getX() {
		return x;
	}

	public Filler setX(int x) {
		this.x = x;
		return this;
	}

	public int getY() {
		return y;
	}

	public Filler setY(int y) {
		this.y = y;
		return this;
	}

	public char getCharacter() {
		return character;
	}

	public Filler setCharacter(char character) {
		this.character = character;
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + character;
		result = prime * result + x;
		result = prime * result + y;
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
		Filler other = (Filler) obj;
		if (character != other.character)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
package com.sample.application.drawing.model;

/**
 * Validates & holds the input  data
 * @author sidonepudi
 */
import com.sample.application.drawing.exception.InvalidInputException;
import com.sample.application.drawing.impl.Command;
import com.sample.application.drawing.util.Constants;
import com.sample.application.drawing.util.DrawingUtils;

public class Create implements Command {

	private int height;
	private int width;

	public Create(String... params) throws InvalidInputException {
		if (params.length != 2)
			throw new InvalidInputException("Create command expects 2 params" + "\n" + Constants.CREATE_HELP);
		try {
			width = DrawingUtils.toPositiveInt(params[0]);
			height = DrawingUtils.toPositiveInt(params[1]);
		} catch (InvalidInputException e) {
			throw new InvalidInputException(e.getMessage() + "\n" + Constants.CREATE_HELP);
		}
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
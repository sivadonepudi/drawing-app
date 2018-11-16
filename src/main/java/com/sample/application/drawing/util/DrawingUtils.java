package com.sample.application.drawing.util;

/**
 * Place holder for utility operations 
 * @author sidonepudi
 */
import com.sample.application.drawing.exception.InvalidInputException;

public class DrawingUtils {
	public static int toPositiveInt(String input) throws InvalidInputException {
		try {
			int i = Integer.parseInt(input);
			if (i <= 0) {
				throw new InvalidInputException(i + " is not valid, expecting +ve value");
			}
			return i;
		} catch (NumberFormatException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}

	public static void shouldAllPositive(int... numbers) throws InvalidInputException {
		for (int num : numbers) {
			if (num < 1) {
				throw new InvalidInputException("Number should be > 0");
			}
		}
	}

	public static void shouldAllNonNegative(int... numbers) throws InvalidInputException {
		for (int num : numbers) {
			if (num < 0) {
				throw new InvalidInputException("Number should be > 0");
			}
		}
	}
}

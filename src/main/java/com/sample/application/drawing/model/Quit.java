/**
 * 
 */
package com.sample.application.drawing.model;

import com.sample.application.drawing.exception.InvalidInputException;
import com.sample.application.drawing.impl.Command;
import com.sample.application.drawing.util.Constants;

/**
 * @author sidonepudi
 *
 */
public class Quit implements Command {
	public Quit(String... params) throws InvalidInputException {
		if (params.length != 0) {
			throw new InvalidInputException("Invalid syntax" + "\n" + Constants.QUIT_HELP);
		}
	}
}

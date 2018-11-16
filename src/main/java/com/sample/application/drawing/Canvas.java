package com.sample.application.drawing;

/**
 * Exposes the business operations
 * @author sidonepudi
 */

import com.sample.application.drawing.exception.InvalidInputException;
import com.sample.application.drawing.impl.Command;

public interface Canvas {

	/**
	 * Executes the requested operation
	 * 
	 * @param command
	 * @throws InvalidInputException
	 */
	void execute(Command command) throws InvalidInputException;

	/**
	 * Renders the output
	 */
	String render();

	/**
	 * Check whether to continue running the application
	 * 
	 * @return
	 */
	boolean shutdown();
}

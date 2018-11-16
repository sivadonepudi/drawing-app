package com.sample.application.drawing.impl;

/**
 * Singleton, factory for command handlers
 * @author sidonepudi
 */
import java.util.Arrays;

import com.sample.application.drawing.exception.InvalidInputException;
import com.sample.application.drawing.model.Create;
import com.sample.application.drawing.model.Filler;
import com.sample.application.drawing.model.Line;
import com.sample.application.drawing.model.Quit;
import com.sample.application.drawing.model.Rectangle;

public class DrawingFactory {
	private static DrawingFactory factory;

	private DrawingFactory() {
	}

	/**
	 * returns the Singleton instance
	 * 
	 * @return CommandHandlerFactory
	 */
	public static DrawingFactory getInstance() {
		if (factory == null) {
			factory = new DrawingFactory();
		}
		return factory;
	}

	/**
	 * Provides the handler for given command
	 * 
	 * @param commandLine
	 * @return
	 * @throws InvalidInputException
	 */
	public Command getCommand(String commandLine) throws InvalidInputException {
		String[] split = commandLine.split(" ");
		String mainCommand = split[0].toUpperCase();
		String[] params = Arrays.copyOfRange(split, 1, split.length);
		switch (mainCommand) {
		case "C":
			return new Create(params);
		case "L":
			return new Line(params);
		case "R":
			return new Rectangle(params);
		case "B":
			return new Filler(params);
		case "Q":
			return new Quit(params);
		default:
			throw new InvalidInputException(mainCommand + " is not a valid command");
		}
	}
}
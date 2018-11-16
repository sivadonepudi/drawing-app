package com.sample.application.drawing;

import static java.lang.System.out;

import java.util.Scanner;

import com.sample.application.drawing.exception.InvalidInputException;
import com.sample.application.drawing.impl.CanvasImpl;
import com.sample.application.drawing.impl.Command;
import com.sample.application.drawing.impl.DrawingFactory;

/**
 * simple console version of a drawing program.
 * 
 * @author sidonepudi
 *
 */
public class DrawingApplication {
	private static Canvas canvas;

	/**
	 * Reads & executes the commands
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		DrawingApplication mySelf = new DrawingApplication();
		Scanner scanner = new Scanner(System.in);
		canvas = new CanvasImpl();
		String input = "";
		while (!canvas.shutdown()) {
			out.print("enter command: ");
			input = scanner.nextLine();
			mySelf.process(input);
		}
		scanner.close();
	}

	/**
	 * Validates the input command & renders output to console
	 * 
	 * @param commandLine
	 */
	private void process(String commandLine) {
		try {
			if (commandLine.trim().length() < 1) {
				return;
			}
			Command command = DrawingFactory.getInstance().getCommand(commandLine);
			canvas.execute(command);
			if (!canvas.shutdown()) {
				out.println(canvas.render());
			}
		} catch (InvalidInputException e) {
			out.println(e.getMessage());
		}
	}
}

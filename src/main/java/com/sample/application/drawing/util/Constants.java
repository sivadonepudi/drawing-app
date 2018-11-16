package com.sample.application.drawing.util;

public class Constants {
	public static final char HORIZONTAL_EDGE_CHAR = '-';
	public static final char VERTICAL_EDGE_CHAR = '|';
	public static final char LINE_CHAR = 'x';
	public static final char SPACE_CHAR = ' ';
	public static final String CREATE_HELP = "C w h           Should create a new canvas of width w and height h. w, h should be > 0";
	public static final String FILLER_HELP = "B x y c         Should fill the entire area connected to (x,y) with \"colour\" c. The\n"
			+ "                behaviour of this is the same as that of the \"bucket fill\" tool in paint\n"
			+ "                programs.";
	public static final String LINE_HELP = "L x1 y1 x2 y2   Should create a new line from (x1,y1) to (x2,y2). Currently only\n"
			+ "                horizontal or vertical lines are supported. Horizontal and vertical lines\n"
			+ "                will be drawn using the 'x' character.";
	public static final String RECTANGLE_HELP = "R x1 y1 x2 y2   Should create a new rectangle, whose upper left corner is (x1,y1) and\n"
			+ "                lower right corner is (x2,y2). Horizontal and vertical lines will be drawn\n"
			+ "                using the 'x' character.";
	public static final String QUIT_HELP = "Q                exit command";
}

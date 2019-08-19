package it.daguanno.sudoku.validator.commons.utils;

public class StringUtils {
	
	private StringUtils() {
		// TODO Auto-generated constructor stub
	}
	
	//a simple null or empty string check
	public static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

}

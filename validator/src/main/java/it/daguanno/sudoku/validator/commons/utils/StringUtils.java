package it.daguanno.sudoku.validator.commons.utils;

import static java.lang.Character.isDigit;

public class StringUtils {
	
	//a simple numerical control
	public static boolean isNumeric(final CharSequence cs) {
		if (isEmpty(cs)) {
			return false;
		}
		final int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (!isDigit(cs.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	//a simple null or empty string check
	public static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

}

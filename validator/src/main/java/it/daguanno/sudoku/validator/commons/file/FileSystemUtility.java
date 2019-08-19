package it.daguanno.sudoku.validator.commons.file;

import java.io.File;

public class FileSystemUtility {

	public static void createIfNotExist(File logDir) {
		if (!(logDir.exists())) {
			logDir.mkdir();
		}
	}
}

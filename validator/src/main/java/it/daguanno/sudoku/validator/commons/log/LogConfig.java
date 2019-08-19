package it.daguanno.sudoku.validator.commons.log;

import static java.util.logging.Level.ALL;
import static it.daguanno.sudoku.validator.commons.file.FileSystemUtility.createIfNotExist;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class LogConfig {

	public final static String 	DATE_FORMAT		= "dd-MM-yyyy"	;
	public final static String 	LOG_FOLDER		= "logs/"		;
	public final static String 	FILE_NAME		= "sudoku"		;
	public final static String 	SEPARATOR		= "_"			;
	public final static String 	FILE_EXTENSION	= ".log"		;
	
	private LogConfig() {
		// TODO Auto-generated constructor stub
	}
	
	//quick and dirty log config
	public static void execute() throws SecurityException, IOException {
		Logger rootLogger = Logger.getLogger("");
		manageLogsFolder();
		manageFileLog(rootLogger);
		manageLogLevel(rootLogger);
	}
	
	//log level forced to all
	private static void manageLogLevel(Logger rootLogger) {
		for (Handler handler : rootLogger.getHandlers()) {
			handler.setLevel(ALL);
		}
		rootLogger.setLevel(ALL);
	}
	
	//file generator, file name like sudoku_17-08-2019_561a921d-39d6-4ca2-a7fb-19c59057043c.log
	private static void manageFileLog(Logger rootLogger) throws IOException {
		UUID uuid = UUID.randomUUID();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		
		StringBuilder fileName = new StringBuilder()
									.append(LOG_FOLDER)
									.append(FILE_NAME)
									.append(SEPARATOR)
									.append( formatter.format(date) )
									.append(SEPARATOR)
									.append(uuid)
									.append(FILE_EXTENSION);
		
		FileHandler file = new FileHandler(fileName.toString());
		file.setFormatter(new SimpleFormatter());
		rootLogger.addHandler(file);
	}
	
	//dir log generator
	private static void manageLogsFolder() {
		File logDir = new File(LOG_FOLDER);
		createIfNotExist(logDir);
	}


}

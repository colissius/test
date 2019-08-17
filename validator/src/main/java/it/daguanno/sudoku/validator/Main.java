package it.daguanno.sudoku.validator;

import static it.daguanno.sudoku.validator.commons.costants.IConstants.END;
import static it.daguanno.sudoku.validator.commons.costants.IConstants.EXCEPTION;
import static it.daguanno.sudoku.validator.commons.costants.IConstants.START;
import static it.daguanno.sudoku.validator.commons.exceptions.SudokuException.EnumSudokuException.NO_ARGS;
import static it.daguanno.sudoku.validator.commons.exceptions.SudokuException.EnumSudokuException.TOO_MANY_INFO;
import static it.daguanno.sudoku.validator.commons.utils.StringUtils.isEmpty;
import static java.util.logging.Level.SEVERE;

import java.util.logging.Logger;

import it.daguanno.sudoku.validator.application.SudokuFacade;
import it.daguanno.sudoku.validator.application.model.impl.FileSystemContext;
import it.daguanno.sudoku.validator.commons.exceptions.SudokuException;
import it.daguanno.sudoku.validator.commons.log.LogConfig;

public class Main {
	
	private static final Logger logger = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) throws Exception {
		//execute base log config
		LogConfig.execute();
		logger.info(START);
		int endValue = 0;
		try {
			//args check
			argsValidation(args); 
			//start logic
			SudokuFacade.build().executeSudokuValidation(new FileSystemContext(args[0]));
		} catch (Exception e) {
			endValue = 1;
			logger.log(SEVERE, EXCEPTION, e);
		} finally {
			logger.info(END);
			System.exit(endValue);
		}
	}

	private static void argsValidation(String[] args) throws SudokuException {
		if(args == null || args.length == 0 || isEmpty(args[0])) {
			throw new SudokuException(NO_ARGS);
		} else if(args.length > 1) {
			throw new SudokuException(TOO_MANY_INFO);
		}
	}

}

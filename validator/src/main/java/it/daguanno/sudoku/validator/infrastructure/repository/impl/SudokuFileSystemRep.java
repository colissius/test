package it.daguanno.sudoku.validator.infrastructure.repository.impl;

import static it.daguanno.sudoku.validator.commons.costants.IConstants.END;
import static it.daguanno.sudoku.validator.commons.costants.IConstants.START;
import static it.daguanno.sudoku.validator.commons.exceptions.SudokuException.EnumSudokuException.NO_CONFIG;
import static it.daguanno.sudoku.validator.commons.exceptions.SudokuException.EnumSudokuException.NO_FILE;
import static it.daguanno.sudoku.validator.commons.exceptions.SudokuException.EnumSudokuException.PARSING_ERROR;
import static it.daguanno.sudoku.validator.commons.exceptions.SudokuException.EnumSudokuException.TOO_FEW_COLUMNS;
import static it.daguanno.sudoku.validator.commons.exceptions.SudokuException.EnumSudokuException.TOO_FEW_ROWS;
import static it.daguanno.sudoku.validator.commons.exceptions.SudokuException.EnumSudokuException.TOO_MANY_COLUMNS;
import static it.daguanno.sudoku.validator.commons.exceptions.SudokuException.EnumSudokuException.TOO_MANY_ROWS;
import static it.daguanno.sudoku.validator.commons.utils.StringUtils.isEmpty;
import static java.nio.charset.Charset.defaultCharset;
import static java.util.Arrays.binarySearch;
import static java.util.Arrays.sort;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;
import static java.lang.Character.isDigit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Logger;

import it.daguanno.sudoku.validator.application.model.impl.FileSystemContext;
import it.daguanno.sudoku.validator.commons.data.SudokuMatrixDTO;
import it.daguanno.sudoku.validator.commons.exceptions.SudokuException;
import it.daguanno.sudoku.validator.infrastructure.repository.ISudokuRepository;

public class SudokuFileSystemRep implements ISudokuRepository<FileSystemContext> {

	private static final Logger logger = Logger.getLogger(SudokuFileSystemRep.class.getName());
	
	
	/*
	
	the files should be like
	 
	7,3,5,6,1,4,8,9,2\n
	8,4,2,9,7,3,5,6,1\n
	9,6,1,2,8,5,3,7,4\n
	2,8,6,3,4,9,1,5,7\n
	4,1,3,8,5,7,9,2,6\n
	5,7,9,1,2,6,4,3,8\n
	1,5,7,4,9,2,6,8,3\n
	6,9,4,7,3,8,2,1,5\n
	3,2,8,5,6,1,7,4,9\n

	 or
	 
	7,3,5,6,1,4,8,9,2\n\r
	8,4,2,9,7,3,5,6,1\n\r
	9,6,1,2,8,5,3,7,4\n\r
	2,8,6,3,4,9,1,5,7\n\r
	4,1,3,8,5,7,9,2,6\n\r
	5,7,9,1,2,6,4,3,8\n\r
	1,5,7,4,9,2,6,8,3\n\r
	6,9,4,7,3,8,2,1,5\n\r
	3,2,8,5,6,1,7,4,9\n\r
	
	or
	 
	7,3,5,6,1,4,8,9,2\n\r
	8,4,2,9,7,3,5,6,1\n\r
	9,6,1,2,8,5,3,7,4\n\r
	2,8,6,3,4,9,1,5,7\n\r
	4,1,3,8,5,7,9,2,6\n\r
	5,7,9,1,2,6,4,3,8\n\r
	1,5,7,4,9,2,6,8,3\n\r
	6,9,4,7,3,8,2,1,5\n\r
	3,2,8,5,6,1,7,4,9 
	 
	 */
	
	// during the reading I perform a validation
	public SudokuMatrixDTO extract(FileSystemContext context) throws Exception {
		logger.info(START);
		//reading resources 
		Reader buffer = getBuffer(getFile(context));
		//check config
		checkConfig(context);
		//set parsing config
		char rowSeparator = context.getFsConfig().getRowSeparator();
		char colSeparator = context.getFsConfig().getColumnSeparator();
		short columnsNum = context.getFsConfig().getColumnsNum();
		short rowsNum = context.getFsConfig().getColumnsNum();
		char[] escape = context.getFsConfig().getEscape();
		Short matrix[][] = new Short[9][9];
		short row = 0;
		short col = 0;
		int r;
		
		//for binarySearch
		sort(escape);
		
		char ch = 0;
		int numCount = 0;
		
		boolean isLastNumeric = false;
		while ((r = buffer.read()) != -1) {
			ch = (char) r;
			
			if(col >= columnsNum) {
				throw new SudokuException(TOO_MANY_COLUMNS);
			}
			
			if(row >= rowsNum) {
				throw new SudokuException(TOO_MANY_ROWS);
			}
			
			if(isDigit(ch) && !isLastNumeric) {
				isLastNumeric = true;
				matrix[row][col] = Short.valueOf(String.valueOf(ch));
				numCount++;
			} else if(ch == colSeparator && isLastNumeric) {
				isLastNumeric = false;
				col++;
			} else if(ch == rowSeparator && isLastNumeric) {
				isLastNumeric = false;
				row++;
				if(col+1 < columnsNum) {
					throw new SudokuException(TOO_FEW_COLUMNS);
				}
				col = 0;
			} else if(binarySearch(escape, ch) == -1){
				//binarySearch
				//Searches the specified array of chars for the specified value using 
				//thebinary search algorithm. The array must be sorted (asby the 
				//sort(char []) method) prior to making this call. If itis not sorted, 
				//the results are undefined. If the array containsmultiple elements 
				//with the specified value, there is no guarantee whichone will be found.
				logger.log(SEVERE, "error char : [{0}] char to int : [{1}]", new Object[]{ch, (int)ch});
				throw new SudokuException(PARSING_ERROR);
			}
		}
		if(numCount == 81 && isDigit(ch) && row < rowsNum-1) {
			throw new SudokuException(TOO_FEW_ROWS);
		} else if(!isDigit(ch) && row < rowsNum) {
			throw new SudokuException(TOO_FEW_ROWS);
		}
		
		buffer.close();
		
		SudokuMatrixDTO result = new SudokuMatrixDTO();
		result.setMatrix(matrix);
		logger.info(END);
		return result;
	}

	private void checkConfig(FileSystemContext context) throws SudokuException {
		//config check
		if(context.getFsConfig() == null) {
			throw new SudokuException(NO_CONFIG);
		}
	}

	private File getFile(FileSystemContext context) throws SudokuException {
		//file check
		if (isEmpty(context.getFileName())) {
			throw new SudokuException(NO_FILE);
		}
		File file = new File(context.getFileName());

		if (!file.exists()) {
			throw new SudokuException(NO_FILE);
		}
		return file;
	}

	private BufferedReader getBuffer(File file) throws FileNotFoundException {
		//i read char by char
		logger.log(INFO, "{0} : {1}",new Object[]{"Charset ", defaultCharset() });
		return new BufferedReader(new InputStreamReader(new FileInputStream(file), defaultCharset()));
	}

}

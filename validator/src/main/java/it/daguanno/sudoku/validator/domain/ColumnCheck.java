package it.daguanno.sudoku.validator.domain;

import static it.daguanno.sudoku.validator.commons.costants.IConstants.END;
import static it.daguanno.sudoku.validator.commons.costants.IConstants.START;
import static it.daguanno.sudoku.validator.domain.IEngineConstants.SUDOKU_COLUMNS;
import static it.daguanno.sudoku.validator.domain.IEngineConstants.SUDOKU_ROWS;
import static java.util.logging.Level.FINE;

import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import it.daguanno.sudoku.validator.commons.data.SudokuMatrixDTO;

public class ColumnCheck extends CheckChain {

	protected static final Logger logger = Logger.getLogger(ColumnCheck.class.getName());
	
	public ColumnCheck() {
	}

	public ColumnCheck(CheckChain next) {
		super.setNext(next);
	}
	
	
	@Override
	//check that in the sudoku columns there 
	//are no values ​​repeated several times
	public int execute(SudokuMatrixDTO dto) throws Exception {
		logger.info(START);
		Short matrix[][] = dto.getMatrix();
		for(int y = 0; y < SUDOKU_COLUMNS ; y++ ) {
			Set<Short> setCheck = new TreeSet<Short>();
			for(int x = 0; x < SUDOKU_ROWS ; x++ ) {
				Short currVal =  matrix[x][y];
				logger.log(FINE, "column {0} row {1} value {2}",new Object[]{y,x,currVal});
				if(setCheck.contains(currVal)) {
					return 1;
				} else {
					setCheck.add(currVal);
				}
			}
		}
		logger.info(END);
		return 0;
	}

}

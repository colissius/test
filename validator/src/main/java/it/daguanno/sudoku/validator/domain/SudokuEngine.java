package it.daguanno.sudoku.validator.domain;

import static it.daguanno.sudoku.validator.commons.costants.IConstants.END;
import static it.daguanno.sudoku.validator.commons.costants.IConstants.START;
import static it.daguanno.sudoku.validator.domain.IEngineConstants.SUDOKU_COLUMNS;
import static it.daguanno.sudoku.validator.domain.IEngineConstants.SUDOKU_ROWS;

import it.daguanno.sudoku.validator.commons.data.SudokuMatrixDTO;

public class SudokuEngine extends CheckChain {
	
	public SudokuEngine(CheckChain next) {
		super.setNext(next);
	}
	
	protected int execute(SudokuMatrixDTO dto) throws Exception {
		//Null object pattern		
		logger.info(START);
		Short matrix[][] = dto.getMatrix();
		for(int y = 0; y < SUDOKU_COLUMNS ; y++ ) {
			for(int x = 0; x < SUDOKU_ROWS ; x++ ) {
				Short currVal =  matrix[x][y];
				if(currVal <= 0 || currVal > 9) {
					return 1;
				}
			}
		}
		logger.info(END);
		return 0;
	}
	
}

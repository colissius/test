package it.daguanno.sudoku.validator.domain;

import it.daguanno.sudoku.validator.commons.data.SudokuMatrixDTO;

public class SudokuEngine extends CheckChain {
	
	public SudokuEngine() {
	}

	public SudokuEngine(CheckChain next) {
		super.setNext(next);
	}
	
	protected int execute(SudokuMatrixDTO dto) throws Exception {
		//Null object pattern		
		return 0;
	}
	
}

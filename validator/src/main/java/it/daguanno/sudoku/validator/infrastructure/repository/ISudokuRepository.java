package it.daguanno.sudoku.validator.infrastructure.repository;

import it.daguanno.sudoku.validator.application.model.IContext;
import it.daguanno.sudoku.validator.commons.data.SudokuMatrixDTO;

public interface ISudokuRepository<T extends IContext> {

	public SudokuMatrixDTO extract(T context) throws Exception;
	
}

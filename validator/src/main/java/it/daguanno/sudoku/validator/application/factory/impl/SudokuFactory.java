package it.daguanno.sudoku.validator.application.factory.impl;

import static it.daguanno.sudoku.validator.application.model.IContextConfig.FILE_SYS_CHAR_ESCAPE;
import static it.daguanno.sudoku.validator.application.model.IContextConfig.FILE_SYS_COLUMN_SEPARATOR;
import static it.daguanno.sudoku.validator.application.model.IContextConfig.FILE_SYS_ROW_SEPARATOR;
import static it.daguanno.sudoku.validator.domain.IEngineConstants.SUDOKU_COLUMNS;
import static it.daguanno.sudoku.validator.domain.IEngineConstants.SUDOKU_ROWS;

import it.daguanno.sudoku.validator.application.model.impl.FileSystemConfig;
import it.daguanno.sudoku.validator.application.model.impl.FileSystemContext;
import it.daguanno.sudoku.validator.domain.CheckChain;
import it.daguanno.sudoku.validator.domain.ColumnCheck;
import it.daguanno.sudoku.validator.domain.Matrix3x3Check;
import it.daguanno.sudoku.validator.domain.RowCheck;
import it.daguanno.sudoku.validator.domain.SudokuEngine;
import it.daguanno.sudoku.validator.infrastructure.repository.ISudokuRepository;
import it.daguanno.sudoku.validator.infrastructure.repository.impl.SudokuFileSystemRep;

public class SudokuFactory {

	private SudokuFactory() {
	}
	
	public static SudokuFactory build() {
		return new SudokuFactory();
	}

	public SudokuEngine retrieveSudokuEngine() {
		CheckChain chain = new RowCheck(new ColumnCheck(new Matrix3x3Check()));
		SudokuEngine engine = new SudokuEngine(chain);
		return engine;
	}
	
	public ISudokuRepository<FileSystemContext> retrieveFSRepository() {
		return new SudokuFileSystemRep();
	}
	
	public FileSystemConfig retrieveFSConfig() {
		return retrieveFSConfig(FILE_SYS_ROW_SEPARATOR,   
								FILE_SYS_COLUMN_SEPARATOR,
								FILE_SYS_CHAR_ESCAPE
								);
	}
	
	public FileSystemConfig retrieveFSConfig(	char rowSeparator,   
												char columnSeparator,
												char[] escape) {
		FileSystemConfig fsConfig = new FileSystemConfig();
		
		fsConfig.setColumnsNum(SUDOKU_COLUMNS)         ;
		fsConfig.setRowNum(SUDOKU_ROWS)                ;
		fsConfig.setRowSeparator(rowSeparator)         ;
		fsConfig.setColumnSeparator(columnSeparator)   ;
		fsConfig.setEscape(escape)                     ;
		
		return fsConfig;
	}
}

package it.daguanno.sudoku.validator;

import static it.daguanno.sudoku.validator.application.model.IContextConfig.FILE_SYS_COLUMN_SEPARATOR;
import static it.daguanno.sudoku.validator.application.model.IContextConfig.FILE_SYS_ROW_SEPARATOR;
import static it.daguanno.sudoku.validator.domain.IEngineConstants.SUDOKU_COLUMNS;
import static it.daguanno.sudoku.validator.domain.IEngineConstants.SUDOKU_ROWS;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.daguanno.sudoku.validator.application.factory.impl.SudokuFactory;
import it.daguanno.sudoku.validator.application.model.impl.FileSystemConfig;
import it.daguanno.sudoku.validator.domain.CheckChain;
import it.daguanno.sudoku.validator.domain.ColumnCheck;
import it.daguanno.sudoku.validator.domain.Matrix3x3Check;
import it.daguanno.sudoku.validator.domain.RowCheck;
import it.daguanno.sudoku.validator.domain.SudokuEngine;
import it.daguanno.sudoku.validator.infrastructure.repository.ISudokuRepository;

public class SudokuFactoryTest {

    @Test
	public void buildTest() {

    	Object sudokuFactory = SudokuFactory.build();
    	assertTrue(sudokuFactory != null);
    	assertTrue(sudokuFactory instanceof SudokuFactory);
    	
	}
    
    @Test
	public void retrieveSudokuEngineTest() {
    	
    	SudokuEngine engine = SudokuFactory.build().retrieveSudokuEngine();
    	assertTrue(engine != null);
    	assertTrue(engine.getNext() != null);
    	
    	CheckChain tmp = engine;
    	short rowCheckCount 		= 0; 
    	short columnCheckCount 		= 0; 
    	short matrix3x3CheckCount 	= 0; 
    	short classCount			= 0;
    	
    	while(tmp.getNext() != null) {

    		tmp = tmp.getNext();
        	if(tmp instanceof RowCheck) {
        		rowCheckCount++;
        	} else if(tmp instanceof ColumnCheck) {
        		columnCheckCount++;
        	} else if(tmp instanceof Matrix3x3Check) {
        		matrix3x3CheckCount++;
        	}
        	classCount++;
        	
        	assertTrue(tmp != null);
    	}
    	
    	assertTrue(rowCheckCount 		== 1);
    	assertTrue(columnCheckCount 	== 1);
    	assertTrue(matrix3x3CheckCount 	== 1);
    	assertTrue(classCount			== 3);
    	
	}
	
    @Test
	public void retrieveFSRepositoryTest() {
    	Object sudokuFsRep = SudokuFactory.build().retrieveFSRepository();
    	assertTrue(sudokuFsRep != null);
    	assertTrue(sudokuFsRep instanceof ISudokuRepository);
	}
    
    @Test
	public void retrieveFSConfigTest() {
    	Object obj = SudokuFactory.build().retrieveFSConfig();
    	assertTrue(obj != null);
    	assertTrue(obj instanceof FileSystemConfig);
    	FileSystemConfig fsConfig = (FileSystemConfig)obj;
    	
    	assertTrue(fsConfig.getColumnsNum() == SUDOKU_COLUMNS );
    	assertTrue(fsConfig.getRowNum() == SUDOKU_ROWS );
    	assertTrue(fsConfig.getRowSeparator() == FILE_SYS_ROW_SEPARATOR );
    	assertTrue(fsConfig.getColumnSeparator() == FILE_SYS_COLUMN_SEPARATOR );
    	assertTrue(fsConfig.getEscape() != null );
    	
	}

}

package it.daguanno.sudoku.validator;

import static it.daguanno.sudoku.validator.commons.exceptions.SudokuException.EnumSudokuException.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.daguanno.sudoku.validator.application.SudokuFacade;
import it.daguanno.sudoku.validator.application.factory.impl.SudokuFactory;
import it.daguanno.sudoku.validator.application.model.impl.FileSystemContext;
import it.daguanno.sudoku.validator.commons.exceptions.SudokuException;
import it.daguanno.sudoku.validator.domain.SudokuEngine;
import it.daguanno.sudoku.validator.infrastructure.repository.ISudokuRepository;

public class SudokuEngineTest {

	private final static String BASE_PATH = "src\\test\\resources\\";

	@Test
	public void buildTest() {

		SudokuEngine engine = new SudokuEngine(null);
		assertTrue(engine != null);
		assertTrue(engine.getNext() == null);

	}

	@Test
	public void validSodokuTest() throws Exception {

		int result = SudokuFacade.build().executeSudokuValidation(new FileSystemContext(BASE_PATH + "Valid.txt"));
		assertTrue(result == 0);

	}

	@Test
	public void tooManyRowsTest() throws Exception {
		Exception ex = null;
		try {
			SudokuFacade.build().executeSudokuValidation(new FileSystemContext(BASE_PATH + "tooManyRow.txt"));
		} catch (Exception e) {
			ex = e;
		}
		assertTrue(ex != null);
		assertTrue(ex instanceof SudokuException);
		assertTrue(TOO_MANY_ROWS.getMsg().equals(ex.getMessage()));

	}

	@Test
	public void tooManyColumnsTest() throws Exception {
		Exception ex = null;
		try {
			SudokuFacade.build().executeSudokuValidation(new FileSystemContext(BASE_PATH + "tooManyColumns.txt"));
		} catch (Exception e) {
			ex = e;
		}
		assertTrue(ex != null);
		assertTrue(ex instanceof SudokuException);
		assertTrue(TOO_MANY_COLUMNS.getMsg().equals(ex.getMessage()));

	}

	@Test
	public void tooFewColumnsTest() throws Exception {
		Exception ex = null;
		try {
			SudokuFacade.build().executeSudokuValidation(new FileSystemContext(BASE_PATH + "tooFewColumns.txt"));
		} catch (Exception e) {
			ex = e;
		}
		assertTrue(ex != null);
		assertTrue(ex instanceof SudokuException);
		assertTrue(TOO_FEW_COLUMNS.getMsg().equals(ex.getMessage()));

	}

	@Test
	public void invalidCharTest() throws Exception {
		Exception ex = null;
		try {
			SudokuFacade.build().executeSudokuValidation(new FileSystemContext(BASE_PATH + "InvalidChar.txt"));
		} catch (Exception e) {
			ex = e;
		}
		assertTrue(ex != null);
		assertTrue(ex instanceof SudokuException);
		assertTrue(PARSING_ERROR.getMsg().equals(ex.getMessage()));

	}

	@Test
	public void tooFewRowTest() throws Exception {

		Exception ex = null;
		try {
			SudokuFacade.build().executeSudokuValidation(new FileSystemContext(BASE_PATH + "tooFewRow.txt"));
		} catch (Exception e) {
			ex = e;
		}
		assertTrue(ex != null);
		assertTrue(ex instanceof SudokuException);
		assertTrue(TOO_FEW_ROWS.getMsg().equals(ex.getMessage()));

	}

	@Test
	public void noFileTest() throws Exception {

		Exception ex = null;
		try {
			SudokuFacade.build().executeSudokuValidation(new FileSystemContext(BASE_PATH + "no_file"));
		} catch (Exception e) {
			ex = e;
		}
		assertTrue(ex != null);
		assertTrue(ex instanceof SudokuException);
		assertTrue(NO_FILE.getMsg().equals(ex.getMessage()));

		ex = null;
		try {
			SudokuFacade.build().executeSudokuValidation(new FileSystemContext(BASE_PATH));
		} catch (Exception e) {
			ex = e;
		}
		assertTrue(ex != null);
		assertTrue(ex instanceof SudokuException);
		assertTrue(NO_FILE.getMsg().equals(ex.getMessage()));

		ex = null;
		try {
			SudokuFacade.build().executeSudokuValidation(new FileSystemContext(null));
		} catch (Exception e) {
			ex = e;
		}
		assertTrue(ex != null);
		assertTrue(ex instanceof SudokuException);
		assertTrue(NO_FILE.getMsg().equals(ex.getMessage()));

		ex = null;
		try {
			SudokuFacade.build().executeSudokuValidation(new FileSystemContext(""));
		} catch (Exception e) {
			ex = e;
		}
		assertTrue(ex != null);
		assertTrue(ex instanceof SudokuException);
		assertTrue(NO_FILE.getMsg().equals(ex.getMessage()));

	}

	@Test
	public void noFsConfigTest() throws Exception {

		Exception ex = null;
		try {
			FileSystemContext context = null;
			SudokuFactory factory = SudokuFactory.build();
			ISudokuRepository<FileSystemContext> repository = factory.retrieveFSRepository();
			repository.extract(context);
		} catch (Exception e) {
			ex = e;
		}
		assertTrue(ex != null);
		assertTrue(ex instanceof SudokuException);
		assertTrue(NO_CONFIG.getMsg().equals(ex.getMessage()));
		
		ex = null;
		try {
			FileSystemContext context = new FileSystemContext(BASE_PATH + "Valid.txt");
			SudokuFactory factory = SudokuFactory.build();
			ISudokuRepository<FileSystemContext> repository = factory.retrieveFSRepository();
			repository.extract(context);
		} catch (Exception e) {
			ex = e;
		}
		assertTrue(ex != null);
		assertTrue(ex instanceof SudokuException);
		assertTrue(NO_CONFIG.getMsg().equals(ex.getMessage()));
		
	}
	
	@Test
	public void invalidNumTest() throws Exception {
		int ret = SudokuFacade.build().executeSudokuValidation(new FileSystemContext(BASE_PATH + "InvalidNumber.txt"));
		assertTrue(ret == 1);

	}

	@Test
	public void invalidSubMatrixTest() throws Exception {
		int ret = SudokuFacade.build().executeSudokuValidation(new FileSystemContext(BASE_PATH + "Invalid3x3Matrix.txt"));
		assertTrue(ret == 1);
	}
	
	@Test
	public void invalidRowTest() throws Exception {
		int ret = SudokuFacade.build().executeSudokuValidation(new FileSystemContext(BASE_PATH + "InvalidRow.txt"));
		assertTrue(ret == 1);

	}
	
	@Test
	public void invalidColumnTest() throws Exception {
		int ret = SudokuFacade.build().executeSudokuValidation(new FileSystemContext(BASE_PATH + "InvalidColumn.txt"));
		assertTrue(ret == 1);
	}


	@Test
	public void checkConfigTest() throws Exception {
		FileSystemContext context = new FileSystemContext(BASE_PATH + "Valid.txt");
		context.setFsConfig(SudokuFactory.build().retrieveFSConfig());
		int result = SudokuFacade.build().executeSudokuValidation(context);
		assertTrue(result == 0);
	}

	
}

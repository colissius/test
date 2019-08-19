package it.daguanno.sudoku.validator;

import static org.junit.Assert.assertTrue;
import static it.daguanno.sudoku.validator.commons.exceptions.SudokuException.EnumSudokuException.NO_ARGS;
import static it.daguanno.sudoku.validator.commons.exceptions.SudokuException.EnumSudokuException.TOO_MANY_INFO;
import static it.daguanno.sudoku.validator.TestConstantUtility.BASE_PATH;

import org.junit.Test;

import it.daguanno.sudoku.validator.commons.exceptions.SudokuException;

public class MainCallTest {

	@Test
	public void noArgsTest() {
		Exception ex = null;
		try {
			Main.main(null);
		} catch (Exception e) {
			ex = e;
		}
		assertTrue(ex != null);
		assertTrue(ex instanceof SudokuException);
		assertTrue(NO_ARGS.getMsg().equals(ex.getMessage()));
	}
	
	@Test
	public void emptyArgsTest() {
		Exception ex = null;
		try {
			Main.main(new String[] {});
		} catch (Exception e) {
			ex = e;
		}
		assertTrue(ex != null);
		assertTrue(ex instanceof SudokuException);
		assertTrue(NO_ARGS.getMsg().equals(ex.getMessage()));
	}

	@Test
	public void emptyValueArgsTest() {
		Exception ex = null;
		try {
			Main.main(new String[] {""});
		} catch (Exception e) {
			ex = e;
		}
		assertTrue(ex != null);
		assertTrue(ex instanceof SudokuException);
		assertTrue(NO_ARGS.getMsg().equals(ex.getMessage()));
	}

	
	@Test
	public void tooManyInfoTest() {
		Exception ex = null;
		try {
			Main.main(new String[]{"a","b"});
		} catch (Exception e) {
			ex = e;
		}
		assertTrue(ex != null);
		assertTrue(ex instanceof SudokuException);
		assertTrue(TOO_MANY_INFO.getMsg().equals(ex.getMessage()));
	}

	@Test
	public void okTest() {
		Exception ex = null;
		try {
			Main.main(new String[]{BASE_PATH + "Valid.txt"});
		} catch (Exception e) {
			ex = e;
		}
		assertTrue(ex == null);
	}
	
	
}

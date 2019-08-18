package it.daguanno.sudoku.validator.commons.exceptions;

public class SudokuException extends Exception {

	private static final long serialVersionUID = 1L;
	//changing an error message does not 
	//require to change other points
	public static enum EnumSudokuException {

		NO_ARGS 	 	("NO ARGS"				),
		TOO_MANY_INFO   ("TOO MANY INFORMATION"	),
		NO_CONFIG		("NO CONFIG"   			),
		NO_FILE			("NO FILE"   			),
		PARSING_ERROR	("PARSING ERROR"   		),
		TOO_FEW_ROWS	("TOO FEW ROWS"   		),
		TOO_FEW_COLUMNS	("TOO FEW COLUMNS"  	),
		TOO_MANY_ROWS	("TOO MANY ROWS"   		),
		TOO_MANY_COLUMNS("TOO MANY COLUMNS" 	),
		INVALID			("INVALID"   			);
		
		private String msg;
		
		private EnumSudokuException(String msg) {
			this.msg = msg;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
		
	}

	public SudokuException(EnumSudokuException enumEx) {
		super(enumEx.getMsg());
	}
	
}

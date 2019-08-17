package it.daguanno.sudoku.validator.application.model.impl;

public class FileSystemConfig {

	private char rowSeparator	;
	private char columnSeparator;
	private char[] escape		;
	private short columnsNum	;
	private short rowNum		;
	
	public short getColumnsNum() {
		return columnsNum;
	}
	public void setColumnsNum(short columnsNum) {
		this.columnsNum = columnsNum;
	}
	public short getRowNum() {
		return rowNum;
	}
	public void setRowNum(short rowNum) {
		this.rowNum = rowNum;
	}
	public char getRowSeparator() {
		return rowSeparator;
	}
	public void setRowSeparator(char rowSeparator) {
		this.rowSeparator = rowSeparator;
	}
	public char getColumnSeparator() {
		return columnSeparator;
	}
	public void setColumnSeparator(char columnSeparator) {
		this.columnSeparator = columnSeparator;
	}
	public char[] getEscape() {
		return escape;
	}
	public void setEscape(char[] escape) {
		this.escape = escape;
	}
	
}

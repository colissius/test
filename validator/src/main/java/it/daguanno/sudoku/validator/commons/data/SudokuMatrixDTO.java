package it.daguanno.sudoku.validator.commons.data;

public class SudokuMatrixDTO {

	private Short matrix[][] = new Short[9][9];

	public Short[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Short[][] matrix) {
		this.matrix = matrix;
	}
	
}

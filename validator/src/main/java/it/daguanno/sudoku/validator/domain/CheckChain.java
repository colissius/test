package it.daguanno.sudoku.validator.domain;

import static it.daguanno.sudoku.validator.commons.costants.IConstants.END;
import static it.daguanno.sudoku.validator.commons.costants.IConstants.START;
import static java.util.logging.Level.INFO;

import java.util.logging.Logger;

import it.daguanno.sudoku.validator.commons.data.SudokuMatrixDTO;

public abstract class CheckChain {

	protected static final Logger logger = Logger.getLogger(CheckChain.class.getName());
	
	protected CheckChain next;
	
	/*
	 * Chain of Responsibility is a behavioral design pattern 
	 * that lets you pass requests along a chain of handlers. 
	 * Upon receiving a request, each handler decides either 
	 * to process the request or to pass it to the next handler
	 * in the chain
	 */
	public int check(SudokuMatrixDTO dto) throws Exception {
		logger.log(INFO, "{0} : {1}",new Object[]{START, getClass().getName() });
		int ret = execute(dto);
		if(getNext() != null) {
			ret += getNext().check(dto);
		}
		logger.log(INFO, "{0} : {1}",new Object[]{END, getClass().getName() });
		return ret;
	}
	
	protected abstract int execute(SudokuMatrixDTO dto) throws Exception;

	public CheckChain getNext() {
		return next;
	}

	public void setNext(CheckChain next) {
		this.next = next;
	}

	public CheckChain next(ColumnCheck next) {
		this.next = next;
		return this;
	}
	
}

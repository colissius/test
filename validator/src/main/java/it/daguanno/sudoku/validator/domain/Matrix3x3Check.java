package it.daguanno.sudoku.validator.domain;

import static it.daguanno.sudoku.validator.commons.costants.IConstants.END;
import static it.daguanno.sudoku.validator.commons.costants.IConstants.START;
import static it.daguanno.sudoku.validator.domain.IEngineConstants.SUB_MATRIX_NUMBER;
import static it.daguanno.sudoku.validator.domain.IEngineConstants.SUB_MATRIX_SIZE;
import static java.util.logging.Level.FINE;

import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import it.daguanno.sudoku.validator.commons.data.SudokuMatrixDTO;

public class Matrix3x3Check extends CheckChain {
	
	protected static final Logger logger = Logger.getLogger(Matrix3x3Check.class.getName());

	public Matrix3x3Check() {
	}

	public Matrix3x3Check(CheckChain next) {
		super.setNext(next);
	}
	
/*
 
THE ALGORITHM 
 
    
  we can see an example matrix. figure 1
 
     0   1   2   3   4   5   6   7   8
   +---+---+---+---+---+---+---+---+---+
 0 | 7 | 3 | 5 | 6 | 1 | 4 | 8 | 9 | 2 |
   +---+---+---+---+---+---+---+---+---+
 1 | 8 | 4 | 2 | 3 | 4 | 5 | 3 | 4 | 5 | 
   +---+---+---+---+---+---+---+---+---+
 2 | 6 | 7 | 8 | 6 | 7 | 8 | 6 | 7 | 8 |
   +---+---+---+---+---+---+---+---+---+
 3 | 0 | 1 | 2 | 0 | 1 | 2 | 0 | 1 | 2 |
   +---+---+---+---+---+---+---+---+---+
 4 | 3 | 4 | 5 | 3 | 4 | 5 | 3 | 4 | 5 |
   +---+---+---+---+---+---+---+---+---+
 5 | 6 | 7 | 8 | 6 | 7 | 8 | 6 | 7 | 8 |
   +---+---+---+---+---+---+---+---+---+
 6 | 0 | 1 | 2 | 0 | 1 | 2 | 0 | 1 | 2 |
   +---+---+---+---+---+---+---+---+---+
 7 | 3 | 4 | 5 | 3 | 4 | 5 | 3 | 4 | 5 |
   +---+---+---+---+---+---+---+---+---+
 8 | 6 | 7 | 8 | 6 | 7 | 8 | 6 | 7 | 8 |
   +---+---+---+---+---+---+---+---+---+
  								figure 1
   

   we have to divide the main matrix into 
   3x3 sub-matrices. figure 2
   
     0   1   2   3   4   5   6   7   8
   +-----------+-----------+-----------+
 0 | 7   3   5 | 6   1   4 | 8   9   2 |
   |           |           |           |
 1 | 8   4   2 | 3   4   5 | 3   4   5 | 
   |           |           |           |
 2 | 6   7   8 | 6   7   8 | 6   7   8 |
   +-----------+-----------+-----------+
 3 | 0   1   2 | 0   1   2 | 0   1   2 |
   |           |           |           |  
 4 | 3   4   5 | 3   4   5 | 3   4   5 |
   |           |           |           |
 5 | 6   7   8 | 6   7   8 | 6   7   8 |
   +-----------+-----------+-----------+
 6 | 0   1   2 | 0   1   2 | 0   1   2 |
   |           |           |           |  
 7 | 3   4   5 | 3   4   5 | 3   4   5 |
   |           |           |           |  
 8 | 6   7   8 | 6   7   8 | 6   7   8 |
   +-----------+-----------+-----------+
   								figure 2
   								
   the sub matrices are 9. figure 3				
   
     0   1   2   3   4   5   6   7   8
   +-----------+-----------+-----------+
 0 |           |           |           |
   |           |           |           |
 1 |     0     |     1     |     2     | 
   |           |           |           |
 2 |           |           |           |
   +-----------+-----------+-----------+
 3 |           |           |           |
   |           |           |           |  
 4 |     3     |     4     |     5     |
   |           |           |           |
 5 |           |           |           |
   +-----------+-----------+-----------+
 6 |           |           |           |
   |           |           |           |  
 7 |     6     |     7     |     8     |
   |           |           |           |  
 8 |           |           |           |
   +-----------+-----------+-----------+
   								figure 3	
   
   if we find a reference point we can 
   cycle on each element of the sub-matrix.
   figure 4
   
     0   1   2   3   4   5   6   7   8
   +-----------+-----------+-----------+
 0 |00         |03         |06         |
   |           |           |           |
 1 |     0     |     1     |     2     | 
   |           |           |           |
 2 |           |           |           |
   +-----------+-----------+-----------+
 3 |30         |33         |36         |
   |           |           |           |  
 4 |     3     |     4     |     5     |
   |           |           |           |
 5 |           |           |           |
   +-----------+-----------+-----------+
 6 |60         |63         |66         |
   |           |           |           |  
 7 |     6     |     7     |     8     |
   |           |           |           |  
 8 |           |           |           |
   +-----------+-----------+-----------+
   								figure 4
   
   	
	how to find the columns?
	we have the following relationship beetwen 
	the sub-matrix number and the column number
   	
   	column num  | sub-matrix num
   	------------+---------------
   		0		|		0
   		3       |		1
   		6       |       2
   		0       |       3
		3       |       4
		6       |       5
   		0       |       6
		3       |       7
		6       |       8
   		        |
   		
   	the formula is simple
   	
   	(sub-matrix num % (mod) 3 ) * 3
   	
   	Let's test it
   
    column num  | sub-matrix num  |  (sub-matrix num % (mod) 3 ) * 3
   	------------+-----------------+-------------------------------------
   		0		|		0         |  (0 % 3) * 3 -> 0 * 3 -> 0
   		3       |		1         |  (1 % 3) * 3 -> 1 * 3 -> 3
   		6       |       2         |  (2 % 3) * 3 -> 2 * 3 -> 6
   		0       |       3         |  (3 % 3) * 3 -> 0 * 3 -> 0
		3       |       4         |  (4 % 3) * 3 -> 1 * 3 -> 3
		6       |       5         |  (5 % 3) * 3 -> 2 * 3 -> 6
   		0       |       6         |  (6 % 3) * 3 -> 0 * 3 -> 0
		3       |       7         |  (7 % 3) * 3 -> 1 * 3 -> 3
		6       |       8         |  (8 % 3) * 3 -> 2 * 3 -> 6
   		        |                 |
   
   how to find the rows?
	we have the following relationship beetwen 
	the sub-matrix number and the rows number
   
   	   row num  | sub-matrix num
   	------------+---------------
   		0		|		0
   		0       |		1
   		0       |       2
   		3       |       3
		3       |       4
		3       |       5
   		6       |       6
		6       |       7
		6       |       8
   		        |
   
   the formula might seem counterintuitive
    	(sub-matrix num / 3 ) * 3
   
   	from a mathematical point of view, multiplying and dividing by
	the same amount is useless, however if we work without 
	decimals everything changes
   
       row num  | sub-matrix num |    (sub-matrix num / 3 ) * 3  
   	------------+----------------+------------------------------- 
   		0		|		0        |  (0 / 3 ) * 3 -> (0) * 3 -> 0
   		0       |		1        |  (1 / 3 ) * 3 -> (0) * 3 -> 0
   		0       |       2        |  (2 / 3 ) * 3 -> (0) * 3 -> 0
   		3       |       3        |  (3 / 3 ) * 3 -> (1) * 3 -> 3
		3       |       4        |  (4 / 3 ) * 3 -> (1) * 3 -> 3
		3       |       5        |  (5 / 3 ) * 3 -> (1) * 3 -> 3
   		6       |       6        |  (6 / 3 ) * 3 -> (2) * 3 -> 6
		6       |       7        |  (7 / 3 ) * 3 -> (2) * 3 -> 6
		6       |       8        |  (8 / 3 ) * 3 -> (2) * 3 -> 6
   		        |                |
   
   
   
 */
	
	
	@Override
	public int execute(SudokuMatrixDTO dto) throws Exception {
		logger.info(START);
		Short matrix[][] = dto.getMatrix();
		//get the sub-matrix num 
		for(int numSubMatrix = 0; numSubMatrix < SUB_MATRIX_NUMBER; numSubMatrix++ ) {
			if(!findPoint( matrix,  numSubMatrix)) {
				return 1;
			}
		}
		logger.info(END);
		return 0;
	}
	
	public boolean findPoint(Short matrix[][], int numSubMatrix) throws Exception {
		
		//	(sub-matrix num % (mod) 3 ) * 3
		int currRow = numSubMatrix/SUB_MATRIX_SIZE*SUB_MATRIX_SIZE;
		// (sub-matrix num / 3 ) * 3
		int currColumn = numSubMatrix%SUB_MATRIX_SIZE*SUB_MATRIX_SIZE;
		// 3x3 matrix check
		return subMatrixCheck(matrix, currRow , currColumn);
		
	}
	
	public boolean subMatrixCheck(Short matrix[][], int startRow , int startColum) throws Exception {
		Set<Short> setCheck = new TreeSet<Short>();
		// 3x3 matrix check
		for(int x = startRow; x < startRow + SUB_MATRIX_SIZE; x++) {
			for(int y = startColum; y < startColum + SUB_MATRIX_SIZE; y++) {
				short currVal = matrix[x][y];
				logger.log(FINE, "matrix[{0}][{1}] value {2}",new Object[]{y,x,currVal});
				if(setCheck.contains(currVal)) {
					return false;
				} else {
					setCheck.add(currVal);
				}
			}
		}
		return true;
	}
	

}

package it.daguanno.sudoku.validator.application;

import static it.daguanno.sudoku.validator.commons.costants.IConstants.END;
import static it.daguanno.sudoku.validator.commons.costants.IConstants.START;

import java.util.logging.Logger;

import it.daguanno.sudoku.validator.application.factory.impl.SudokuFactory;
import it.daguanno.sudoku.validator.application.model.impl.FileSystemContext;
import it.daguanno.sudoku.validator.commons.data.SudokuMatrixDTO;
import it.daguanno.sudoku.validator.domain.SudokuEngine;
import it.daguanno.sudoku.validator.infrastructure.repository.ISudokuRepository;


public class SudokuFacade {
	
	private static final Logger logger = Logger.getLogger(SudokuFacade.class.getName());
	
	public static SudokuFacade build() {
		return new SudokuFacade();
	}
	
	public int executeSudokuValidation(FileSystemContext context) throws Exception {
		logger.info(START);
		//give me the factory
		SudokuFactory factory = SudokuFactory.build();
		//give me the engine
		SudokuEngine engine = factory.retrieveSudokuEngine();
		//give me the raw data
		ISudokuRepository<FileSystemContext> repository = factory.retrieveFSRepository();
		//give me the data parsing rules
		if(context.getFsConfig() == null) {
			context.setFsConfig(factory.retrieveFSConfig());
		}
		//give me the fine data
		SudokuMatrixDTO dto = repository.extract(context);
		//process me the fine data
		int ret = engine.check(dto);
		logger.info(END);
		return ret;
	}
	
}

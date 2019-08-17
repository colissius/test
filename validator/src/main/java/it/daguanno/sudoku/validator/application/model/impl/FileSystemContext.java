package it.daguanno.sudoku.validator.application.model.impl;

import it.daguanno.sudoku.validator.application.model.IContext;

public class FileSystemContext implements IContext {

	private int[] escape					  ;
	private String fileName 			= null;
	private FileSystemConfig fsConfig 	= null;

	public FileSystemContext(String fileName) {
		this.fileName = fileName;
	}
	public FileSystemConfig getFsConfig() {
		return fsConfig;
	}
	public void setFsConfig(FileSystemConfig fsConfig) {
		this.fsConfig = fsConfig;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int[] getEscape() {
		return escape;
	}
	public void setEscape(int[] escape) {
		this.escape = escape;
	}

}

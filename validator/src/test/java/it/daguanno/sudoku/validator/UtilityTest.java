package it.daguanno.sudoku.validator;

import static it.daguanno.sudoku.validator.commons.file.FileSystemUtility.createIfNotExist;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doReturn;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import it.daguanno.sudoku.validator.commons.file.FileSystemUtility;
import it.daguanno.sudoku.validator.commons.log.LogConfig;
import it.daguanno.sudoku.validator.commons.utils.StringUtils;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LogConfig.class)
public class UtilityTest {
	
	@Test
	public void noFolderTest() {
		
		File mockFolder = mock(File.class);
		doReturn(false).when(mockFolder).exists();
		doReturn(true).when(mockFolder).mkdir();
		
		FileSystemUtility fsUtil = new FileSystemUtility();
		assertTrue(fsUtil != null);
		
		createIfNotExist(mockFolder);
		verify(mockFolder).exists();
		verify(mockFolder).mkdir();
		
	}
	
	@Test
	public void folderExistTest() {
			
		File mockFolder = mock(File.class);
		doReturn(true).when(mockFolder).exists();
		
		FileSystemUtility.createIfNotExist(mockFolder);
	
		verify(mockFolder, never()).mkdir();
		
	}
	
	@Test
	public void noArgsTest() {
		boolean res = StringUtils.isEmpty("");
		assertTrue(res);
		res = StringUtils.isEmpty(null);
		assertTrue(res);
		res = StringUtils.isEmpty(" ");
		assertTrue(!res);
	}
	
}

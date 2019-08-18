package it.daguanno.sudoku.validator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import it.daguanno.sudoku.validator.commons.log.LogConfig;

//import static org.junit.Assert.assertTrue;
//
//import java.util.logging.Logger;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;
//import static org.mockito.Matchers.any;
//
//import it.daguanno.sudoku.validator.commons.log.LogConfig;

//@RunWith(PowerMockRunner.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({ LogConfig.class, Logger.class })
public class MainCallTest2 {

	public static class AnswerEx<Void> implements Answer<Void> {

		private Exception ex = null;
		@Override
		public Void answer(InvocationOnMock invocation) throws Throwable {
			ex = invocation.getArgument(2, Exception.class);
			System.out.println("teeeeeeeeeeeeeeest");
			return null;
		}
		public Exception getEx() {
			return ex;
		}
		
	}
	
	@Test
	@PrepareForTest({ LogConfig.class, Logger.class, System.class })
	public void name() throws Exception {
		mockStatic(Logger.class);
		Logger logger = mock(Logger.class);
		when(Logger.getLogger(any(String.class))).thenReturn(logger);
		mockStatic(LogConfig.class);
		PowerMockito.doNothing().when(LogConfig.class, "execute");
		
//		mockStatic(System.class);
//		when(System.exit(anyInt())).thenReturn(logger);
//		
		
//		logger.log(SEVERE, EXCEPTION, e);
//		when(logger.log(isA(Level.class), anyString(), isA(Exception.class))).thenAnswer(new Answer() {
//
//			public Object answer(InvocationOnMock invocation) throws Throwable {
//				Exception ex = invocation.getArgument(2);
//			}
//		});
		
		
//		PowerMockito.doAnswer((Answer) invocation -> {
//		        Object arg0 = invocation.getArgument(0);
//		        Object arg1 = invocation.getArgument(1);
//		        Object arg2 = invocation.getArgument(2);
//		         System.out.println("----------------");
//		        System.out.println(arg2);
//		        
//		        return null;
//		    }).when(logger, "log", isA(Level.class), anyString(), isA(Exception.class));
		
		AnswerEx<Void> answer = new AnswerEx<Void>();
//		
//		PowerMockito.doAnswer(answer).when(logger, "log", isA(Level.class), anyString(), isA(Exception.class));
//		
		
//		PowerMockito.doAnswer(answer).when(logger).log(isA(Level.class), anyString(), isA(Exception.class));
		
//		when(logger, "log", isA(Level.class), anyString(), isA(Exception.class)).then(new Answer<Void>() {
//			@Override
//			public Void answer(InvocationOnMock invocation) throws Throwable {
//				System.out.println("teeeeeeeeeeeeeeest");
//				int i = 0;
//				i = 1/i;
//				return null;
//			}
//		});
		
//		when(logger, "log", isA(Level.class), anyString(), isA(Exception.class)).thenThrow(new Exception("teeeeeeest"));
		
//		when(logger, "log", isA(Level.class), anyString(), isA(Exception.class)).then(answer);
		
		
//		when(logger, "log", isA(Level.class), anyString(), isA(Exception.class)).then( 
//				
//				(Answer) invocation -> {
//			        Object arg0 = invocation.getArgument(0);
//			        Object arg1 = invocation.getArgument(1);
//			        Object arg2 = invocation.getArgument(2);
//			         System.out.println("----------------");
//			        System.out.println(arg2);
//			        
//			        return null;
//			    }
//				);
//		
		ArrayList<String>listMock = Mockito.mock(ArrayList.class);
		Mockito.doAnswer(invocation -> "Always the same").when(listMock).get(Mockito.anyInt());
		 
		String element = listMock.get(1);
		
		System.out.println(element);
		
		
		Mockito.doAnswer(answer).when(logger).log(Mockito.any(Level.class), Mockito.anyString(), Mockito.any(Exception.class));
//		when(logger, "log", isA(Level.class), anyString(), isA(Exception.class)).then( 
//		
//		(Answer) invocation -> {
//	        Object arg0 = invocation.getArgument(0);
//	        Object arg1 = invocation.getArgument(1);
//	        Object arg2 = invocation.getArgument(2);
//	         System.out.println("----------------");
//	        System.out.println(arg2);
//	        
//	        return null;
//	    }
//		);
		
		
//		verify(cutspy).log(eq("log msg"), any(Exception.class));
		System.out.println("test");
		
		Main.main(null);
		System.out.println("test");
		verify(logger).info(anyString());
//		assertTrue(answer.getEx() instanceof SudokuException);
	}

//	@Test
//	@PrepareForTest({ LogConfig.class, Logger.class })
//	public void stubStaticMethodToGetMiniStatement() {
//		Exception ex = null;
//		try {
//			PowerMockito.mockStatic(Logger.class);
//		        Logger logger = PowerMockito.mock(Logger.class);
////		        when(Logger.getLogger(any(Class.class))).thenReturn(logger);
//		        PowerMockito.doReturn(logger).when(Logger.class, "getLogger", Main.class.getName());
////		        new Controller().log();
////
////		        verify(logger).warn(anyString());
////			
//			PowerMockito.mockStatic(LogConfig.class);
//			PowerMockito.doNothing().when(LogConfig.class, "execute");
//			Main.main(null);
//		} catch (Exception e) {
//			ex = e;
//		}
//		assertTrue(ex == null);
//		ex.printStackTrace();
//	}

}

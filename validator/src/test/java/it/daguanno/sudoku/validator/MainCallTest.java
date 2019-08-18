package it.daguanno.sudoku.validator;

import static org.mockito.ArgumentMatchers.anyString;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.logging.Logger;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import it.daguanno.sudoku.validator.commons.log.LogConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ LogConfig.class, Logger.class })
public class MainCallTest {

	private Logger logger;
	 
    @Before public void setUp () {
       
    }
	@Test
	@PrepareForTest({ LogConfig.class, Logger.class, System.class })
	public void name() throws Exception {
        mockStatic(Logger.class);
        Logger logger = mock(Logger.class);
        when(Logger.getLogger(anyString())).thenReturn(logger);

//		 logger = EasyMock.createMock(Logger.class);
//	        Whitebox.setInternalState(Controller.class, logger);
        Controller controller = mock(Controller.class);
//        JSONObject injectedAuditObject = new JSONObject();
//        PowerMockito.spy(JSONObject.class);

        
        controller.log() ;

        verify(logger).info(anyString());
	}
}

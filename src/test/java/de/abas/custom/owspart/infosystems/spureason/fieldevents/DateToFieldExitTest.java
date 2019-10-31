package de.abas.custom.owspart.infosystems.spureason.fieldevents;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.event.ButtonEvent;
import de.abas.erp.axi2.event.FieldEvent;
import de.abas.erp.common.type.AbasDate;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.infosystem.custom.owspart.UsageReasonSparePart;

@RunWith(MockitoJUnitRunner.class)
public class DateToFieldExitTest {
	@Mock
    private UsageReasonSparePart sparepartUsage;
	
	@Mock
    private UsageReasonSparePart.Row sparepartUsageRow;
	
	@Mock
	private DbContext dbContext;
	
	@Mock
    private CodeTemplates codeTemplates;
	
	private DateToFieldExit dateToFieldExit;
	
	@Before
    public void prepare(){
        dateToFieldExit = new DateToFieldExit();
        dateToFieldExit.setCodeTemplates(codeTemplates);
    }
	
	@Test
	public void testEventExceptionIsThrown() throws Exception {
		AbasDate mockAbasDateFrom = Mockito.mock(AbasDate.class);
		AbasDate mockAbasDateTo = Mockito.mock(AbasDate.class);
		Date mockDateFrom = Mockito.mock(Date.class);
		Date mockDateTo= Mockito.mock(Date.class);
		Mockito.when(mockDateTo.before(mockDateFrom)).thenReturn(true);
		Mockito.when(mockAbasDateFrom.toDate()).thenReturn(mockDateFrom);
		Mockito.when(mockAbasDateTo.toDate()).thenReturn(mockDateTo);
		Mockito.when(mockAbasDateTo.toDate().before(mockAbasDateFrom.toDate())).thenReturn(true);
		Mockito.when(sparepartUsage.getYspartdatefrom()).thenReturn(mockAbasDateFrom);
		Mockito.when(sparepartUsage.getYspartdateto()).thenReturn(mockAbasDateTo);
		
		dateToFieldExit.handleEventImpl(Mockito.mock(FieldEvent.class), Mockito.mock(ScreenControl.class), dbContext, sparepartUsage, sparepartUsageRow);
		
		Mockito.verify(codeTemplates).validateDateRangeForInfosytem(mockAbasDateTo, mockAbasDateFrom);
		Mockito.verify(codeTemplates).createEventException(Mockito.anyString());
	}

}

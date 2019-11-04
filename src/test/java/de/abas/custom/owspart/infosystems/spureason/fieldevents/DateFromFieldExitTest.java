package de.abas.custom.owspart.infosystems.spureason.fieldevents;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.event.FieldEvent;
import de.abas.erp.common.type.AbasDate;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.infosystem.custom.owspart.UsageReasonSparePart;

@RunWith(MockitoJUnitRunner.class)
public class DateFromFieldExitTest {
	@Mock
    private UsageReasonSparePart sparepartUsage;
	
	@Mock
    private UsageReasonSparePart.Row sparepartUsageRow;
	
	@Mock
	private DbContext dbContext;
	
	private DateFromFieldExit dateFromFieldExit;
	
	@Before
    public void prepare(){
		dateFromFieldExit = new DateFromFieldExit();
		dateFromFieldExit.setCodeTemplates(new CodeTemplates());
    }
	
	@Test(expected = EventException.class)
	public void testEventExceptionIsThrown() throws Exception {
		AbasDate mockAbasDateFrom = Mockito.mock(AbasDate.class);
		AbasDate mockAbasDateTo = Mockito.mock(AbasDate.class);
		Date dateFrom = oneDayAfter();
		Date dateTo = new Date();
		Mockito.when(mockAbasDateFrom.toDate()).thenReturn(dateFrom);
		Mockito.when(mockAbasDateTo.toDate()).thenReturn(dateTo);
		Mockito.when(sparepartUsage.getYspartdatefrom()).thenReturn(mockAbasDateFrom);
		Mockito.when(sparepartUsage.getYspartdateto()).thenReturn(mockAbasDateTo);
		
		dateFromFieldExit.handleEventImpl(Mockito.mock(FieldEvent.class), Mockito.mock(ScreenControl.class), dbContext, sparepartUsage, sparepartUsageRow);
	}
	
	private Date oneDayAfter() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}
}

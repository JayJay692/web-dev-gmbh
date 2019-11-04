package de.abas.custom.owspart.infosystems.spureason.buttonevents;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import de.abas.custom.owspart.spareParts.SparePartsSelector;
import de.abas.erp.axi.screen.ScreenControl;
import de.abas.erp.axi2.event.ButtonEvent;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.infosystem.custom.owspart.UsageReasonSparePart;
import de.abas.erp.db.infosystem.custom.owspart.UsageReasonSparePart.Table;
import de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile;
import de.abas.erp.db.schema.custom.ersatzteileapp.ErsatzteileEditor;

@RunWith(MockitoJUnitRunner.class)
public class StartButtonAfterTest {	
	@Mock
    private UsageReasonSparePart sparepartUsage;
	
	@Mock
    private UsageReasonSparePart.Row sparepartUsageRow;
	
	@Mock
	private SparePartsSelector sparePartSelector;
	
	@Mock
	private DbContext dbContext;
	
	private StartButtonAfter startButtonAfter;

	@Before
    public void prepare(){
		startButtonAfter = new StartButtonAfter();
    }
	
	@Test
	public void testTableIsFilledWhenSparePartsAreFound() {
		ArrayList<Ersatzteile> spareSpartMockList = new ArrayList<Ersatzteile>();
		Ersatzteile sparePartMock = PowerMockito.mock(Ersatzteile.class);
		ErsatzteileEditor.Table mockTable = mock(ErsatzteileEditor.Table.class);
        ErsatzteileEditor.Row mockRow = mock(ErsatzteileEditor.Row.class);
        when(mockTable.appendRow()).thenReturn(mockRow);
        when(sparePartMock.table()).thenReturn(mockTable);
		spareSpartMockList.add(sparePartMock);
		Table mockTableUsage = Mockito.mock(UsageReasonSparePart.Table.class);
		when(sparepartUsage.table()).thenReturn(mockTableUsage);
		when(mockTableUsage.appendRow()).thenReturn(sparepartUsageRow);
		when(sparePartSelector.selectBy(sparepartUsage, dbContext)).thenReturn(spareSpartMockList);
		
		startButtonAfter.setSparePartsSelector(sparePartSelector);
		startButtonAfter.handleEventImpl(Mockito.mock(ButtonEvent.class), Mockito.mock(ScreenControl.class), dbContext, sparepartUsage, sparepartUsageRow);
		
		Mockito.verify(mockTableUsage, Mockito.times(1)).appendRow();
	}
	
	@Test
	public void testTableIsEmptyWhenNoSparePartsAreFound() {
		ArrayList<Ersatzteile> spareSpartMockList = new ArrayList<Ersatzteile>();
		Table mockTableUsage = Mockito.mock(UsageReasonSparePart.Table.class);
		when(sparepartUsage.table()).thenReturn(mockTableUsage);
		when(mockTableUsage.appendRow()).thenReturn(sparepartUsageRow);
		when(sparePartSelector.selectBy(sparepartUsage, dbContext)).thenReturn(spareSpartMockList);
		
		startButtonAfter.setSparePartsSelector(sparePartSelector);
		startButtonAfter.handleEventImpl(Mockito.mock(ButtonEvent.class), Mockito.mock(ScreenControl.class), dbContext, sparepartUsage, sparepartUsageRow);
		
		Mockito.verify(mockTableUsage, Mockito.times(0)).appendRow();
	}
}

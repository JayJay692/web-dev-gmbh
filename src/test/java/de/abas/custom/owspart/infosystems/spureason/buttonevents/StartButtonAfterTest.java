package de.abas.custom.owspart.infosystems.spureason.buttonevents;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import de.abas.erp.db.infosystem.custom.owspart.UsageReasonSparePart;

public class StartButtonAfterTest {	
	@Mock
    private UsageReasonSparePart sparepartUsage;
	
	@Mock
	private StartButtonAfter startButtonAfter;

	@Before
    public void prepare(){
		startButtonAfter = new StartButtonAfter();
    }
	
	@Test
	public void testTableIsFilledWhenSparePartsAreFound() {
		
	}
}

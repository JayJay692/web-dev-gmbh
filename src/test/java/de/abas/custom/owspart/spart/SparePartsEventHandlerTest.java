package de.abas.custom.owspart.spart;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import de.abas.custom.owspart.utils.PropertiesLoader;
import de.abas.erp.db.DbContext;

public class SparePartsEventHandlerTest {
	private static PropertiesLoader propertiesLoader;
    private static DbContext ctx;
	
	 @BeforeClass
	 public static void setup(){
		 propertiesLoader = new PropertiesLoader();
		 ctx = propertiesLoader.getCtx();
	 }

	 @AfterClass
	 public static void close(){
		 ctx.close();
	 }
	 
	 @Test()
	 public void testScreenEnterNewNotAllowed(){
		 
	 }
}

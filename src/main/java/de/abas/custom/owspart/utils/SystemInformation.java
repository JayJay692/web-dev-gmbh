package de.abas.custom.owspart.utils;

import de.abas.erp.common.type.AbasDate;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.company.CompanyData;
import de.abas.erp.db.schema.warehouse.WarehouseGroup;
import de.abas.jfop.base.buffer.BufferFactory;
import de.abas.jfop.base.buffer.GlobalTextBuffer;
import de.abas.erp.api.util.UniqueObjectFactory;

public class SystemInformation {
	
	GlobalTextBuffer globalTextBuffer = getGlobalTextBuffer();
	
	public WarehouseGroup getIntlGroup(DbContext ctx) {
		CompanyData companyData = getCompanyData(ctx);
		WarehouseGroup intWarehGrp = companyData.getIntWarehGrp();
		return intWarehGrp;
	}
	
	public String getOperatorCode() {
		return globalTextBuffer.getStringValue("operatorCode");
	}
	
	public String getMitarbId() {
		return globalTextBuffer.getStringValue("currUserPwd^employeePwdRef^id");
	}
	
	public String getPasswordDefinition() {
		return globalTextBuffer.getStringValue("currUserPwd");
	}

	public boolean getEdpMode() {
		return globalTextBuffer.getBooleanValue("runByEDP");
	}

	public String getClientTempdir() {
		return globalTextBuffer.getStringValue("clientTempDir");
	}
	
	public AbasDate getTodaysDate() {
		return globalTextBuffer.getAbasDateValue("date");
	}
	
	public String getEvtentVariable() {
		return globalTextBuffer.getStringValue("evtVar");
	}
	
	public String getAbasVersion() {
		return globalTextBuffer.getStringValue("programVersion");
	}
	
	public int getAbasVersionYear() {
		String year =  getAbasVersion().substring(13, 17);
		return Integer.parseInt(year);
	}
	
	public String getClientName() {
		return globalTextBuffer.getStringValue("client");
	}
	
	public String getOneTimePwd() {
		return globalTextBuffer.getStringValue("oneTimePwd");
	}
	
	public GlobalTextBuffer getGlobalTextBuffer(){
		BufferFactory bufferFactory = BufferFactory.newInstance(true);
		return bufferFactory.getGlobalTextBuffer();		
	}
	
	private CompanyData getCompanyData (DbContext ctx){
		UniqueObjectFactory factory = new  UniqueObjectFactory(ctx);
		return factory.getCompanyData();
	}
}

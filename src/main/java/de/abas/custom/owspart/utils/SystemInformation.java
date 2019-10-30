package de.abas.custom.owspart.utils;

import de.abas.erp.common.type.AbasDate;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.company.CompanyData;
import de.abas.erp.db.schema.warehouse.WarehouseGroup;
import de.abas.jfop.base.buffer.Buffer;
import de.abas.jfop.base.buffer.BufferFactory;
import de.abas.jfop.base.buffer.GlobalTextBuffer;
import de.abas.erp.api.util.UniqueObjectFactory;

public class SystemInformation {
	
	private GlobalTextBuffer globalTextBuffer;

	public SystemInformation(){
		globalTextBuffer = BufferFactory.newInstance(true).getGlobalTextBuffer();
	}
	
	public WarehouseGroup getIntlGroup(DbContext databaseContext) {
		CompanyData companyData = new UniqueObjectFactory(databaseContext).getCompanyData();
		return companyData.getIntWarehGrp();
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

	public boolean isEdpMode() {
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
		String year = getAbasVersion().substring(13, 17);
		return Integer.parseInt(year);
	}
	
	public String getClientName() {
		return globalTextBuffer.getStringValue("client");
	}
	
	public String getOneTimePwd() {
		return globalTextBuffer.getStringValue("oneTimePwd");
	}
	
}

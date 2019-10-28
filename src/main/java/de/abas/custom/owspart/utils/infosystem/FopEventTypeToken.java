package de.abas.custom.owspart.utils.infosystem;

public enum FopEventTypeToken {
	SCREEN_ENTER("SE"), 
	SCREEN_VALIDATION("SV"),
	SCREEN_EXIT("SX"), 
	WORKFLOW("WF"), 
	SCREEN_CANCEL("SC"), 
	SCREEN_END("SEE"), 
	FIELD_FILLED("FF"), 
	FIELD_VALIDATION("FV"), 
	FIELD_EXIT("FX"), 
	BUTTON_BEFORE("BB"), 
	BUTTON_AFTER("BA"), 
	ROW_INSERT_BEFORE("RIB"), 
	ROW_INSERT_AFTER("RIA"), 
	ROW_DELETE_BEFORE("RDB"), 
	ROW_DELETE_AFTER("RDA"), 
	ROW_MOVE_BEFORE("RMB"), 
	ROW_MOVE_AFTER("RMA"), 
	ROW_HIGHLIGHT("RH"), 
	ROW_CHANGE("RC"),
	IS_REPORT_HEADER("RPH"),
	IS_REPORT_FOOTER("RPF"),
	IS_GROUP_HEADER("GRH"),
	IS_GROUP_FOOTER("GRF"),
	IS_TABLE("TAB");
	
	private final String token;

	private FopEventTypeToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}

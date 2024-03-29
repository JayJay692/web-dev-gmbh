package de.abas.custom.owspart.utils.esdk;

import java.util.List;

import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.erp.common.type.enums.EnumDatabase;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.company.Vartab;

public class DatabaseMetaData {

	public String getDatabaseCommand(DbContext ctx, EnumDatabase dbNoDb) {
		String vartabSearchWord = "V-" + getDatabaseNumberAsString(dbNoDb) + "-00";
		return getVartab(ctx, vartabSearchWord).getDBCmd();
	}

	private Vartab getVartab(DbContext ctx, String vartabSearchWord) {
		// TODO: abfangen wenn null
		List<Vartab> selectVartab = new CodeTemplates().getSelectList(Vartab.class, "swd", vartabSearchWord, ctx);
        return selectVartab.get(0);
	}

	public String getDatabaseNumberAsString(EnumDatabase dbNoDb) {
		return dbNoDb.toString().replace("(", "").replace(")", "");
	}

}

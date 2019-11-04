package de.abas.custom.owspart.product;

import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile;
import de.abas.erp.db.schema.custom.ersatzteileapp.ErsatzteileEditor;
import de.abas.erp.db.schema.part.ProductEditor;
import de.abas.erp.db.schema.vendor.Vendor;

public class SparePartFactory {

    private CodeTemplates codeTemplates = new CodeTemplates();

    Ersatzteile createNewSparePart(ProductEditor head, DbContext ctx) throws EventException {
        try {
            ErsatzteileEditor sparePartEditor = ctx.newObject(ErsatzteileEditor.class);
            sparePartEditor.setSwd("SP" + head.getSwd());
            sparePartEditor.setDescr("Ersatzteil zu Artikel" + head.getIdno());
            sparePartEditor.setYspartpart(head);
            sparePartEditor.setYspartvendor((Vendor) head.getVendor());
            sparePartEditor.setYspartvendorordern(head.getPOrderNo());
            sparePartEditor.commit();
            head.setYspartspartcreated(true);
            return sparePartEditor.objectId();
        } catch (Exception e) {
            throw codeTemplates.createEventException("Ersatzteil konnte nicht angelegt werden");
        }
    }
}
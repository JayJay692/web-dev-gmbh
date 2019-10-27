package de.abas.custom.owspart.product;

import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.custom.owspart.utils.SystemInformation;
import de.abas.eks.jfop.remote.FOe;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi2.event.ButtonEvent;
import de.abas.erp.common.type.enums.EnumEditorAction;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile;
import de.abas.erp.db.schema.custom.ersatzteileapp.ErsatzteileEditor;
import de.abas.erp.db.schema.part.ProductEditor;
import de.abas.erp.db.schema.vendor.Vendor;

public class ButtonEventsProduct {
	
	DbContext ctx;
	ButtonEvent event;
	CodeTemplates codeTemplate = new CodeTemplates();

	public ButtonEventsProduct (DbContext ctx, ButtonEvent event) {
		this.ctx = ctx;
		this.event = event;
	}
	
	public void createSpartButtonAfter(ProductEditor head) throws EventException{
		checkEventPreconditions();		
		Ersatzteile sparePart = createNewSparePart(head);
		openSparePartInEditMode(sparePart.getId().toString());
	}	
	
	private void openSparePartInEditMode(String sparePartId) {
			if (!new SystemInformation().getEdpMode()) {
				FOe.command(" -parallel \"" + sparePartId + "<(Edit)>\"");			
			}
	}

	private Ersatzteile createNewSparePart(ProductEditor head) throws EventException {
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
			throw codeTemplate.createEventException("Ersatzteil konnte nicht angelegt werden");
		}
	}

	private void checkEventPreconditions() throws EventException {
		if (codeTemplate.isEventNewOrCopy(event) || EnumEditorAction.View.equals(event.getCommand())) {
			throw codeTemplate.createEventException("nur im Bearbeiten-Modus erlaubt");
		}
	}

}

package de.abas.custom.owspart.product;

import de.abas.custom.owspart.utils.CodeTemplates;
import de.abas.custom.owspart.utils.SystemInformation;
import de.abas.eks.jfop.remote.FOe;
import de.abas.erp.axi.event.EventException;
import de.abas.erp.axi2.event.ButtonEvent;
import de.abas.erp.common.type.enums.EnumEditorAction;
import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile;
import de.abas.erp.db.schema.part.ProductEditor;

public class CreateNewSparePartButtonEvent {

	private SparePartFactory sparePartFactory = new SparePartFactory();
	DbContext ctx;
	ButtonEvent event;
	CodeTemplates codeTemplate = new CodeTemplates();

	public CreateNewSparePartButtonEvent(DbContext ctx, ButtonEvent event) {
		this.ctx = ctx;
		this.event = event;
	}
	
	public void createSpartButtonAfter(ProductEditor head) throws EventException{
		checkEventPreconditions();		
		Ersatzteile sparePart = createNewSparePart(head);
		openSparePartInEditMode(sparePart.getId().toString());
	}	
	
	private void openSparePartInEditMode(String sparePartId) {
			if (!new SystemInformation().isEdpMode()) {
				FOe.command(" -parallel \"" + sparePartId + "<(Edit)>\"");			
			}
	}

	private Ersatzteile createNewSparePart(ProductEditor head) throws EventException {
		return sparePartFactory.createNewSparePart(head, ctx);
	}

	private void checkEventPreconditions() throws EventException {
		if (codeTemplate.isEventNewOrCopy(event) || EnumEditorAction.View.equals(event.getCommand())) {
			throw codeTemplate.createEventException("nur im Bearbeiten-Modus erlaubt");
		}
	}

}

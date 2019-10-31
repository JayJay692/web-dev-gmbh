package de.abas.custom.owspart.infosystem.spureason;

import de.abas.erp.db.DbContext;
import de.abas.erp.db.EditorAction;
import de.abas.erp.db.exception.CommandException;
import de.abas.erp.db.schema.custom.ersatzteileapp.Ersatzteile;
import de.abas.erp.db.schema.part.Product;
import de.abas.erp.db.schema.part.ProductEditor;
import de.abas.erp.db.selection.Conditions;
import de.abas.erp.db.selection.SelectionBuilder;

public class SparePartFactory {

    public static Ersatzteile createTestSparepart(DbContext context) throws CommandException {
        Product product = ArticleFactory.createArticle(context);
        ProductEditor productEditor = product.createEditor();
        productEditor.open(EditorAction.UPDATE);
        productEditor.invokeYspartcreatespart();
        productEditor.commit();

        return findCorrespondingSparepart(context, product);
    }

    private static Ersatzteile findCorrespondingSparepart(DbContext context, Product product) {
        SelectionBuilder<Ersatzteile> selectionBuilder = SelectionBuilder.create(Ersatzteile.class);
        selectionBuilder.add(Conditions.eq(Ersatzteile.META.yspartpart.toString(), product.getIdno()));
        return context.createQuery(selectionBuilder.build()).execute().get(0);
    }
}

package de.abas.custom.owspart.infosystem.spureason;

import de.abas.erp.db.DbContext;
import de.abas.erp.db.schema.part.Product;
import de.abas.erp.db.schema.part.ProductEditor;

public class ArticleFactory {

    public static Product createArticle(DbContext context){
        ProductEditor productEditor = context.newObject(ProductEditor.class);
        productEditor.setSwd("test");
        productEditor.commit();
        return productEditor.objectId();
    }
}

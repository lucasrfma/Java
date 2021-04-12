package Labs.ProductManagement.app;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

import Labs.ProductManagement.data.*;


/**
 * {@code Product} management application
 */
public class Shop
{
    public static void main(String[] args) {

        ProductManager pm = new ProductManager();
        ProductManager pmBR = new ProductManager(new Locale("pt","BR"));
        
        Product p1 = pm.createProduct(101,"Tea",BigDecimal.valueOf(1.99));
        Product p1BR = pmBR.createProduct(101,"Chá", BigDecimal.valueOf(2.99));
        
        pm.printProductReport();
        pmBR.printProductReport();

        p1 = pm.reviewProduct(p1, Rating.FIVE_STAR, "Best tea i've ever had");
        p1BR = pmBR.reviewProduct(p1BR, Rating.FIVE_STAR, "TOp");

        pm.printProductReport();
        pmBR.printProductReport();
    }
}
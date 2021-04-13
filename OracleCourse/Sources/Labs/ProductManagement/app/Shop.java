package Labs.ProductManagement.app;

import java.math.BigDecimal;
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
        
        pm.createProduct(101,"Tea",BigDecimal.valueOf(1.99));
        pmBR.createProduct(101,"Chá", BigDecimal.valueOf(2.99));
        
        pm.printProductReport();
        pmBR.printProductReport();

        pm.reviewProduct(5, "Best tea i've ever had");
        pmBR.reviewProduct(5, "TOp");

        pm.printProductReport();
        pmBR.printProductReport();

        pm.reviewProduct(4, "Good, but not the best...");
        pmBR.reviewProduct(4, "Muito bom, mas não perfeito");

        pm.printProductReport();
        pmBR.printProductReport();

        pm.reviewProduct(3, "Not my cup of tea...");
        pmBR.reviewProduct(3, "Até é bom, mas pelo preço não vale");

        pm.printProductReport();
        pmBR.printProductReport();

        pm.reviewProduct(3, "Meh...");
        pmBR.reviewProduct(3, "Prefiro o que e faço");

        pm.setLocale(new Locale("pt","BR"));

        pm.printProductReport();
        pmBR.printProductReport();
    }
}
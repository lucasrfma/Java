package Labs.ProductManagement.app;

import java.math.BigDecimal;
// import java.util.Locale;
import java.time.LocalDate;

import Labs.ProductManagement.data.*;


/**
 * {@code Product} management application
 */
public class Shop
{
    public static void main(String[] args) {

        ProductManager pm = new ProductManager();
        // Locale brazil = new Locale("pt","BR");
        // ProductManager pmBR = new ProductManager(brazil);
        
        Product p1 = pm.createProduct(101,"Tea",BigDecimal.valueOf(1.99));
        pm.printProductReport();

        pm.reviewProduct(p1,5, "Best tea i've ever had");
        pm.printProductReport();

        pm.reviewProduct(p1,4, "Good, but not the best...");
        pm.printProductReport();

        pm.reviewProduct(p1,3, "Not my cup of tea...");
        pm.printProductReport();
        
        pm.reviewProduct(p1,3, "Meh...");
        Product p2 = pm.createProduct(102, "Cake", BigDecimal.valueOf(5.99),LocalDate.now().plusDays(2));
        pm.printProductReport();
        

        pm.reviewProduct(p2,5, "Perfect cake");
        pm.reviewProduct(p2,4, "Very nice cake");
        pm.reviewProduct(p2,3, "Its ok...");
        pm.reviewProduct(p2,3, "Good, but too expensive");
        pm.printProductReport();

    }
}
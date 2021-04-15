package Labs.ProductManagement.app;

import java.math.BigDecimal;
// import java.util.Locale;
import java.time.LocalDate;
import java.util.Locale;

import Labs.ProductManagement.data.*;
import Labs.ProductManagement.data.ProductManager.LocaleSet;


/**
 * {@code Product} management application
 */
public class Shop
{
    public static void main(String[] args) {

        /////
        ProductManager pm = new ProductManager(LocaleSet.PT_BR);
        /////

        pm.createProduct(101,"Tea",BigDecimal.valueOf(1.99));
        pm.printProductReport(101);

        pm.reviewProduct(101,Rating.FIVE_STAR, "Best tea i've ever had");
        pm.printProductReport(101);

        /////
        pm.changeLocale(Locale.US);
        /////

        pm.reviewProduct(101,4, "Good, but not the best...");
        pm.printProductReport(101);

        pm.reviewProduct(101,3, "Not my cup of tea...");
        pm.printProductReport(101);
        
        pm.reviewProduct(101,Rating.THREE_STAR, "Meh...");
        pm.createProduct(102, "Cake", BigDecimal.valueOf(5.99),LocalDate.now().plusDays(2));

        /////
        pm.changeLocale("pt-BR");
        /////

        pm.printProductReport(101);

        pm.reviewProduct(102,5, "Perfect cake");
        pm.reviewProduct(102,4, "Very nice cake");
        pm.reviewProduct(102,3, "Its ok...");
        pm.reviewProduct(102,3, "Good, but too expensive");
        pm.printProductReport(102);
        
        pm.printAllProductsReport();

        // System.out.println(pm.findProductByID(102));
    }
}
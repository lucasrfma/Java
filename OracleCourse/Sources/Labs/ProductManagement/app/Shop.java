package Labs.ProductManagement.app;

import java.math.BigDecimal;
// import java.util.Locale;
import java.time.LocalDate;
import java.util.Comparator;
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
        // pm.printProductReport(101);

        pm.reviewProduct(101,Rating.FIVE_STAR, "Best tea i've ever had");
        // pm.printProductReport(101);

        /////
        pm.changeLocale(Locale.US);
        /////

        pm.reviewProduct(101,4, "Good, but not the best...");
        // pm.printProductReport(101);

        pm.reviewProduct(101,3, "Not my cup of tea...");
        // pm.printProductReport(101);
        
        pm.reviewProduct(101,Rating.THREE_STAR, "Meh...");
        pm.createProduct(102, "Cake", BigDecimal.valueOf(5.99),LocalDate.now().plusDays(1));

        /////
        pm.changeLocale(LocaleSet.PT_BR);
        /////

        // pm.printProductReport(101);

        pm.reviewProduct(102,5, "Perfect cake");
        pm.reviewProduct(102,4, "Very nice cake");
        pm.reviewProduct(102,5, "GREAT");
        pm.reviewProduct(102,5, "Perfect");
        // pm.printProductReport(102);
        
        
        pm.createProduct(103, "Frapuccino", BigDecimal.valueOf(12.99));
        
        pm.reviewProduct(103, 3, "Good, but WAY to expensive");
        pm.reviewProduct(103, 3, "Who can even pay for this??");
        pm.reviewProduct(103, 4, "I quite like it");

        pm.createProduct(104, "Juice", BigDecimal.valueOf(5.99));
        pm.reviewProduct(104, 3, "Seems industrialized, not fresh");
        pm.createProduct(105, "Water", BigDecimal.valueOf(1.99));
        pm.reviewProduct(105, 1, "Water should be free >:(");

        
        // Comparator<Product> DescendingPriceSorter = (p1,p2) -> p2.getPrice().compareTo(p1.getPrice());
        // Comparator<Product> DescendingRatingSorter = (p1,p2) -> p2.getRating().ordinal() - p1.getRating().ordinal();
        // Comparator<Product> AscendingPriceSorter = (p1,p2) -> p1.getPrice().compareTo(p2.getPrice());
        // Comparator<Product> AscendingRatingSorter = (p1,p2) -> p1.getRating().ordinal() - p2.getRating().ordinal();
        // unsorted
        // pm.printAllProductsReport();
        // // Sort by price: most expensive first
        // pm.printAllProductsReport( DescendingPriceSorter );
        // // Sort by rating: best rating first
        // pm.printAllProductsReport( DescendingRatingSorter );
        
        // Ascending Price then Ascending Rating
        // pm.printAllProductsReport(AscendingPriceSorter.thenComparing(AscendingRatingSorter));   
        // Ascending Rating then Ascending Price
        // pm.printFilteredProductsReport( p -> p.getId() < 104,AscendingRatingSorter.thenComparing(AscendingPriceSorter));

        pm.getDiscounts().forEach( (rating, discount) -> System.out.println(rating + ": " + discount));

    }
}
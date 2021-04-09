package Labs.ProductManagement.app;

import java.math.BigDecimal;
import Labs.ProductManagement.data.*;

/**
 * {@code Product} management application
 */
public class Shop
{
    public static void main(String[] args) {
        
        Product p1 = new Product("Tea",BigDecimal.valueOf(1.99));
        Product p2 = new Product("Coffee", BigDecimal.valueOf(2.99),Rating.FOUR_STAR);
        Product p3 = new Product("Cake", BigDecimal.valueOf(5.99),Rating.FIVE_STAR);
        Product p4 = new Product();
        Product p5 = p3.applyRating(Rating.THREE_STAR);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p5);
    }
}
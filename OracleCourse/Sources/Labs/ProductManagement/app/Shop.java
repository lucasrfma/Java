package Labs.ProductManagement.app;

import java.math.BigDecimal;
import java.time.LocalDate;

import Labs.ProductManagement.data.*;
import static Labs.ProductManagement.data.ProductManager.*;


/**
 * {@code Product} management application
 */
public class Shop
{
    public static void main(String[] args) {
        
        Product p1 = createProduct(101,"Tea",BigDecimal.valueOf(1.99));
        Product p11 = p1.applyRating(Rating.FIVE_STAR);
        Product p2 = createProduct(102,"Coffee", BigDecimal.valueOf(2.99),Rating.FOUR_STAR);
        Product p3 = createProduct(103,"Cake", BigDecimal.valueOf(5.99),Rating.FIVE_STAR,LocalDate.now().plusDays(2));
        Product p33 = createProduct(103,"Cake", BigDecimal.valueOf(5.99),Rating.FIVE_STAR,LocalDate.now().plusDays(1));
        Product p333 = createProduct(103,"Cake", BigDecimal.valueOf(5.99),Rating.FIVE_STAR,LocalDate.now().plusDays(0));
        Product p4 = p333.applyRating(Rating.THREE_STAR);

        System.out.println(p1);
        System.out.println(p11);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p33);
        System.out.println(p333);
        System.out.println(p4);
    }
}
package Labs.ProductManagement.app;

import java.math.BigDecimal;
import Labs.ProductManagement.data.*;

/**
 * {@code Product} management application
 */
public class Shop
{
    public static void main(String[] args) {
        
        Product p1 = new Product();

        p1.setId(101);
        p1.setName("Tea");
        p1.setPrice(BigDecimal.valueOf(1.99));

        System.out.println(p1);
    }
}
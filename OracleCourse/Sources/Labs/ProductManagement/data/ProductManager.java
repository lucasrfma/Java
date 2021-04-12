package Labs.ProductManagement.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class ProductManager {

    private Locale locale;
    private ResourceBundle resources;
    private DateTimeFormatter dateFormat;
    private NumberFormat moneyFormat;
    
    private Product product;
    private Review review;

    public ProductManager(Locale locale)
    {
        this.locale = locale;
        resources = ResourceBundle.getBundle("labs.ProductManagement.data.resources", locale);
        dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
        moneyFormat = NumberFormat.getCurrencyInstance(locale);
    }
    public ProductManager()
    {
        this.locale = new Locale("en","US");
        resources = ResourceBundle.getBundle("labs.ProductManagement.data.resources", locale);
        dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
        moneyFormat = NumberFormat.getCurrencyInstance(locale);
    }
    
    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore)
    {
        product = new Food(id, name, price, rating, bestBefore);
        return product;
    }
    public Product createProduct(int id, String name, BigDecimal price, LocalDate bestBefore)
    {
        product = new Food(id, name, price, bestBefore);
        return product;
    }
    public Product createProduct(int id, String name, BigDecimal price, Rating rating)
    {
        product = new Drink(id, name, price, rating);
        return product;
    }
    public Product createProduct(int id, String name, BigDecimal price)
    {
        product = new Drink(id, name, price);
        return product;
    }

    public Product reviewProduct(Product product, Rating rating, String comment)
    {
        review = new Review(rating, comment);
        this.product = product.applyRating(rating);
        return this.product;
    }
    public void printProductReport()
    {
        StringBuilder text = new StringBuilder();
        text.append(MessageFormat.format(resources.getString("product"),
                                         product.getName(),
                                         moneyFormat.format(product.getPrice()),
                                         product.getRating(),
                                         dateFormat.format(product.getBestBefore()))+"\n");
        // text.append("\n");
        if( review != null )
        {
            text.append(MessageFormat.format(resources.getString("review"),
                                            review.getRating(),
                                            review.getComment()));
        } else{
            text.append(resources.getString("no.reviews")+"\n");
        }
        System.out.println(text);
    }

}

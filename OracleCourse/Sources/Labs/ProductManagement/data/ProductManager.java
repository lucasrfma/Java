package Labs.ProductManagement.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductManager {

    // private Locale locale;
    private ResourceBundle resources;
    private DateTimeFormatter dateFormat;
    private NumberFormat moneyFormat;
    
    Map<Product, List<Review>> products = new HashMap<>();

    /**
     * Constructor for ProductManager
     * Takes locale as a parameter and uses it to properly format the output messages
     * @param locale
     */
    public ProductManager(Locale locale)
    {
        this.setLocale(locale);
    }
    /**
     * Default constructor for ProductManager
     * Calls the Locale constructor using locale("en","US")
     */
    public ProductManager()
    {
        this.setLocale(new Locale("en","US"));
    }
    /**
     * Resets locale
     */
    public void setLocale(Locale locale)
    {
        resources = ResourceBundle.getBundle("labs.ProductManagement.data.resources", locale);
        dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
        moneyFormat = NumberFormat.getCurrencyInstance(locale);
    }

    public Product createProduct(int id, String name, BigDecimal price, LocalDate bestBefore)
    {
        Product product = new Food(id, name, price, bestBefore);
        List<Review> newProductsReviews = new ArrayList<Review>();
        products.putIfAbsent(product, newProductsReviews);
        return product;
    }
    public Product createProduct(int id, String name, BigDecimal price)
    {
        Product product = new Drink(id, name, price);
        List<Review> newProductsReviews = new ArrayList<Review>();
        products.putIfAbsent(product, newProductsReviews);
        return product;
    }

    /**
     * Review a product on the Map
     * @param product - product to b reviewed
     * @param rating - rating to be applied
     * @param comment - comment made together with the rating
     * @return product with the rating applied
     */
    public Product reviewProduct(Product product, Rating rating, String comment)
    {
        Review newReview = new Review(rating, comment);
        products.get(product).add(newReview);
        List<Review> newReviewList = products.get(product);
        double sum = 0;
        for (Review review : newReviewList) {
            sum += review.getRating().ordinal();
        }
        product = product.applyRating((int)Math.round(sum/newReviewList.size()));

        products.remove(product);
        products.put(product, newReviewList);
        return product;
    }
    public Product reviewProduct(Product product, int rating, String comment)
    {
        return this.reviewProduct(product,Rating.convert(rating), comment);
    }
    /**
     * Prints a product report:
     *  - Product information: Name, and Overall Rating
     *  - List of reviews: rating and comments
     */
    public void printProductReport()
    {
        System.out.println("\n--------------------------PRODUCT REPORT--------------------------\n");
        for (Product product : products.keySet()) {
            StringBuilder text = new StringBuilder();
            text.append(MessageFormat.format(resources.getString("product"),
            product.getName(),
            moneyFormat.format(product.getPrice()),
            product.getRating(),
            dateFormat.format(product.getBestBefore()))+"\n");
            
            if( products.get(product).isEmpty() ){
                text.append(resources.getString("no.reviews")+"\n");
            }
            else{
                for (Review review : products.get(product)) {
                    text.append(MessageFormat.format(resources.getString("review"),
                    dateFormat.format(review.getTimeStamp()),
                    review.getRating(),
                    review.getComment())+"\n");
                }
            }
            System.out.println(text);
        }
        System.out.println("------------------------------------------------------------------\n");
    }

}

package Labs.ProductManagement.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Arrays;

public class ProductManager {

    // private Locale locale;
    private ResourceBundle resources;
    private DateTimeFormatter dateFormat;
    private NumberFormat moneyFormat;
    
    private Product product;
    private Review[] reviews;
    private double ratingAverage;

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


    /**
     * Since this product manager has to keep track of reviews and make an average
     * out of them, doesn't seem appropriate to start off with a rating.
     * Maybe if these methods also included in the parameters at least an
     * "reviewNumber" integer as well, to give a weight to this starting rating it
     * would make sense...
     */
    // public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore)
    // {
    //     product = new Food(id, name, price, rating, bestBefore);
    //     return product;
    // }
    // public Product createProduct(int id, String name, BigDecimal price, int rating, LocalDate bestBefore)
    // {
    //     product = new Food(id, name, price, rating, bestBefore);
    //     return product;
    // }

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
    public Product createProduct(int id, String name, BigDecimal price, int rating)
    {
        product = new Drink(id, name, price, rating);
        return product;
    }
    public Product createProduct(int id, String name, BigDecimal price)
    {
        product = new Drink(id, name, price);
        return product;
    }

    /**
     * Review some other product...
     *  Not sure how this would be useful, but it is what was created in the practice
     *  lesson of the course
     *  It would make sense if the rating+comment system was saved in the product itself I guess...
     * @param product - product to b reviewed
     * @param rating - rating to be applied
     * @param comment - comment made together with the rating
     * @return product with the rating applied
     */
    public Product reviewProduct(Product product, Rating rating, String comment)
    {
        Review newReview = new Review(rating, comment);
        if(reviews != null)
        {
            reviews = Arrays.copyOf(reviews, reviews.length+1);
            reviews[reviews.length-1] = newReview;
        } else
        {
            reviews = new Review[]{newReview};
        }
        ratingAverage += (rating.ordinal()-ratingAverage)/reviews.length;
        this.product = product.applyRating((int)Math.round(ratingAverage));
        return this.product;
    }
    public Product reviewProduct(Product product, int rating, String comment)
    {
        return this.reviewProduct(this.product,Rating.convert(rating), comment);
    }
    /**
     * Same method as above, but using this object's own Product.
     * Seems to make more sense since the reviews are stored here, no on the Product object.
     * But it also makes this class not a simple factoryClass, made to create products and return them
     * but to actually keep track of a single product object and its reviews.
     * @param rating - rating to be applied
     * @param comment - comment made together with the rating
     * @return product with the rating applied
     */
    public Product reviewProduct(Rating rating, String comment)
    {
        return this.reviewProduct(this.product, rating, comment);
    }
    public Product reviewProduct(int rating, String comment)
    {
        return this.reviewProduct(this.product, Rating.convert(rating), comment);
    }
    /**
     * Prints a product report:
     *  - Product information: Name, and Overall Rating
     *  - List of reviews: rating and comments
     */
    public void printProductReport()
    {
        StringBuilder text = new StringBuilder();
        text.append(MessageFormat.format(resources.getString("product"),
                                         product.getName(),
                                         moneyFormat.format(product.getPrice()),
                                         product.getRating(),
                                         dateFormat.format(product.getBestBefore()))+"\n");
        // text.append("\n");
        if( reviews != null )
        {
            for(int i = 0; i < reviews.length; ++i ){
                text.append(MessageFormat.format(resources.getString("review"),
                                                dateFormat.format(reviews[i].getTimeStamp()),
                                                reviews[i].getRating(),
                                                reviews[i].getComment())+"\n");
            }
        } else{
            text.append(resources.getString("no.reviews")+"\n");
        }
        System.out.println(text);
    }

}

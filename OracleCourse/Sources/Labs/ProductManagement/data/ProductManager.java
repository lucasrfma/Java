package Labs.ProductManagement.data;

import java.math.BigDecimal;
import java.time.LocalDate;
// to concatenate and format strings
import java.text.MessageFormat;
// to customize number format, such as currency etc
import java.text.NumberFormat;
// to customize date time format, such as mm/dd/yyyy or something like that
import java.time.format.DateTimeFormatter;
// enumeration to easily choose between standard date time formats
import java.time.format.FormatStyle;
// to be used in conjunction with the above formatting classes
import java.util.Locale;
// to be able to get information out of a .properties file
// used in tandem with locale to choose specific .properties files
// according to chosen locale
import java.util.ResourceBundle;

//collections used
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ProductManager {
    
    Map<Product, List<Review>> products = new HashMap<>();
    
    ResourceFormatter resourceFormatter;

    /**
     * Constructor for ProductManager
     * Takes locale as a parameter and uses it to properly format the output messages
     * @param locale
     */
    public ProductManager(Locale locale)
    {
        resourceFormatter = new ResourceFormatter(locale.toLanguageTag());
    }
    /**
     * Default constructor for ProductManager
     * Calls the Locale constructor using locale("en","US")
     */
    public ProductManager()
    {
        this(LocaleSet.EN_US);
    }
    public ProductManager(String locale)
    {
        resourceFormatter = new ResourceFormatter(locale);
    }
    public ProductManager(LocaleSet locale)
    {
        resourceFormatter = new ResourceFormatter(locale);
    }

    public void changeLocale(Locale locale)
    {
        resourceFormatter.setLocale(locale.toLanguageTag());
    }
    public void changeLocale(String locale)
    {
        resourceFormatter.setLocale(locale);
    }
    public void changeLocale(LocaleSet locale)
    {
        resourceFormatter.setLocale(locale);
    }

    public Product createProduct(int id, String name, BigDecimal price, LocalDate bestBefore)
    {
        Product product = new Food(id, name, price, bestBefore);
        products.putIfAbsent(product, new ArrayList<Review>());
        return product;
    }
    public Product createProduct(int id, String name, BigDecimal price)
    {
        Product product = new Drink(id, name, price);
        products.putIfAbsent(product, new ArrayList<Review>());
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
        products.get(product).add(new Review(rating, comment));
        List<Review> newReviewList = products.get(product);
        double sum = 0;
        for (Review review : newReviewList) {
            sum += review.getRating().ordinal();
        }
        product = product.applyRating((int)Math.round(sum/newReviewList.size()));

        Collections.sort(newReviewList,Collections.reverseOrder());

        products.remove(product);
        products.put(product, newReviewList);
        return product;
    }
    public Product reviewProduct(Product product, int rating, String comment)
    {
        return this.reviewProduct(product,Rating.convert(rating), comment);
    }
    public Product reviewProduct(int id, Rating rating, String comment)
    {
        return this.reviewProduct(findProductByID(id),rating, comment);
    }
    public Product reviewProduct(int id, int rating, String comment)
    {
        return this.reviewProduct(findProductByID(id),Rating.convert(rating), comment);
    }
    /**
     * Prints a product report:
     *  - Product information: Name, and Overall Rating
     *  - List of reviews: rating and comments
     */
    public void printProductReport(Product product)
    {
        StringBuilder text = new StringBuilder();
        text.append(resourceFormatter.formatProduct(product));
        
        if( products.get(product).isEmpty() ){
            text.append(resourceFormatter.getResourceText("no.reviews")+"\n");
        }
        else{
            for (Review review : products.get(product)) {
                text.append(resourceFormatter.formatReview(review));
            }
        }
        System.out.println(text);
    }
    public void printProductReport(int id)
    {
        printProductReport(findProductByID(id));
    }

    public void printAllProductsReport()
    {
        System.out.println(resourceFormatter.getResourceText("all.products"));
        for (Product product : products.keySet()) {
            printProductReport(product);
        }
        System.out.println("-------------------------------------------------------------------\n");
    }
    public void printAllProductsReport(Comparator<Product> sorter)
    {
        List<Product> productList = new ArrayList<>(products.keySet());
        productList.sort(sorter);
        System.out.println(resourceFormatter.getResourceText("all.products"));
        for (Product product : productList) {
            printProductReport(product);
        }
        System.out.println("-------------------------------------------------------------------\n");
    }

    /**
     * Searches for a specific Product by ID
     * @param id - ID of the product to be found
     * @return the product, if there is one with the received ID in this ProductManager
     * @return null, if there isn't one.
     */
    public Product findProductByID(int id)
    {
        for (Product product : products.keySet()) {
            if ( product.getId() == id ){
                return product;
            }
        }
        return null;
    }

    private static class ResourceFormatter{

        // private Locale locale;
        private ResourceBundle resources;
        private DateTimeFormatter dateFormat;
        private NumberFormat moneyFormat;

        private ResourceFormatter(Locale locale)
        {
            this.setLocale(locale.toLanguageTag());
        }
        private ResourceFormatter(LocaleSet locale){
            this.setLocale(locale);
        }
        private ResourceFormatter(String locale)
        {
            this.setLocale(locale);
        }
        /**
         * Resets locale
         */
        private void setLocale(Locale locale)
        {
            resources = ResourceBundle.getBundle("labs.ProductManagement.data.resources", locale);
            dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
            moneyFormat = NumberFormat.getCurrencyInstance(locale);
        }
        private void setLocale(LocaleSet locale)
        {
            String[] newLocale = locale.toString().split("-");
            this.setLocale(new Locale(newLocale[0],newLocale[1]));
        }
        private void setLocale(String locale)
        {
            locale = locale.replace('_', '-');
            boolean validLocale = false;
            for (var element : LocaleSet.values()) {
                if(element.toString().equals(locale)){
                    validLocale = true;
                    this.setLocale(element);
                }
            }
            if(!validLocale){
                this.setLocale(LocaleSet.EN_US);
            }
        }

        private String formatProduct(Product product)
        {
            return MessageFormat.format(resources.getString("product"),
                                product.getName(),
                                moneyFormat.format(product.getPrice()),
                                product.getRating(),
                                dateFormat.format(product.getBestBefore()))+"\n";
        }

        private String formatReview(Review review)
        {
            return MessageFormat.format(resources.getString("review"),
                                        dateFormat.format(review.getTimeStamp()),
                                        review.getRating(),
                                        review.getComment())+"\n";
        }

        private String getResourceText(String key)
        {
            return resources.getString(key);
        }

    }

    public enum LocaleSet{

        EN_US   ("en-US"),
        EN_GB   ("en-GB"),
        JP_JP   ("jp-JP"),
        PT_BR   ("pt-BR");

        private String locale;
    
        private LocaleSet(String locale)
        {
            this.locale = locale;
        }
    
        @Override
        public String toString()
        {
            return locale;
        }
        /**
         * Converts integer to LocaleSet
         * @param intLocale Integer value representing a LocaleSet
         * @return The equivalent LocaleSet to the integer received.
         * Returns LocaleSet.EN_US if intRating is not a valid number.
         */
        public static LocaleSet convert(int intLocale)
        {
            return (intLocale >= 0 && intLocale <= 5) ? LocaleSet.values()[intLocale] : EN_US; 
        }
    }
}

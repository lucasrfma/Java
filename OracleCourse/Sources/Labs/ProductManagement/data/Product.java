package Labs.ProductManagement.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * {@code Product} class represents properties and behaviours of product objects
 * in the Product Management System.<br>
 * Each product has an id, name and price<br>
 * Each product can have a discount, calculated based on {@link DEBIT_DISCOUNT} and {@link CASH_DISCOUNT}
 * discount rates
 */
public class Product {

    /**
     * A constant that defines a {@link java.math.BigDecimal} that represents a rate of discount to be applied
     * in case of debit card payments.
     */
    public static final BigDecimal DEBIT_DISCOUNT = new BigDecimal("0.1");
    /**
     * A constant that defines a {@link java.math.BigDecimal} that represents a rate of discount to be applied
     * in case of cash payments.
     */
    final public static BigDecimal CASH_DISCOUNT = new BigDecimal("0.15");
    private static int ID = 100;
    private int id;
    private String name;
    private BigDecimal price;
    private Rating rating;

    public Product(String name, BigDecimal price, Rating rating)
    {
        id = ++ID;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }
    public Product(String name, BigDecimal price)
    {
        this(name, price, Rating.NOT_RATED);
    }
    public Product()
    {
        this("placeholder",BigDecimal.ZERO);
    }
    private Product(int id, String name, BigDecimal price, Rating rating)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    // Getters
    public int getId(){return id;}
    public String getName(){return name;}
    public BigDecimal getPrice(){return price;}
    public Rating getRating(){return rating;}

    // // setters
    // public void setId(final int newId){id = newId;}
    // public void setName(final String newName){name = newName;}
    // public void setPrice(final BigDecimal newPrice){price = newPrice;}
    // public void setRating(final Rating newRating){rating = newRating;}

    public Product applyRating(Rating rating)
    {
        return new Product(id,name,price,rating);
    }

    /**
     *  Discount calculator for debit card payment option
     **/ 
    public BigDecimal getDebitDiscount()
    {
        return DEBIT_DISCOUNT.multiply(price).setScale(2, RoundingMode.HALF_EVEN);
    }
    /**
     *  Discount calculator for cash payment option
     */ 
    public BigDecimal getCashDiscount()
    {
        return CASH_DISCOUNT.multiply(price).setScale(2,RoundingMode.HALF_EVEN);
    }

    @Override
    public String toString()
    {
        return "Product: " + name + " | ID: " + id + " | Price: " + price + 
        " | CashDC: " + this.getCashDiscount() + " | DebitDC " + this.getDebitDiscount() + " | Rating " + rating;
    }

}

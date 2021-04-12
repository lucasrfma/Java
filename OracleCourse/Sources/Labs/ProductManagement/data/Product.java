package Labs.ProductManagement.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 * {@code Product} class represents properties and behaviours of product objects
 * in the Product Management System.<br>
 * Each product has an id, name and price<br>
 * Each product can have a discount, calculated based on {@link DEBIT_DISCOUNT} and {@link CASH_DISCOUNT}
 * discount rates
 */
public abstract class Product implements Rateable<Product>{

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
    private int id;
    private String name;
    private BigDecimal price;
    private Rating rating;

    public Product(int id, String name, BigDecimal price, Rating rating)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }
    public Product(int id, String name, BigDecimal price)
    {
        this(id, name, price, Rating.NOT_RATED);
    }

    // Getters
    public int getId(){return id;}
    public String getName(){return name;}
    public BigDecimal getPrice(){return price;}
    public LocalDate getBestBefore(){return LocalDate.now();}
    @Override
    public Rating getRating(){return rating;}

    /**
     * A string that represents the discount availables regardless of payment options
     */
    public String specialDiscount()
    {
        return "No Specials";
    }
    /**
     * Discount to be applied regardless of payment option.
     * Method created to be overwritten by subclasses
     */
    public BigDecimal getDiscount()
    {
        return BigDecimal.ZERO;
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
        " | " + specialDiscount() +
        " | CashDC " + getCashDiscount().setScale(2, RoundingMode.HALF_EVEN) +
        " | DebitDC " + getDebitDiscount().setScale(2, RoundingMode.HALF_EVEN) +
        " | Rating " + rating  + " | Best Before " + getBestBefore();
    }

    @Override
    public boolean equals(Object p)
    {
        if( !(p instanceof Product) )
        {
            return false;
        }
        return this.id == ((Product)p).id && this.name.equals(((Product)p).name);
    }
    /**
     * 
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(id,name);
    }
}

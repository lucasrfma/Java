package Labs.ProductManagement.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;

public class Drink extends Product {
    
    private static LocalTime happyHourStart = LocalTime.of(17, 29, 59);
    private static LocalTime happyHourEnd = LocalTime.of(18, 31);

    Drink(int id,String name, BigDecimal price, Rating rating)
    {
        super(id,name, price, rating);
    }
    Drink(int id,String name, BigDecimal price, int rating)
    {
        super(id,name, price, rating);
    }
    Drink(int id,String name, BigDecimal price)
    {
        super(id,name, price);
    }

    @Override
    public Product applyRating(Rating rating)
    {
        return new Drink(getId(),getName(),getPrice(),rating);
    }
    /**
     * Discount methods
     * @return
     */
    @Override
    public String specialDiscount()
    {
        BigDecimal disc;
        if( (disc = getDiscount()).equals(BigDecimal.ZERO) )
        {
            return super.specialDiscount();
        }
        return "Happy Hour " + disc.setScale(2, RoundingMode.HALF_EVEN);
    }
    @Override
    public BigDecimal getDiscount()
    {
        LocalTime now = LocalTime.now();
        return now.isAfter(happyHourStart) && now.isBefore(happyHourEnd) ? 
                    this.getPrice().multiply(BigDecimal.valueOf(0.2)) : 
                    BigDecimal.ZERO;
    }
    @Override
    public BigDecimal getCashDiscount()
    {
        return super.getCashDiscount().add(getDiscount());
    }
    @Override
    public BigDecimal getDebitDiscount()
    {
        return super.getDebitDiscount().add(getDiscount());
    }
}

package Labs.ProductManagement.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Food extends Product {

    private LocalDate bestBefore;

    Food(int id,String name, BigDecimal price, Rating rating, LocalDate bestBefore)
    {
        super(id,name, price, rating);
        this.bestBefore = bestBefore;
    }
    Food(int id,String name, BigDecimal price, LocalDate bestBefore)
    {
        super(id, name, price);
        this.bestBefore = bestBefore;
    }
    /**
     * Get the best before date for the food product.
     */
    @Override
    public LocalDate getBestBefore(){return bestBefore;}

    @Override
    public Product applyRating(Rating rating)
    {
        return new Food(getId(),getName(),getPrice(),rating,getBestBefore());
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
        return "Near best before: " + disc.setScale(2, RoundingMode.HALF_EVEN);
        
    }
    @Override
    public BigDecimal getDiscount()
    {
        return bestBefore.equals(LocalDate.now()) ? 
                    this.getPrice().multiply(BigDecimal.valueOf(0.2)) : 
                    bestBefore.equals(LocalDate.now().plusDays(1)) ? 
                    this.getPrice().multiply(BigDecimal.valueOf(0.1)) : 
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

    /**
     * Overrides of Object class methods
     */
    // @Override
    // public String toString()
    // {
    //     return super.toString() + " | Best Before " + bestBefore;
    // }
    @Override
    public boolean equals(Object p)
    {
        if( !(p instanceof Food) )
        {
            return false;
        }
        return super.equals(p) && bestBefore.equals(((Food)p).bestBefore);
    }
    @Override
    public int hashCode()
    {
        return super.hashCode() + bestBefore.hashCode();
    }
}

package Labs.ProductManagement.data;

@FunctionalInterface
public interface Rateable<T> {
    
    public static final Rating DEFAULT_RATING = Rating.NOT_RATED;

    T applyRating(Rating rating);

    public default T applyRating(int rating)
    {
        return applyRating(Rating.convert(rating));
    }

    public default Rating getRating() {
        return DEFAULT_RATING;
    }

    // Decided to implement this directly on Rating.java
    // Seems to make more sense to me to have it there.
    // public static Rating convert(int stars)
}

package Labs.ProductManagement.data;

public enum Rating{
    // white star \u2606
    // black star \u2605
    NOT_RATED   ("\u2606\u2606\u2606\u2606\u2606"),
    ONE_STAR    ("\u2605\u2606\u2606\u2606\u2606"),
    TWO_STAR    ("\u2605\u2605\u2606\u2606\u2606"),
    THREE_STAR  ("\u2605\u2605\u2605\u2606\u2606"),
    FOUR_STAR   ("\u2605\u2605\u2605\u2605\u2606"),
    FIVE_STAR   ("\u2605\u2605\u2605\u2605\u2605");
    private String stars;

    private Rating(String stars)
    {
        this.stars = stars;
    }

    @Override
    public String toString()
    {
        return stars;
    }
    /**
     * Converts integer to Rating
     * @param intRating Integer value representing a Rating
     * @return The equivalent Rating to the integer received.
     * Returns Rating.NOT_RATED if intRating is not a valid number.
     */
    public static Rating convert(int intRating)
    {
        return (intRating >= 0 && intRating <= 5) ? Rating.values()[intRating] : NOT_RATED; 
    }
}
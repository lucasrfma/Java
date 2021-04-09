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

    // public String getStars()
    // {
    //     return stars;
    // }

    @Override
    public String toString()
    {
        return stars;
    }
}
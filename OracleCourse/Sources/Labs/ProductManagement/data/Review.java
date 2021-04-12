package Labs.ProductManagement.data;

public class Review implements Rateable<Review>
{
    private Rating rating;
    private String comment;
    
    public Review(Rating rating, String comment)
    {
        this.rating = rating;
        this.comment = comment;
    }

    // getters
    @Override
    public Rating getRating(){ return rating;}
    public String getComment(){return comment;}

    @Override
    public Review applyRating(Rating rating) {
        return (new Review(rating,this.comment));
    }
}

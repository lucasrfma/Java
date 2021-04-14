package Labs.ProductManagement.data;

import java.time.LocalDateTime;

public class Review implements Rateable<Review>, Comparable<Review>
{
    private Rating rating;
    private String comment;
    private LocalDateTime timeStamp;
    
    public Review(Rating rating, String comment)
    {
        this.comment = comment;
        this.rating = rating;
        timeStamp = LocalDateTime.now();
    }
    public Review(int rating, String comment)
    {
        this(Rating.convert(rating),comment);
    }

    // getters
    @Override
    public Rating getRating(){ return rating;}
    public String getComment(){return comment;}
    public LocalDateTime getTimeStamp(){return timeStamp;}

    @Override
    public Review applyRating(Rating rating) {
        return (new Review(rating,this.comment));
    }

    @Override
    public int compareTo(Review o) {
        return this.rating.ordinal() - o.rating.ordinal();
    }
}

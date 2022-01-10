package model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewsData {

    private int idReview;
    private int rating;
    private String title;
    private String description;
    private Date date;

    @Override
    public String toString() {
        return "ID: " + idReview + "\n" +
                "Rating: " + rating + "\n" +
                "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Date: " + date;
    }
}

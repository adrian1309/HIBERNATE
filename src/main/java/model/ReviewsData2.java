package model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewsData2 {

    private int idReview;
    private int rating;
    private String title;
    private String description;
    private Date date;
    private String item;
    private String customer;
    private int purchase;

    @Override
    public String toString() {
        return "Item: " + item + "    " +
                "Customer: " + customer + "    " +
                "Date: " + date;
    }
}

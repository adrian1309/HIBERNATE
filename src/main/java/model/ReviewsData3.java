package model;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewsData3 {

    private int idReview;
    private int rating;
    private String title;
    private String description;
    private Date date;
    private int idItem;
    private int idCustomer;
    private int idPurchase;

    @Override
    public String toString() {
        return "IdItem: " + idItem +
                "   IdCustomer: " + idCustomer +
                "   IdPurchase: " + idPurchase;
    }
}

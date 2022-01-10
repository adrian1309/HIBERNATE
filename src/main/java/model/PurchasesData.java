package model;

import lombok.*;

import java.sql.Date;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchasesData {

    private int idPurchase;
    private Date date;
    private Collection<Reviews> reviewsByIdPurchase;

    @Override
    public String toString() {
        return "ID: " + idPurchase + "\n" +
                "Date: " + date + "\n" +
                "Reviews: " + reviewsByIdPurchase;
    }
}

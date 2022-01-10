package model;

import lombok.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchasesData3 {
    private int idPurchase;
    private int idItem;
    private int idCustomer;
    private Date date;

    @Override
    public String toString() {
        return "IdPurchase: " + idPurchase + "    " +
                "IdItem: " + idItem + "    " +
                "IdCustomer: " + idCustomer +
                "Date: " + date;
    }
}

package model;

import lombok.*;

import java.sql.Date;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchasesData2 {
    private int idItem;
    private int idCustomer;
    private Date date;

    @Override
    public String toString() {
        return "IdItem: " + idItem + "    " +
                "IdCustomer: " + idCustomer + "    " +
                "Date: " + date;
    }
}

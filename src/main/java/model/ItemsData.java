package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemsData {

    private Double price;
    private Long numPurchasesLastMonth;
    private Double avgRating;

    @Override
    public String toString() {
        return "ItemsData{" +
                "price=" + price +
                ", numPurchsesLastMonth=" + numPurchasesLastMonth +
                ", avgRating=" + avgRating +
                '}';
    }
}

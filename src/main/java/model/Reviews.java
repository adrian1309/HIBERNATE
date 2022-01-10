package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Reviews", schema = "adriancristobal-Webstore", catalog = "")
public class Reviews {
    private int idReview;
    private int rating;
    private String title;
    private String description;
    private Date date;
    private Purchases purchasesByIdPurchase;
    private Customers customersByIdCustomer;
    private Items itemsByIdItem;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idReview", nullable = false)
    public int getIdReview() {
        return idReview;
    }

    public void setIdReview(int idReview) {
        this.idReview = idReview;
    }

    @Basic
    @Column(name = "rating", nullable = false)
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 200)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reviews reviews = (Reviews) o;

        if (idReview != reviews.idReview) return false;
        if (rating != reviews.rating) return false;
        if (title != null ? !title.equals(reviews.title) : reviews.title != null) return false;
        if (description != null ? !description.equals(reviews.description) : reviews.description != null) return false;
        return date != null ? date.equals(reviews.date) : reviews.date == null;
    }

    @Override
    public int hashCode() {
        int result = idReview;
        result = 31 * result + rating;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idPurchase", referencedColumnName = "idPurchase", nullable = false)
    public Purchases getPurchasesByIdPurchase() {
        return purchasesByIdPurchase;
    }

    public void setPurchasesByIdPurchase(Purchases purchasesByIdPurchase) {
        this.purchasesByIdPurchase = purchasesByIdPurchase;
    }

    @ManyToOne
    @JoinColumn(name = "idCustomer", referencedColumnName = "idCustomer", nullable = false)
    public Customers getCustomersByIdCustomer() {
        return customersByIdCustomer;
    }

    public void setCustomersByIdCustomer(Customers customersByIdCustomer) {
        this.customersByIdCustomer = customersByIdCustomer;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idItem", referencedColumnName = "idItem", nullable = false)
    public Items getItemsByIdItem() {
        return itemsByIdItem;
    }

    public void setItemsByIdItem(Items itemsByIdItem) {
        this.itemsByIdItem = itemsByIdItem;
    }

    @Override
    public String toString() {
        return "IdReview: " + idReview +
                " Rating: " + rating +
                " Title: " + title +
                " Description: " + description +
                " Date: " + date + "\n";
    }
}

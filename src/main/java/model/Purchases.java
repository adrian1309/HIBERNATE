package model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "Purchases", schema = "adriancristobal-Webstore", catalog = "")
public class Purchases {
    private int idPurchase;
    private Date date;
    private Customers customersByIdCustomer;
    private Items itemsByIdItem;
    private Collection<Reviews> reviewsByIdPurchase;


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idPurchase", nullable = false)
    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
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

        Purchases purchases = (Purchases) o;

        if (idPurchase != purchases.idPurchase) return false;
        return date != null ? date.equals(purchases.date) : purchases.date == null;
    }

    @Override
    public int hashCode() {
        int result = idPurchase;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
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

    @OneToMany(mappedBy = "purchasesByIdPurchase", cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<Reviews> getReviewsByIdPurchase() {
        return reviewsByIdPurchase;
    }

    public void setReviewsByIdPurchase(Collection<Reviews> reviewsByIdPurchase) {
        this.reviewsByIdPurchase = reviewsByIdPurchase;
    }

    @Override
    public String toString() {
        return "IdPurchase: " + idPurchase +
                "  Date: " + date;
    }
}

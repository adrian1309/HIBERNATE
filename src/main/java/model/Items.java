package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collection;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Items", schema = "adriancristobal-Webstore", catalog = "")
public class Items {
    private int idItem;
    private String name;
    private String company;
    private double price;
    private Collection<Purchases> purchasesByIdItem;
    private Collection<Reviews> reviewsByIdItem;

    public void constructor (int idItem, String name, String company, double price){
        this.idItem = idItem;
        this.name = name;
        this.company = company;
        this.price = price;
    }



    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idItem", nullable = false)
    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "company", nullable = false, length = 45)
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Items items = (Items) o;

        if (idItem != items.idItem) return false;
        if (Double.compare(items.price, price) != 0) return false;
        if (name != null ? !name.equals(items.name) : items.name != null) return false;
        return company != null ? company.equals(items.company) : items.company == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idItem;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /*No deja poner dos fetch = FetchType.EAGER,
    asique la otra opcion es @LazyCollection(LazyCollectionOption.FALSE)*/

    @OneToMany(mappedBy = "itemsByIdItem", fetch = FetchType.EAGER)
    public Collection<Purchases> getPurchasesByIdItem() {
        return purchasesByIdItem;
    }

    public void setPurchasesByIdItem(Collection<Purchases> purchasesByIdItem) {
        this.purchasesByIdItem = purchasesByIdItem;
    }

    @OneToMany(mappedBy = "itemsByIdItem")
    @LazyCollection(LazyCollectionOption.FALSE)
    public Collection<Reviews> getReviewsByIdItem() {
        return reviewsByIdItem;
    }

    public void setReviewsByIdItem(Collection<Reviews> reviewsByIdItem) {
        this.reviewsByIdItem = reviewsByIdItem;
    }

    @Override
    public String toString() {
        return "N: " + name + " C: " + company;

    }
}



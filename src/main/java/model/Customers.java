package model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Collection;

@NamedQueries({
        @NamedQuery(
                name = "SELECT_ALL_CUSTOMERS",
                query = "from Customers"
        )
        ,
        @NamedQuery(
                name = "SELECT_ALL_USERS",
                query = "from Users"
        )
        ,
        @NamedQuery(
                name = "LOGIN",
                query = "select u from Users u" +
                        " where userName = :userName and password = :password"
        )
        ,
        @NamedQuery(
                name = "CHECK_USER_STATUS",
                query = "select count(idCustomer) from Customers c where" +
                        " c.idCustomer in (select u.idUser from Users u where u.userName = :userName and u.password = :password) group by c.idCustomer"
        )
})

@Entity
@Table(name = "Customers", schema = "adriancristobal-Webstore", catalog = "")
public class Customers {
    private int idCustomer;
    private String name;
    private int telephone;
    private String address;
    private Users usersByIdCustomer;
    private Collection<Purchases> purchasesByIdCustomer;
    private Collection<Reviews> reviewsByIdCustomer;

    @Id
    @Column(name = "idCustomer", nullable = false)
    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "telephone", nullable = false)
    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customers customers = (Customers) o;

        if (idCustomer != customers.idCustomer) return false;
        if (telephone != customers.telephone) return false;
        if (name != null ? !name.equals(customers.name) : customers.name != null) return false;
        return address != null ? address.equals(customers.address) : customers.address == null;
    }

    @Override
    public int hashCode() {
        int result = idCustomer;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + telephone;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "idCustomer", referencedColumnName = "idUser", nullable = false)
    public Users getUsersByIdCustomer() {
        return usersByIdCustomer;
    }

    public void setUsersByIdCustomer(Users usersByIdCustomer) {
        this.usersByIdCustomer = usersByIdCustomer;
    }

    @OneToMany(mappedBy = "customersByIdCustomer")
    public Collection<Purchases> getPurchasesByIdCustomer() {
        return purchasesByIdCustomer;
    }

    public void setPurchasesByIdCustomer(Collection<Purchases> purchasesByIdCustomer) {
        this.purchasesByIdCustomer = purchasesByIdCustomer;
    }

    @OneToMany(mappedBy = "customersByIdCustomer")
    public Collection<Reviews> getReviewsByIdCustomer() {
        return reviewsByIdCustomer;
    }

    public void setReviewsByIdCustomer(Collection<Reviews> reviewsByIdCustomer) {
        this.reviewsByIdCustomer = reviewsByIdCustomer;
    }

    @Override
    public String toString() {
        return "ID: " + idCustomer +
                " Name: " + name;
    }

    public String toStringIdAndName(){
        return "ID: " + idCustomer + " Name: " + name;
    }

}

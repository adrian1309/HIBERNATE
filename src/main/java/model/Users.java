package model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;

@Entity
@Table(name = "Users", schema = "adriancristobal-Webstore", catalog = "")
public class Users {
    private int idUser;
    private String userName;
    private String password;
    private Customers customersByIdUser;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idUser", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "userName", nullable = false, length = 200)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 200)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (idUser != users.idUser) return false;
        if (userName != null ? !userName.equals(users.userName) : users.userName != null) return false;
        return password != null ? password.equals(users.password) : users.password == null;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @OneToOne(mappedBy = "usersByIdCustomer")
    @NotFound(action = NotFoundAction.IGNORE)
    public Customers getCustomersByIdUser() {
        return customersByIdUser;
    }

    public void setCustomersByIdUser(Customers customersByIdUser) {
        this.customersByIdUser = customersByIdUser;
    }

    @Override
    public String toString() {
        return "Users{" +
                "idUser=" + idUser +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

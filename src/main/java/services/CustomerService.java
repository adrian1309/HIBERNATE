package services;

import model.Customers;
import model.Users;

import java.util.List;

public interface CustomerService {
    void addCustomer(Customers customer, Users user);

    List<Customers> getAllCustomers();

    void deleteCustomer(Customers customer);

    Customers getCustomer(Customers customer);

    Users login(Users user);

    int checkUserAdminOrCustomer(Users user);
}

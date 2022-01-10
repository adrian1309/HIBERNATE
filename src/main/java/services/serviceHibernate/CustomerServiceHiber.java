package services.serviceHibernate;

import dao.DAOCustomersHibernate;
import model.Customers;
import model.Users;
import services.CustomerService;

import java.util.List;

public class CustomerServiceHiber implements CustomerService {
    @Override
    public void addCustomer(Customers customer, Users user) {
        DAOCustomersHibernate dao = new DAOCustomersHibernate();
        dao.add(customer, user);
    }

    @Override
    public List<Customers> getAllCustomers() {
        DAOCustomersHibernate dao = new DAOCustomersHibernate();
        return dao.getAllCustomers();
    }

    @Override
    public void deleteCustomer(Customers customer) {
        DAOCustomersHibernate dao = new DAOCustomersHibernate();
        dao.delete(customer);
    }

    @Override
    public Customers getCustomer(Customers customer) {
        return null;
    }

    @Override
    public Users login(Users user) {
        DAOCustomersHibernate dao = new DAOCustomersHibernate();
        return dao.login(user);
    }

    @Override
    public int checkUserAdminOrCustomer(Users user) {
        DAOCustomersHibernate dao = new DAOCustomersHibernate();
        return dao.checkUserStatus(user);
    }
}

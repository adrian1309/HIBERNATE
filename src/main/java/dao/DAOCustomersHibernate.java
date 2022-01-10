package dao;

import model.Customers;
import model.Items;
import model.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DAOCustomersHibernate {
    Session session;

    public List<Customers> getAllCustomers() {
        List<Customers> list = null;
        session = HibernateUtils.getSession();
        Query query = session.createNamedQuery("SELECT_ALL_CUSTOMERS");
        list = query.list();
        session.close();
        return list;
    }

    public List<Users> getAllUsers() {
        List<Users> list = null;
        session = HibernateUtils.getSession();
        Query query = session.createQuery(" from Users ");
        list = query.list();
        session.close();
        return list;
    }

    public Customers getCustomerByUserId(Users user){
        int id = user.getIdUser();
        session = HibernateUtils.getSession();
        Customers customer = session.get(Customers.class, id);
        session.close();
        return customer;
    }

    public void add(Customers customer, Users user) {
        Transaction transaction = null;
        //insert into Users (userName, password) values (:userName, :password)
        //insert into Customers (idCustomer, name, telephone, address) values (:idCustomer,:name,:telephone,:address)
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();

            List<Users> listUser = getAllUsers();
            //para coger el que hemos añadido pero este ya tiene el id autoincrementado
            //a diferencia del que introducimos al principio
            Users userTaken = listUser.get(listUser.size()-1);

            customer.setIdCustomer(userTaken.getIdUser());
            System.out.println(customer);

            transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();


        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }

    }

    public void delete(Customers customer){
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.delete(customer);
            transaction.commit();

        } catch (ConstraintViolationException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Logger.getLogger(DAOCustomersHibernate.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            session.close();
        }
    }

    public Users login(Users user) {
        session = HibernateUtils.getSession();
        Query query = session.createQuery("select u from Users u " +
                "where userName = :userName and password = :password");
        query.setParameter("userName", user.getUserName());
        query.setParameter("password", user.getPassword());
        Users userGet = (Users) query.uniqueResult();
        session.close();
        return userGet;
    }

    public int checkUserStatus(Users user){
        int result;
        session = HibernateUtils.getSession();
        Query query = session.createQuery("select count(idCustomer) from Customers c where " +
                        "c.idCustomer in (select u.idUser from Users u where " +
                        "u.userName = :userName and u.password = :password) group by c.idCustomer")
                .setParameter("userName", user.getUserName())
                .setParameter("password", user.getPassword());
        Long value = (Long) query.uniqueResult();
        session.close();
        if (value == null) {
            //es User
            result = 0;
        }else {
            //NO es User
            result = 1;
        }
        return result;
    }
}

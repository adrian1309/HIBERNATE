package dao;

import model.Customers;
import model.Reviews;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOReviewsHibernate {
    Session session;

    public List<Reviews> getAll(){
        session = HibernateUtils.getSession();
        List<Reviews> listReviews =  session.createQuery("from Reviews").list();
        session.close();
        return listReviews;
    }

    public void add(Reviews review){
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.save(review);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void delete(Reviews review) {
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.delete(review);
            transaction.commit();

        } catch (ConstraintViolationException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Logger.getLogger(DAOReviewsHibernate.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            session.close();
        }
    }

    public List<Reviews> getReviewByCustomerId(Customers customer){
        session = HibernateUtils.getSession();
        //Reviews review =  session.get(Reviews.class, customer.getIdCustomer());
        Query query = session.createQuery("select r from Reviews r where " +
                "customersByIdCustomer.idCustomer = :idCustomer");
        query.setParameter("idCustomer", customer.getIdCustomer());
        List<Reviews> listReviews = query.getResultList();
        session.close();
        return listReviews;
    }

    public List<Reviews> getReviewsOrderByItem(){
        session = HibernateUtils.getSession();
        List<Reviews> listReviews = session.createQuery("select r from Reviews r order by itemsByIdItem.idItem").list();
        session.close();
        return listReviews;
    }

    public List<Reviews> getReviewsOrderByCustomer(){
        session = HibernateUtils.getSession();
        List<Reviews> listReviews = session.createQuery("select r from Reviews r order by customersByIdCustomer.idCustomer").list();
        session.close();
        return listReviews;
    }

    public List<Reviews> getReviewsOrderByRating(){
        session = HibernateUtils.getSession();
        List<Reviews> listReviews = session.createQuery("select r from Reviews r order by rating").list();
        session.close();
        return listReviews;
    }
}

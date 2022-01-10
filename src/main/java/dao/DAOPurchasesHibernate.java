package dao;

import model.Customers;
import model.Purchases;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOPurchasesHibernate {
    Session session;

    public List<Purchases> getAll(){
        session = HibernateUtils.getSession();
        List<Purchases> listPurchases = session.createQuery("from Purchases").list();
        session.close();
        return listPurchases;
    }

    public void add(Purchases purchase){
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.save(purchase);
            transaction.commit();
            session.close();
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

    public void delete(Purchases purchase){
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.delete(purchase);
            transaction.commit();
            session.close();
        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Logger.getLogger(DAOPurchasesHibernate.class.getName()).log(Level.SEVERE, null, e);
        }
        finally {
            session.close();
        }
    }

    public List<Purchases> getPurchasesByIdCustomer(Customers customer){
        session = HibernateUtils.getSession();
        Query query = session.createQuery("select p from Purchases p where customersByIdCustomer.idCustomer = :idCustomer");
        query.setParameter("idCustomer", customer.getIdCustomer());
        List<Purchases> listPurchases = query.list();
        session.close();
        return listPurchases;
    }

    public List<Purchases> getPurchasesOrderByItem(){
        session = HibernateUtils.getSession();
        Query query = session.createQuery("select p from Purchases p order by itemsByIdItem.idItem");
        List<Purchases> purchasesList = query.list();
        session.close();
        return purchasesList;
    }

    public List<Purchases> getPurchasesOrderByCustomer(){
        session = HibernateUtils.getSession();
        Query query = session.createQuery("select p from Purchases p order by customersByIdCustomer.idCustomer");
        List<Purchases> purchasesList = query.list();
        session.close();
        return purchasesList;
    }

    public List<Purchases> getPurchasesOrderByDate(){
        session = HibernateUtils.getSession();
        Query query = session.createQuery("select p from Purchases p order by date");
        List<Purchases> purchasesList = query.list();
        session.close();
        return purchasesList;
    }

}

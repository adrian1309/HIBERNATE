package dao;

import model.Customers;
import model.Items;
import model.Purchases;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.time.LocalDate;
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

    public List<Purchases> getPurchasesListByItem(Items item){
        session = HibernateUtils.getSession();
        Query query = session.createQuery("select p from Purchases p where p.itemsByIdItem.idItem = :idItem");
        query.setParameter("idItem", item.getIdItem());
        List<Purchases> purchasesList = query.list();
        session.close();
        return purchasesList;
    }

    public List<Purchases> getPurchasesListByCustomer(Customers customer){
        session = HibernateUtils.getSession();
        Query query = session.createQuery("select p from Purchases p where p.customersByIdCustomer.idCustomer = :idCustomer");
        query.setParameter("idCustomer", customer.getIdCustomer());
        List<Purchases> purchasesList = query.list();
        session.close();
        return purchasesList;
    }

    public List<Purchases> getPurchasesListByDate(LocalDate localDate1, LocalDate localDate2){
        Date date1 = Date.valueOf(localDate1.toString());
        Date date2 = Date.valueOf(localDate2.toString());
        session = HibernateUtils.getSession();
        Query query = session.createQuery("select p from Purchases p where date between :date1 and :date2");
        query.setParameter("date1", date1);
        query.setParameter("date2", date2);
        List<Purchases> purchasesList = query.list();
        session.close();
        return purchasesList;
    }

    public List<Purchases> getPurchasesOrderByDateNoUsado(){
        session = HibernateUtils.getSession();
        Query query = session.createQuery("select p from Purchases p order by date");
        List<Purchases> purchasesList = query.list();
        session.close();
        return purchasesList;
    }

}

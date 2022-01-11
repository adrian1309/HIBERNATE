package dao;

import model.Items;
import model.Reviews;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOItemsHibernate {
    Session session;

    public List<Items> getAll() {
        List<Items> list = null;
        session = HibernateUtils.getSession();
        Query query = session.createQuery(" select it from Items as it ");
        list = query.list();
        //List queryList = session.createQuery(" select it from Items as it ").list;
        session.close();
        return list;
    }

    public Items getById(int idItem) {
        session = HibernateUtils.getSession();
        // con load es LAZY, con get EAGER
        Items item = session.get(Items.class, idItem);
        session.close();
        return item;
    }

    public void add(Items item) {
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }



    public void delete(Items item) {
        Transaction transaction = null;
        try {
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.delete(item);
            transaction.commit();

        } catch (ConstraintViolationException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Logger.getLogger(DAOItemsHibernate.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            session.close();
        }

    }

    public void update(Items item) {
        Transaction transaction = null;
        try {
            // Open session
            session = HibernateUtils.getSession();
            transaction = session.beginTransaction();
            session.update(item);
            transaction.commit();


        } catch (ConstraintViolationException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Logger.getLogger(DAOItemsHibernate.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            session.close();
        }
    }


    public int getPurchasesLastMonthFail(Items item){
        session = HibernateUtils.getSession();
        Query query = session.createQuery("select count(idPurchase) from Purchases p where p.itemsByIdItem.idItem = :idItem and date BETWEEN DATE_FORMAT(NOW() - INTERVAL 1 MONTH, '%Y-%m-01 00:00:00') AND DATE_FORMAT(LAST_DAY(current_date() - 30, '%Y-%m-%d 23:59:59'))");
        query.setParameter("idItem", item.getIdItem());
        Integer result = (Integer) query.uniqueResult();
        session.close();
        return result;
    }

    public long getPurchasesLastMonth(Items item){
        Calendar calendar = new GregorianCalendar();
        int monthActual = calendar.get(Calendar.MONTH) + 1;
        int monthBefore = Math.abs(monthActual - 13);
        System.out.println(monthBefore);

        session = HibernateUtils.getSession();
        Query query = session.createQuery("select count(idPurchase) from Purchases p " +
                " where p.itemsByIdItem.idItem = :idItem and month(date) like :month");
        query.setParameter("idItem", item.getIdItem());
        query.setParameter("month", monthBefore);
        Long result = (Long) query.uniqueResult();
        session.close();
        return result;
    }

    public double getAvgRating(Items item){
        session = HibernateUtils.getSession();
        Query query = session.createQuery("select avg(rating) from Reviews r where r.itemsByIdItem.idItem = :idItem");
        query.setParameter("idItem", item.getIdItem());
        Double result = (Double) query.uniqueResult();
        session.close();
        if(result == null){
            result = -1.0;
        }
        return result;
    }

    public List<Reviews> getOrderByDateASC(Items item){
        session = HibernateUtils.getSession();
        Query query = session.createQuery("select r from Reviews r where r.itemsByIdItem.idItem = :idItem order by date asc");
        query.setParameter("idItem", item.getIdItem());
        List<Reviews> result = query.list();
        session.close();
        return result;
    }

    public List<Reviews> getOrderByDateDESC(Items item){
        session = HibernateUtils.getSession();
        Query query = session.createQuery("select r from Reviews r where r.itemsByIdItem.idItem = :idItem order by date desc");
        query.setParameter("idItem", item.getIdItem());
        List<Reviews> result = query.list();
        session.close();
        return result;
    }

    public List<Reviews> getOrderByRatingASC(Items item){
        session = HibernateUtils.getSession();
        Query query = session.createQuery("select r from Reviews r where r.itemsByIdItem.idItem = :idItem order by rating asc");
        query.setParameter("idItem", item.getIdItem());
        List<Reviews> result = query.list();
        session.close();
        return result;
    }

    public List<Reviews> getOrderByRatingDESC(Items item){
        session = HibernateUtils.getSession();
        Query query = session.createQuery("select r from Reviews r where r.itemsByIdItem.idItem = :idItem order by rating desc ");
        query.setParameter("idItem", item.getIdItem());
        List<Reviews> result = query.list();
        session.close();
        return result;
    }


}

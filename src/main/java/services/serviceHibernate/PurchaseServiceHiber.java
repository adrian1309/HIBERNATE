package services.serviceHibernate;

import dao.DAOPurchasesHibernate;
import model.Customers;
import model.Purchases;
import services.PurchaseService;

import java.sql.Date;
import java.util.List;

public class PurchaseServiceHiber implements PurchaseService {
    @Override
    public void addPurchase(Purchases purchase) {
        DAOPurchasesHibernate dao = new DAOPurchasesHibernate();
        dao.add(purchase);
    }

    @Override
    public void deletePurchase(Purchases purchase) {
        DAOPurchasesHibernate dao = new DAOPurchasesHibernate();
        dao.delete(purchase);
    }

    @Override
    public List<Purchases> getAllPurchases() {
        DAOPurchasesHibernate dao = new DAOPurchasesHibernate();
        return dao.getAll();
    }

    @Override
    public List<Purchases> searchByDate(Date date) {
        DAOPurchasesHibernate dao = new DAOPurchasesHibernate();
        return null;
    }

    @Override
    public List<Purchases> getPurchasesByIdCustomer(Customers customer){
        DAOPurchasesHibernate dao = new DAOPurchasesHibernate();
        return dao.getPurchasesByIdCustomer(customer);
    }

    @Override
    public List<Purchases> getPurchasesOrderByItem() {
        DAOPurchasesHibernate dao = new DAOPurchasesHibernate();
        return dao.getPurchasesOrderByItem();
    }

    @Override
    public List<Purchases> getPurchasesOrderByCustomer() {
        DAOPurchasesHibernate dao = new DAOPurchasesHibernate();
        return dao.getPurchasesOrderByCustomer();
    }

    @Override
    public List<Purchases> getPurchasesOrderByDate() {
        DAOPurchasesHibernate dao = new DAOPurchasesHibernate();
        return dao.getPurchasesOrderByDate();
    }


}

package services.serviceHibernate;

import dao.DAOPurchasesHibernate;
import model.Customers;
import model.Items;
import model.Purchases;
import services.PurchaseService;

import java.sql.Date;
import java.time.LocalDate;
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
    public List<Purchases> getPurchasesListByItem(Items item) {
        DAOPurchasesHibernate dao = new DAOPurchasesHibernate();
        return dao.getPurchasesListByItem(item);
    }

    @Override
    public List<Purchases> getPurchasesListByCustomer(Customers customer) {
        DAOPurchasesHibernate dao = new DAOPurchasesHibernate();
        return dao.getPurchasesListByCustomer(customer);
    }

    @Override
    public List<Purchases> getPurchasesListByDate(LocalDate date1, LocalDate date2) {
        DAOPurchasesHibernate dao = new DAOPurchasesHibernate();
        return dao.getPurchasesListByDate(date1, date2);
    }


}

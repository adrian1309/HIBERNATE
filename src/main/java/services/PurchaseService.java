package services;

import model.Customers;
import model.Purchases;

import java.sql.Date;
import java.util.List;

public interface PurchaseService {
    void addPurchase(Purchases purchase);

    void deletePurchase(Purchases purchase);

    List<Purchases> getAllPurchases();

    List<Purchases> searchByDate(Date date);

    List<Purchases> getPurchasesByIdCustomer(Customers customer);

    List<Purchases> getPurchasesOrderByItem();

    List<Purchases> getPurchasesOrderByCustomer();

    List<Purchases> getPurchasesOrderByDate();
}

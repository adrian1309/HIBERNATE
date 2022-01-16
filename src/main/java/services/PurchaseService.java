package services;

import model.Customers;
import model.Items;
import model.Purchases;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface PurchaseService {
    void addPurchase(Purchases purchase);

    void deletePurchase(Purchases purchase);

    List<Purchases> getAllPurchases();

    List<Purchases> searchByDate(Date date);

    List<Purchases> getPurchasesByIdCustomer(Customers customer);

    List<Purchases> getPurchasesListByItem(Items item);

    List<Purchases> getPurchasesListByCustomer(Customers customer);

    List<Purchases> getPurchasesListByDate(LocalDate date1, LocalDate date2);
}

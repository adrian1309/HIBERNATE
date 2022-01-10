/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.purchases;


import dao.DAOCustomersHibernate;
import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import model.Customers;
import model.Items;
import model.Purchases;
import model.PurchasesData3;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;


public class FXMLAddPurchasesController implements Initializable {


    @FXML
    private TableView<PurchasesData3> purchasesTable;
    @FXML
    private TableColumn<PurchasesData3, Integer> idPurchaseColumnTable;
    @FXML
    private TableColumn<PurchasesData3, Integer> idCustomerColumnTable;
    @FXML
    private TableColumn<PurchasesData3, Integer> idItemColumnTable;
    @FXML
    private TableColumn<PurchasesData3, LocalDate> dateColumnTable;
    @FXML
    private ComboBox<Customers> customerBox;
    @FXML
    private ComboBox<Items> itemBox;
    @FXML
    private DatePicker dateBox;

    Alert alerta; //porque hay que inicializar el alert

    private FXMLPrincipalController principalController = new FXMLPrincipalController();

    
    public void loadPurchasesList() {
        purchasesTable.getItems().clear();
        customerBox.getSelectionModel().clearSelection();
        itemBox.getSelectionModel().clearSelection();
        dateBox.getEditor().clear();
        List<Purchases> purchasesList = principalController.getPurchaseService().getAllPurchases();
        for (Purchases p : purchasesList){
            PurchasesData3 purchaseData3 = new PurchasesData3();
            purchaseData3.setIdPurchase(p.getIdPurchase());
            purchaseData3.setIdItem(p.getItemsByIdItem().getIdItem());
            purchaseData3.setIdCustomer(p.getCustomersByIdCustomer().getIdCustomer());
            purchaseData3.setDate(p.getDate());
            purchasesTable.getItems().add(purchaseData3);
        }
    }

    public void loadItemsList() {
        itemBox.getItems().clear();
        itemBox.getItems().addAll(principalController.getItemService().getAll());
        itemBox.setConverter(new StringConverter<Items>() {
            @Override
            public String toString(Items item) {
                return item == null ? null : item.getName();
            }

            @Override
            public Items fromString(String s) {
                return null;
            }
        });

    }

    public void loadCustomersList() {
        List<Customers> customerBoxContent = principalController.getCustomerService().getAllCustomers();
        customerBox.getItems().clear();
        if (principalController.getEsAdmin()){
            customerBox.getItems().addAll(customerBoxContent);
        }else{
            DAOCustomersHibernate dao = new DAOCustomersHibernate();
            customerBox.getItems().add(dao.getCustomerByUserId(principalController.getUser()));
        }


        customerBox.setConverter(new StringConverter<Customers>() {
            @Override
            public String toString(Customers customer) {
                return customer == null ? null : customer.getName();
            }

            @Override
            public Customers fromString(String s) {
                return null;
            }
        });


    }



    public void addPurchase() {
        try {
            Purchases purchase = new Purchases();

            Customers customer = customerBox.getSelectionModel().getSelectedItem();
            Items item = itemBox.getSelectionModel().getSelectedItem();

            purchase.setCustomersByIdCustomer(customer);
            purchase.setItemsByIdItem(item);
            purchase.setDate(Date.valueOf(dateBox.getValue()));

            principalController.getPurchaseService().addPurchase(purchase);
            alert("Enhorabuena!", "Purchase añadido", Alert.AlertType.CONFIRMATION);
            customerBox.getSelectionModel().clearSelection();
            itemBox.getSelectionModel().clearSelection();
            dateBox.getEditor().clear();

            purchasesTable.getItems().clear();
            List<Purchases> purchasesList = principalController.getPurchaseService().getAllPurchases();
            for (Purchases p : purchasesList){
                PurchasesData3 purchaseData3 = new PurchasesData3();
                purchaseData3.setIdPurchase(p.getIdPurchase());
                purchaseData3.setIdItem(p.getItemsByIdItem().getIdItem());
                purchaseData3.setIdCustomer(p.getCustomersByIdCustomer().getIdCustomer());
                purchase.setDate(p.getDate());
                purchasesTable.getItems().add(purchaseData3);
            }

        } catch (Exception e){
            alert("ERROR", "Hay un error. Comprueba los datos", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void alert(String titulo, String texto, Alert.AlertType tipo){
        alerta.setTitle(titulo);
        alerta.setContentText(texto);
        alerta.setAlertType(tipo);
        alerta.showAndWait();
    }

    public void setPrincipal(FXMLPrincipalController fxmlPrincipalController){
        this.principalController = fxmlPrincipalController;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alerta = new Alert(Alert.AlertType.CONFIRMATION);
        idPurchaseColumnTable.setCellValueFactory(new PropertyValueFactory<PurchasesData3, Integer>("idPurchase"));
        idCustomerColumnTable.setCellValueFactory(new PropertyValueFactory<PurchasesData3, Integer>("idCustomer"));
        idItemColumnTable.setCellValueFactory(new PropertyValueFactory<PurchasesData3, Integer>("idItem"));
        dateColumnTable.setCellValueFactory(new PropertyValueFactory<PurchasesData3, LocalDate>("date"));
    }
}

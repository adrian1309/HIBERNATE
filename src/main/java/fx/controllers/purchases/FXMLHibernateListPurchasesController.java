package fx.controllers.purchases;

import fx.controllers.FXMLPrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import model.Customers;
import model.Items;
import model.Purchases;
import model.PurchasesData2;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLHibernateListPurchasesController implements Initializable {

    @FXML
    private ComboBox<String> cbOrderBy;
    @FXML
    private ListView<Object> lvItemOrCustomers;
    @FXML
    private DatePicker dpFirstPeriod;
    @FXML
    private DatePicker dpSecondPeriod;
    @FXML
    private Button btnOrder;
    @FXML
    private ListView<PurchasesData2> lvPurchases;

    private FXMLPrincipalController principalController = new FXMLPrincipalController();

    public void setPrincipal(FXMLPrincipalController fxmlPrincipalController) {
        principalController = fxmlPrincipalController;
    }

    public void loadItemsOrCustomer(){
        String selectedInComboBox = cbOrderBy.getSelectionModel().getSelectedItem();
        switch (selectedInComboBox) {
            case "Item":
                lvPurchases.getItems().clear();
                loadPurchases();
                lvItemOrCustomers.getItems().clear();
                dpFirstPeriod.getEditor().clear();
                dpSecondPeriod.getEditor().clear();
                lvItemOrCustomers.setDisable(false);
                dpFirstPeriod.setDisable(true);
                dpSecondPeriod.setDisable(true);
                btnOrder.setDisable(true);
                List<Items> itemsList = principalController.getItemService().getAll();
                for (Items i: itemsList){
                    lvItemOrCustomers.getItems().add(i);
                }
                break;
            case "Customer":
                lvPurchases.getItems().clear();
                loadPurchases();
                lvItemOrCustomers.getItems().clear();
                dpFirstPeriod.getEditor().clear();
                dpSecondPeriod.getEditor().clear();
                lvItemOrCustomers.setDisable(false);
                dpFirstPeriod.setDisable(true);
                dpSecondPeriod.setDisable(true);
                btnOrder.setDisable(true);
                List<Customers> customersList = principalController.getCustomerService().getAllCustomers();
                for (Customers c: customersList){
                    lvItemOrCustomers.getItems().add(c);
                }
                break;
            case "Period Date":
                lvPurchases.getItems().clear();
                loadPurchases();
                lvItemOrCustomers.getItems().clear();
                dpFirstPeriod.getEditor().clear();
                dpSecondPeriod.getEditor().clear();
                lvItemOrCustomers.setDisable(true);
                dpFirstPeriod.setDisable(false);
                dpSecondPeriod.setDisable(false);
                btnOrder.setDisable(false);
                break;
        }
    }

    public void loadPurchases() {
        List<Purchases> listPurchases = principalController.getPurchaseService().getAllPurchases();
        createPurchasesData2(listPurchases);
    }

    public void loadComboBox () {
        cbOrderBy.getItems().addAll("Item", "Customer", "Period Date");
    }

    public void loadPurchasesListByItemOrCustomer(MouseEvent mouseEvent) {
        PurchasesData2 purchaseData2 = null;

        if (cbOrderBy.getSelectionModel().getSelectedItem().equals("Item")){
            Object itemOrCustomerSelected = lvItemOrCustomers.getSelectionModel().getSelectedItem();
            List<Purchases> listPurchases = principalController.getPurchaseService().getPurchasesListByItem((Items) itemOrCustomerSelected);
            lvPurchases.getItems().clear();
            for (Purchases p: listPurchases){
                purchaseData2 = new PurchasesData2();
                purchaseData2.setIdItem(p.getItemsByIdItem().getIdItem());
                purchaseData2.setIdCustomer(p.getCustomersByIdCustomer().getIdCustomer());
                purchaseData2.setDate(p.getDate());
                lvPurchases.getItems().add(purchaseData2);
            }

        }else if (cbOrderBy.getSelectionModel().getSelectedItem().equals("Customer")){
            Object itemOrCustomerSelected = lvItemOrCustomers.getSelectionModel().getSelectedItem();
            List<Purchases> listPurchases = principalController.getPurchaseService().getPurchasesListByCustomer((Customers) itemOrCustomerSelected);
            lvPurchases.getItems().clear();
            for (Purchases p: listPurchases){
                purchaseData2 = new PurchasesData2();
                purchaseData2.setIdItem(p.getItemsByIdItem().getIdItem());
                purchaseData2.setIdCustomer(p.getCustomersByIdCustomer().getIdCustomer());
                purchaseData2.setDate(p.getDate());
                lvPurchases.getItems().add(purchaseData2);
            }
        }

    }

    public void clearLists () {
        cbOrderBy.getItems().clear();
        cbOrderBy.getSelectionModel().clearSelection();
        lvItemOrCustomers.getItems().clear();
        lvItemOrCustomers.setDisable(true);
        dpFirstPeriod.setDisable(true);
        dpSecondPeriod.setDisable(true);
        btnOrder.setDisable(true);
        lvPurchases.getItems().clear();

        }

    public void btnOrderPeriodClicked(ActionEvent actionEvent) {
        try{
            PurchasesData2 purchaseData2 = null;
            LocalDate firstPeriod = dpFirstPeriod.getValue();
            LocalDate secondPeriod = dpSecondPeriod.getValue();
            List<Purchases> listPurchases = principalController.getPurchaseService().getPurchasesListByDate(firstPeriod, secondPeriod);
            lvPurchases.getItems().clear();
            for (Purchases p: listPurchases) {
                purchaseData2 = new PurchasesData2();
                purchaseData2.setIdItem(p.getItemsByIdItem().getIdItem());
                purchaseData2.setIdCustomer(p.getCustomersByIdCustomer().getIdCustomer());
                purchaseData2.setDate(p.getDate());
                lvPurchases.getItems().add(purchaseData2);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createPurchasesData2(List<Purchases> purchasesListOrderByItem) {
        for(Purchases p : purchasesListOrderByItem){
            PurchasesData2 purchasesData2 = new PurchasesData2();
            purchasesData2.setIdItem(p.getItemsByIdItem().getIdItem());
            purchasesData2.setIdCustomer(p.getCustomersByIdCustomer().getIdCustomer());
            purchasesData2.setDate(p.getDate());
            lvPurchases.getItems().add(purchasesData2);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}

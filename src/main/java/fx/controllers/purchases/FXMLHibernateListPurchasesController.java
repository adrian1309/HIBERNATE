package fx.controllers.purchases;

import fx.controllers.FXMLPrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Customers;
import model.Items;
import model.Purchases;
import model.PurchasesData2;

import java.net.URL;
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
                lvItemOrCustomers.getItems().clear();
                lvItemOrCustomers.setDisable(false);
                List<Items> itemsList = principalController.getItemService().getAll();
                for (Items i: itemsList){
                    lvItemOrCustomers.getItems().add(i.toStringIdAndName());
                }
                break;
            case "Customer":
                lvItemOrCustomers.getItems().clear();
                lvItemOrCustomers.setDisable(false);
                List<Customers> customersList = principalController.getCustomerService().getAllCustomers();
                for (Customers c: customersList){
                    lvItemOrCustomers.getItems().add(c.toStringIdAndName());
                }
            case "Period Date":
                dpFirstPeriod.setDisable(false);
                dpSecondPeriod.setDisable(false);
                btnOrder.setDisable(false);

        }
    }

    public void loadPurchases() {
        List<Purchases> listPurchases = principalController.getPurchaseService().getAllPurchases();
        createPurchasesData2(listPurchases);
    }

    public void loadComboBox () {
            cbOrderBy.getItems().addAll("Item", "Customer", "Period Date");
        }

    public void clearLists () {
        cbOrderBy.getSelectionModel().clearSelection();
        lvItemOrCustomers.getItems().clear();
        lvItemOrCustomers.setDisable(true);
        dpFirstPeriod.setDisable(false);
        dpSecondPeriod.setDisable(false);
        btnOrder.setDisable(false);
        lvPurchases.getItems().clear();

        }

    public void btnOrderPeriodClicked(ActionEvent actionEvent) {
        String selectedInComboBox = cbOrderBy.getSelectionModel().getSelectedItem();
        switch (selectedInComboBox) {
            case "Item":
                lvPurchases.getItems().clear();
                List<Purchases> purchasesListOrderByItem = principalController.getPurchaseService().getPurchasesOrderByItem();
                createPurchasesData2(purchasesListOrderByItem);
                break;
            case "Customer":
                lvPurchases.getItems().clear();
                List<Purchases> purchasesListOrderByCustomer = principalController.getPurchaseService().getPurchasesOrderByCustomer();
                createPurchasesData2(purchasesListOrderByCustomer);
                break;
            case "Period Date":
                lvPurchases.getItems().clear();
                List<Purchases> purchasesListOrderByDate = principalController.getPurchaseService().getPurchasesOrderByDate();
                createPurchasesData2(purchasesListOrderByDate);
                break;
            default:
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("INFORMATION");
                alert.setContentText("You must selected one item in the combobox to order the list");
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.showAndWait();
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

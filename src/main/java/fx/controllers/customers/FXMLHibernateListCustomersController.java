package fx.controllers.customers;

import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Customers;
import model.Purchases;
import model.Reviews;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLHibernateListCustomersController implements Initializable {

    @FXML
    private ListView<Customers> lvCustomers;
    @FXML
    private TableView<Customers> selectedCustomerTable;
    @FXML
    private TableColumn<Customers, Integer> telephoneColumnTable;
    @FXML
    private TableColumn<Customers, String> addressColumnTable;
    @FXML
    private ListView<Purchases> lvPurchasesList;

    private FXMLPrincipalController fxmlPrincipalController = new FXMLPrincipalController();


    public void setPrincipal(FXMLPrincipalController principalController) {
        fxmlPrincipalController = principalController;
    }

    public void loadCustomers() {
        List<Customers> listCustomers = fxmlPrincipalController.getCustomerService().getAllCustomers();
        lvCustomers.getItems().addAll(listCustomers);
    }

    public void loadClickCustomer(MouseEvent mouseEvent) {
        try {
            selectedCustomerTable.getItems().clear();
            Customers customer = lvCustomers.getSelectionModel().getSelectedItem();
            selectedCustomerTable.getItems().add(customer);
            List<Purchases> purchaseList = fxmlPrincipalController.getPurchaseService().getPurchasesByIdCustomer(customer);
            lvPurchasesList.getItems().clear();
            for(Purchases p : purchaseList){
                lvPurchasesList.getItems().add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadPantallaEmergenteCustomers(MouseEvent mouseEvent) {
        try {
            Purchases p = lvPurchasesList.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/customers/FXMLPantallaEmergenteCustomers.fxml"));

            Parent root = loader.load();

            FXMLPantallaEmergenteCustomersController controller = loader.getController();
            controller.loadPurchasesData(p);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clearLists(){
        lvCustomers.getItems().clear();
        selectedCustomerTable.getItems().clear();
        lvPurchasesList.getItems().clear();

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        telephoneColumnTable.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("telephone"));
        addressColumnTable.setCellValueFactory(new PropertyValueFactory<Customers, String>("address"));

    }


}

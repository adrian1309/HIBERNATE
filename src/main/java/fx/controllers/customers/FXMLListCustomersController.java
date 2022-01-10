package fx.controllers.customers;

import fx.controllers.FXMLPrincipalController;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customers;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLListCustomersController implements Initializable {

    public TableView<Customers> customersTable;
    public TableColumn<Customers, String> nameColumnTable;
    public TableColumn<Customers, Integer> phoneColumnTable;
    public TableColumn<Customers, String> addressColumnTable;

    private FXMLPrincipalController fxmlPrincipalController = new FXMLPrincipalController();



    public void setPrincipal(FXMLPrincipalController principalController){
        fxmlPrincipalController = principalController;
    }

    public void loadCustomerList(){
        customersTable.getItems().clear();
        customersTable.getItems().addAll(fxmlPrincipalController.getCustomerService().getAllCustomers());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameColumnTable.setCellValueFactory(new PropertyValueFactory<Customers, String>("name"));
        phoneColumnTable.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("telephone"));
        addressColumnTable.setCellValueFactory(new PropertyValueFactory<Customers, String>("address"));
    }
}

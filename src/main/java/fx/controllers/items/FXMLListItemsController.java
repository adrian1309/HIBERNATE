/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.items;

import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Items;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLListItemsController implements Initializable {

    @FXML
    private TableView<Items> itemsTable;
    @FXML
    private TableColumn<Items, Integer> idColumnTable;
    @FXML
    private TableColumn<Items, String> nameColumnTable;
    @FXML
    private TableColumn<Items, Double> priceColumnTable;


    private FXMLPrincipalController fxmlPrincipalController = new FXMLPrincipalController();


    public void setPrincipal(FXMLPrincipalController principalController){
        fxmlPrincipalController = principalController;
    }

    public void loadItemsList() {
        itemsTable.getItems().clear();
        itemsTable.getItems().addAll(fxmlPrincipalController.getItemService().getAll());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idColumnTable.setCellValueFactory(new PropertyValueFactory<Items, Integer>("idItem"));
        nameColumnTable.setCellValueFactory(new PropertyValueFactory<Items, String>("name"));
        priceColumnTable.setCellValueFactory(new PropertyValueFactory<Items, Double>("price"));
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.customers;

import fx.controllers.FXMLPrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import model.Customers;
import model.Purchases;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FXMLDeleteCustomerController implements Initializable {

    @FXML
    private ListView<Customers> customerBox;

    private FXMLPrincipalController principalController =  new FXMLPrincipalController();

    private Alert alerta;

    public void deleteCustomer(ActionEvent actionEvent) {
        try {
            Customers customerSelected = customerBox.getSelectionModel().getSelectedItem();
            List<Purchases> purchaseList = principalController.getPurchaseService().getAllPurchases();
            List<Purchases> purchasesWithCustomers = purchaseList.stream().filter(p -> p.getCustomersByIdCustomer().getIdCustomer() == customerSelected.getIdCustomer()).collect(Collectors.toList());
            if (!purchasesWithCustomers.isEmpty()){
                alert("WARNING", "Este customer tiene purchases por lo que no se puede eliminar. \n"+
                        "Elimina primero las purchases.", Alert.AlertType.WARNING);
            }else{
                try {
                    principalController.getCustomerService().deleteCustomer(customerSelected);
                    customerBox.getItems().remove(customerSelected);
                    alert("BIEN", "Customer borrado", Alert.AlertType.CONFIRMATION);
                }catch (Exception e){
                    e.printStackTrace();
                    alert("ERROR", "Customer no borrado", Alert.AlertType.ERROR);
                }

            }
        }catch (Exception e){
            alert("ERROR", "Error interno", Alert.AlertType.ERROR);
        }
    }

    private void alert(String titulo, String texto, Alert.AlertType tipo){
        alerta.setTitle(titulo);
        alerta.setContentText(texto);
        alerta.setAlertType(tipo);
        alerta.showAndWait();
    }


    public void setPrincipal(FXMLPrincipalController fxmlPrincipalController){
        principalController = fxmlPrincipalController;
    }

    public void loadCustomerList() {
        customerBox.getItems().clear();
        customerBox.getItems().addAll(principalController.getCustomerService().getAllCustomers());

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alerta = new Alert(Alert.AlertType.CONFIRMATION);
    }
}

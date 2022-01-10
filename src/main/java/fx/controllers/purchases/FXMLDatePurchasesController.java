/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.purchases;

import fx.controllers.FXMLPrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import model.Purchases;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class FXMLDatePurchasesController implements Initializable {

    @FXML
    private DatePicker dateBox;
    @FXML
    private ListView<Purchases> purchaseList;

    Alert alerta;

    private FXMLPrincipalController fxmlPrincipalController = new FXMLPrincipalController();

    public void setPrincipal(FXMLPrincipalController principalController){
        fxmlPrincipalController = principalController;
    }


    public void searchByDate(ActionEvent actionEvent) {
        try {
            //esto es sin usar el dao
            List<Purchases> purchaseListService = fxmlPrincipalController.getPurchaseService().getAllPurchases();
            List<Purchases> listDatePurchase = fxmlPrincipalController.getPurchaseService().searchByDate(Date.valueOf(dateBox.getValue()));
            if (listDatePurchase.isEmpty()){
                alert("INFO", "No existe una compra con esa fecha.", Alert.AlertType.INFORMATION);
                dateBox.getEditor().clear();
                purchaseList.getItems().clear();
                purchaseList.getItems().addAll(purchaseListService);
            }else{
                purchaseList.getItems().clear();
                purchaseList.getItems().addAll(listDatePurchase);
                dateBox.getEditor().clear();
            }
        }catch (Exception e){
            dateBox.getEditor().clear();
            alert("ERROR", "Error en la busqueda", Alert.AlertType.ERROR);

        }
    }

    public void loadPage(){
        dateBox.getEditor().clear();
        purchaseList.getItems().clear();
        purchaseList.getItems().addAll(fxmlPrincipalController.getPurchaseService().getAllPurchases());
    }

    private void alert(String titulo, String texto, Alert.AlertType tipo){
        alerta.setTitle(titulo);
        alerta.setContentText(texto);
        alerta.setAlertType(tipo);
        alerta.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alerta = new Alert(Alert.AlertType.ERROR);
    }
}

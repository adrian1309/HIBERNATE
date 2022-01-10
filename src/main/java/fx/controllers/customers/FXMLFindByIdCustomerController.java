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
import javafx.scene.control.TextField;
import model.Customers;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLFindByIdCustomerController implements Initializable {

    @FXML
    private TextField tfID;
    @FXML
    private ListView<Customers> lvCustomer;

    Alert alerta;
    private FXMLPrincipalController fxmlPrincipalController = new FXMLPrincipalController();

    public void setPrincipal(FXMLPrincipalController principalController){
        fxmlPrincipalController = principalController;
    }
    
     public void searchById(ActionEvent actionEvent) {
        try {
            int idIntroducido = Integer.parseInt(tfID.getText());
            Customers customerCreado = new Customers();
            customerCreado.setIdCustomer(idIntroducido);
            Customers customer = fxmlPrincipalController.getCustomerService().getCustomer(customerCreado);
            if(customer != null) {
                lvCustomer.getItems().add(customer);
                tfID.clear();
            }else{
                alert("INFO", "No existe", Alert.AlertType.INFORMATION);
                lvCustomer.getItems().clear();
                tfID.clear();
            }
        }catch (NumberFormatException e){
            alert("WARNING", "El ID debe ser un numero", Alert.AlertType.WARNING);
        }catch (Exception e){
            alert("ERROR", "Error en la búsqueda", Alert.AlertType.ERROR);
        }
    }

    public void loadPage(){
        lvCustomer.getItems().clear();
        tfID.clear();
    }

    private void alert(String titulo, String texto, Alert.AlertType tipo){
        alerta.setTitle(titulo);
        alerta.setContentText(texto);
        alerta.setAlertType(tipo);
        alerta.showAndWait();
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alerta = new Alert(Alert.AlertType.CONFIRMATION);
    }



}

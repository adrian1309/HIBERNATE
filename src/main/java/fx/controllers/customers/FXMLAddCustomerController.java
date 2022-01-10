/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.customers;

import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Customers;
import model.Users;

import java.net.URL;
import java.util.ResourceBundle;


public class FXMLAddCustomerController implements Initializable {

    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    @FXML
    private TextField nameBox;
    @FXML
    private TextField phoneBox;
    @FXML
    private TextField addressBox;

    Alert alerta; //porque hay que inicializar el alert

    private FXMLPrincipalController principalController = new FXMLPrincipalController();

    public void addCustomer() {
        try {
            Users user = new Users();
            user.setIdUser(-1);
            user.setUserName(userName.getText());
            user.setPassword(password.getText());

            Customers customer = new Customers();
            customer.setIdCustomer(user.getIdUser());
            customer.setName(nameBox.getText());
            customer.setTelephone(Integer.parseInt(phoneBox.getText()));
            customer.setAddress(addressBox.getText());
            principalController.getCustomerService().addCustomer(customer, user);

            alert("Enhorabuena!", "Customer añadido", Alert.AlertType.CONFIRMATION);

        } catch (NumberFormatException e) {
            alert("Invalido", "La id y el telefono deben ser numerica", Alert.AlertType.WARNING);
            e.printStackTrace();
        } catch (Exception e){
            alert("ERROR", "Hay un error. Comprueba los datos", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public void setPrincipal(FXMLPrincipalController fxmlPrincipalController){
        this.principalController = fxmlPrincipalController;
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

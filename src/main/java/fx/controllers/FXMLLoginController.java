/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Users;
import org.springframework.dao.EmptyResultDataAccessException;

import java.net.URL;
import java.util.ResourceBundle;


public class FXMLLoginController implements Initializable {

    
    // Esto es para poder coger lo que pone en ese campo y meterlo en este caso en el atributo usuario
    // del controlador principal.
    @FXML
    private TextField fxUser;
    @FXML
    private TextField passBox;
    @FXML
    private Label errorBox;

    Alert alert =  new Alert(Alert.AlertType.CONFIRMATION);
    
    //Referencia al controlador principal para poder acceder a el, junto con su set para poder cambiarlo.
    private FXMLPrincipalController principal;

    public void setPrincipal(FXMLPrincipalController principal) {
        this.principal = principal;
    }
    
    
    public void clickLogin(){
        try {
            Users user = new Users();
            user.setUserName(fxUser.getText());
            user.setPassword(passBox.getText());
            Users userDevuelto = principal.getCustomerService().login(user);
            System.out.println(userDevuelto);
            //El Admin = 0, y los customers > 0
            if (userDevuelto.getIdUser() != -1) {
                int esAdmin = principal.getCustomerService().checkUserAdminOrCustomer(userDevuelto);
                principal.setUser(userDevuelto);
                principal.setUsername(userDevuelto.getUserName());
                switch (esAdmin) {
                    case 0:
                        principal.setEsAdmin(true);
                        principal.chargeWelcome();
                        break;
                    case 1:
                        principal.setEsAdmin(false);
                        principal.chargeWelcome();
                        break;
                    default:
                        errorBox.setText("ERROR IN DATATBASE");
                        break;
                }
                principal.esconderMenu();
            } else {
                errorBox.setText("User or password is wrong");
            }
        }catch (EmptyResultDataAccessException e){
            errorBox.setText("User or password is wrong");
        }
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.reviews;


import dao.DAOCustomersHibernate;
import dao.DAOReviewsHibernate;
import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import model.Customers;
import model.Reviews;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class FXMLdeleteReviewController implements Initializable {

    @FXML
    private ListView<Customers> customerBox;
    @FXML
    private ListView<Reviews> reviewBox;

    Alert alerta;
    private FXMLPrincipalController fxmlPrincipalController = new FXMLPrincipalController();

    public void setPrincipal(FXMLPrincipalController principalController){
        fxmlPrincipalController = principalController;
    }


    public void loadReviewsList() {
        reviewBox.getItems().clear();
        Customers customerSelected = customerBox.getSelectionModel().getSelectedItem();

        DAOReviewsHibernate dao = new DAOReviewsHibernate();
        List<Reviews> reviewsOfCustomer = dao.getReviewByCustomerId(customerSelected);
        if (!reviewsOfCustomer.isEmpty()){
            reviewBox.getItems().addAll(reviewsOfCustomer);
        }else{
            alert("INFO", "Este customer no tiene reviews", Alert.AlertType.INFORMATION);
        }
     }

    public void loadCustomersList() {
        List<Customers> customerList = fxmlPrincipalController.getCustomerService().getAllCustomers();
        customerBox.getItems().clear();
        if (fxmlPrincipalController.getEsAdmin()) {
            customerBox.getItems().addAll(customerList);
        }else{
            DAOCustomersHibernate dao = new DAOCustomersHibernate();
            customerBox.getItems().add(dao.getCustomerByUserId(fxmlPrincipalController.getUser()));
        }
    }

    public void deleteReview() {
        try {
            Reviews reviewSelected = reviewBox.getSelectionModel().getSelectedItem();
            fxmlPrincipalController.getReviewService().deleteReview(reviewSelected);
            alert("NICE", "Review delete", Alert.AlertType.CONFIRMATION);
            reviewBox.getItems().remove(reviewSelected);
        }catch (Exception e){
            alert("INFO", "Review no seleccionada", Alert.AlertType.INFORMATION);
        }
    }

    private void alert(String titulo, String texto, Alert.AlertType tipo){
        alerta.setTitle(titulo);
        alerta.setContentText(texto);
        alerta.setAlertType(tipo);
        alerta.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alerta =new Alert(Alert.AlertType.ERROR);
    }

}

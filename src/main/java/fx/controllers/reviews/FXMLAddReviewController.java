/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.reviews;

import dao.DAOCustomersHibernate;
import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Customers;
import model.Items;
import model.Purchases;
import model.Reviews;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FXMLAddReviewController implements Initializable {

    @FXML
    private ListView<Customers> clientBox;
    @FXML
    private ListView<Purchases> purchaseBox;
    @FXML
    private ComboBox<Integer> ratingBox;
    @FXML
    private TextField titleBox;
    @FXML
    private TextArea textBox;
    @FXML
    private DatePicker dateBox;
    @FXML
    private ListView<Reviews> reviewList;

    Alert alerta;
    private FXMLPrincipalController fxmlPrincipalController = new FXMLPrincipalController();

    public void setPrincipal(FXMLPrincipalController principalController){
        fxmlPrincipalController = principalController;
    }

    public void loadCustomers() {
        List<Customers> customerList = fxmlPrincipalController.getCustomerService().getAllCustomers();
        clientBox.getItems().clear();
        if (fxmlPrincipalController.getEsAdmin()) {
            clientBox.getItems().addAll(customerList);
        }else{
            DAOCustomersHibernate dao = new DAOCustomersHibernate();
            clientBox.getItems().add(dao.getCustomerByUserId(fxmlPrincipalController.getUser()));
        }

    }

     public void loadPurchases() {
        purchaseBox.getItems().clear();
         Customers customerSelected = clientBox.getSelectionModel().getSelectedItem();
         List<Purchases> purchaseList = fxmlPrincipalController.getPurchaseService().getAllPurchases();

         List<Purchases> purchaseOfCustomer = purchaseList.stream()
                 .filter(purchase -> purchase.getCustomersByIdCustomer().getIdCustomer() == customerSelected.getIdCustomer())
                 .collect(Collectors.toList());
         if (!purchaseOfCustomer.isEmpty()){
             purchaseBox.getItems().addAll(purchaseOfCustomer);
         }else{
             alert("INFO", "Este customer no tiene compras", Alert.AlertType.INFORMATION);
         }

     }

     public void loadRating(){
        List<Integer> rating = new ArrayList<>();
        rating.add(1);
        rating.add(2);
        rating.add(3);
        rating.add(4);
        rating.add(5);
        rating.add(6);
        rating.add(7);
        rating.add(8);
        rating.add(9);
        rating.add(10);
        ratingBox.getItems().addAll(rating);
     }

    public void addReview() {
        try {
            Reviews review = new Reviews();
            review.setIdReview(-1);
            review.setRating(ratingBox.getSelectionModel().getSelectedItem());
            review.setTitle(titleBox.getText());
            review.setDescription(textBox.getText());
            review.setDate(Date.valueOf(dateBox.getValue()));
            review.setPurchasesByIdPurchase(purchaseBox.getSelectionModel().getSelectedItem());
            review.setCustomersByIdCustomer(clientBox.getSelectionModel().getSelectedItem());
            Items item =  fxmlPrincipalController.getItemService().
                    getById(purchaseBox.getSelectionModel().getSelectedItem()
                            .getItemsByIdItem().getIdItem());
            review.setItemsByIdItem(item);
            try {
                fxmlPrincipalController.getReviewService().addReview(review);
                alert("ENHORABUENA!", "Review añadida", Alert.AlertType.CONFIRMATION);
                clientBox.getSelectionModel().clearSelection();
                purchaseBox.getSelectionModel().clearSelection();
                ratingBox.getSelectionModel().clearSelection();
                titleBox.clear();
                textBox.clear();
                dateBox.getEditor().clear();
                purchaseBox.getItems().clear();
            }catch (Exception e){
                e.printStackTrace();
                alert("ERROR","Review no añadida", Alert.AlertType.ERROR);
                clientBox.getSelectionModel().clearSelection();
                purchaseBox.getSelectionModel().clearSelection();
                ratingBox.getSelectionModel().clearSelection();
                titleBox.clear();
                textBox.clear();
                dateBox.getEditor().clear();
            }
        }catch (Exception e){
            e.printStackTrace();
            alert("ERROR","Error al añadir. Try again", Alert.AlertType.ERROR);
            clientBox.getSelectionModel().clearSelection();
            purchaseBox.getSelectionModel().clearSelection();
            ratingBox.getSelectionModel().clearSelection();
            titleBox.clear();
            textBox.clear();
            dateBox.getEditor().clear();
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
        alerta = new Alert(Alert.AlertType.ERROR);
    }

}

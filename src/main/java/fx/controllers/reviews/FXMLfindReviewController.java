/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.reviews;

import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import model.Items;
import model.Reviews;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLfindReviewController implements Initializable {

    @FXML
    private ListView<Reviews> reviewList;
    @FXML
    private ComboBox<Items> itemBox;

    Alert alerta;
    private FXMLPrincipalController fxmlPrincipalController = new FXMLPrincipalController();

    public void setPrincipal(FXMLPrincipalController principalController){
        fxmlPrincipalController = principalController;
    }

    public void loadReviewList() {
        List<Reviews> listReview = fxmlPrincipalController.getReviewService().getAllReviews();
        reviewList.getItems().clear();
        reviewList.getItems().addAll(listReview);
    }

    public void loadItems() {
        List<Items> listItem = fxmlPrincipalController.getItemService().getAll();
        itemBox.getItems().clear();
        itemBox.getItems().addAll(listItem);
    }

    public void searchByItem() {
        try {
            Items itemSelected = itemBox.getSelectionModel().getSelectedItem();
            List<Reviews> itemsReview = fxmlPrincipalController.getReviewService().searchByItem(itemSelected);
            itemBox.getSelectionModel().clearSelection();
            if (!itemsReview.isEmpty()){
                reviewList.getItems().clear();
                reviewList.getItems().addAll(itemsReview);
            }else{
                alert("INFO", "No existe reviews para ese item. Te mostramos la lista entera de nuevo", Alert.AlertType.INFORMATION);
                reviewList.getItems().clear();
                reviewList.getItems().addAll(fxmlPrincipalController.getReviewService().getAllReviews());
            }
        }catch (Exception e){
            alert("ERROR", "No existe reviews para ese item", Alert.AlertType.INFORMATION);
            e.printStackTrace();
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

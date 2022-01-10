/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.purchases;

import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import model.Purchases;
import model.Reviews;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FXMLDeletePurchasesController implements Initializable {

    @FXML
    private ListView<Purchases> purchaseBox;

    Alert alerta;
    private final Alert confir = new Alert(AlertType.CONFIRMATION);

    private FXMLPrincipalController principalController = new FXMLPrincipalController();

    public void deletePurchase(){
        try {
            Purchases selectedPurchase = purchaseBox.getSelectionModel().getSelectedItem();
            List<Reviews> reviewList = principalController.getReviewService().getAllReviews();
            List<Reviews> reviewWithPurchase = reviewList.stream().filter(review -> review.getPurchasesByIdPurchase().getIdPurchase() == selectedPurchase.getIdPurchase()).collect(Collectors.toList());
            if(!reviewWithPurchase.isEmpty()){
                confir.setTitle("¡CUIDADO!");
                confir.setContentText("Si borras el Purchase se van a borrar las reviews. \n" +
                        "¿Seguro que quieres continuar?");
                Optional<ButtonType> action = confir.showAndWait();
                if (action.get() == ButtonType.OK) {
                    principalController.getPurchaseService().deletePurchase(selectedPurchase);
                    purchaseBox.getItems().remove(selectedPurchase);
                    alert("BIEN", "Purchase y sus reviews asociadas borradas", AlertType.CONFIRMATION);
                    }
            }else {
                try {
                    principalController.getPurchaseService().deletePurchase(selectedPurchase);
                    purchaseBox.getItems().remove(selectedPurchase);
                    alert("BIEN", "Purchase eliminado", AlertType.CONFIRMATION);
                } catch (Exception e) {
                    e.printStackTrace();
                    alert("ERROR", "Purchases con reviews NO delete. Try again", AlertType.ERROR);
                }
            }
        }catch (Exception e){
            alert("ERROR", "Ninguna compra seleccionada", AlertType.ERROR);
        }

    }

    private void alert(String titulo, String texto, AlertType tipo){
        alerta.setTitle(titulo);
        alerta.setContentText(texto);
        alerta.setAlertType(tipo);
        alerta.showAndWait();
    }

    public void setPrincipal(FXMLPrincipalController fxmlPrincipalController){
        this.principalController = fxmlPrincipalController;
    }

    public void loadPurchases() {
        purchaseBox.getItems().clear();
        purchaseBox.getItems().addAll(principalController.getPurchaseService().getAllPurchases());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alerta = new Alert(AlertType.CONFIRMATION);
    }

}

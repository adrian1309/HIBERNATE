package fx.controllers.items;


import fx.controllers.FXMLPrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import model.Items;
import model.Purchases;
import model.Reviews;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FXMLDeleteItemsController implements Initializable {


    @FXML
    private ListView<Items> lvItem;

    private Alert alerta;
    private final Alert confir = new Alert(Alert.AlertType.CONFIRMATION);

    private FXMLPrincipalController fxmlPrincipalController =  new FXMLPrincipalController();

    public void setPrincipal(FXMLPrincipalController principalController){
        fxmlPrincipalController = principalController;
    }

    public void deleteItem(ActionEvent actionEvent) {
        Items itemSelected = lvItem.getSelectionModel().getSelectedItem();
        List<Purchases> purchaseList = fxmlPrincipalController.getPurchaseService().getAllPurchases();
        List<Purchases> purchasesWithItemSelected = purchaseList.stream().filter(p -> p.getItemsByIdItem().getIdItem() == itemSelected.getIdItem()).collect(Collectors.toList());
        List<Reviews> reviewList = fxmlPrincipalController.getReviewService().getAllReviews();
        List<Reviews> reviewWithIdItemSelected = reviewList.stream().filter(r -> r.getItemsByIdItem().getIdItem() == itemSelected.getIdItem()).collect(Collectors.toList());
        if (!purchasesWithItemSelected.isEmpty()) {
            confir.setTitle("¡CUIDADO!");
            confir.setContentText("Si borras el Item se van a borrar las purchases y las reviews. \n" +
                    "¿Seguro que quieres continuar?");
            Optional<ButtonType> action = confir.showAndWait();
            if (action.get() == ButtonType.OK) {
                fxmlPrincipalController.getItemService().deleteItem(itemSelected);
                lvItem.getItems().remove(itemSelected);
                alert("BIEN", "Item borrado", Alert.AlertType.CONFIRMATION);
            }
        } else {
            fxmlPrincipalController.getItemService().deleteItem(itemSelected);
            lvItem.getItems().remove(itemSelected);
            alert("BIEN", "Item borrado", Alert.AlertType.CONFIRMATION);
            }
        }

    private void alert(String titulo, String texto, Alert.AlertType tipo){
        alerta.setTitle(titulo);
        alerta.setContentText(texto);
        alerta.setAlertType(tipo);
        alerta.showAndWait();
    }


    public void loadItemsList() {
        lvItem.getItems().clear();
        lvItem.getItems().addAll(fxmlPrincipalController.getItemService().getAll());
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alerta = new Alert(Alert.AlertType.CONFIRMATION);
    }
}

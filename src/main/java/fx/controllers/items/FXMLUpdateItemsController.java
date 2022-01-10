package fx.controllers.items;

import fx.controllers.FXMLPrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Items;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLUpdateItemsController implements Initializable {
    public TextField tfName;
    public TextField tfCompany;
    public TextField tfPrice;
    public ListView<Items> lvItems;

    Alert alerta;
    private FXMLPrincipalController fxmlPrincipalController = new FXMLPrincipalController();

    public void setPrincipal(FXMLPrincipalController principalController){
        fxmlPrincipalController = principalController;
    }


    public void btnUpdate(ActionEvent actionEvent) {
        Items itemSelected = lvItems.getSelectionModel().getSelectedItem();
        try {
            Items itemUpdate = Items.builder()
                    .name(tfName.getText())
                    .company(tfCompany.getText())
                    .price(Double.parseDouble(tfPrice.getText()))
                    .idItem(itemSelected.getIdItem())
                    .build();
            fxmlPrincipalController.getItemService().update(itemUpdate);
            loadItemList();
            alert("CONFIRMACIÓN", "Item actualizado correctamente", Alert.AlertType.CONFIRMATION);
        }catch (Exception e ){
            alert("ERROR", "Introduce correctamente los valores", Alert.AlertType.ERROR);
        }
    }


    public void itemSelected(MouseEvent mouseEvent) {
        try {
            Items itemSelected = lvItems.getSelectionModel().getSelectedItem();
            tfName.setText(itemSelected.getName());
            tfCompany.setText(itemSelected.getCompany());
            tfPrice.setText(String.valueOf(itemSelected.getPrice()));
        }catch (Exception e){
            alert("ERROR", "No se han podido realizar los cambios. Intantalo de nuevo.", Alert.AlertType.ERROR);
        }
    }

    private void alert(String titulo, String texto, Alert.AlertType tipo){
        alerta.setTitle(titulo);
        alerta.setContentText(texto);
        alerta.setAlertType(tipo);
        alerta.showAndWait();
    }

    public void loadItemList(){
        lvItems.getItems().clear();
        lvItems.getItems().addAll(fxmlPrincipalController.getItemService().getAll());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alerta = new Alert(Alert.AlertType.CONFIRMATION);
    }

}

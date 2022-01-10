package fx.controllers.items;

import fx.controllers.FXMLPrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Items;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLAddItemController implements Initializable {

    @FXML
    private ListView<Items> itemsList;
    @FXML
    private TextField txtIdItem;
    @FXML
    private TextField txtNameItem;
    @FXML
    private TextField txtCompanyItem;
    @FXML
    private TextField txtPriceItem;

    Alert alerta; //porque hay que inicializar el alert


    private FXMLPrincipalController principalController = new FXMLPrincipalController();



    public void addItems(ActionEvent actionEvent) {
        try {
            Items item = Items.builder()
                    .name(txtNameItem.getText())
                    .idItem(-1)
                    .company(txtCompanyItem.getText())
                    .price(Double.parseDouble(txtPriceItem.getText()))
                    .build();
            principalController.getItemService().addItem(item);
            alert("Enhorabuena!", "Item añadido", Alert.AlertType.CONFIRMATION);
            txtNameItem.clear();
            txtCompanyItem.clear();
            txtPriceItem.clear();

        } catch (NumberFormatException e) {
            alert("Invalido", "La id debe ser numerica", Alert.AlertType.WARNING);
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
            alert("ERROR", "Hay un error. Comprueba los datos", Alert.AlertType.ERROR);
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
      alerta = new Alert(Alert.AlertType.CONFIRMATION);
    }
}

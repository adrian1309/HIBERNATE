package fx.controllers.items;

import fx.controllers.FXMLPrincipalController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Items;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FXMLFindItemByIdController implements Initializable {

    public TextField tfFindItemById;
    public ListView<Items> lvItemFound;
    public Button btnSearch;

    Alert alerta;
    FXMLPrincipalController fxmlPrincipalController = new FXMLPrincipalController();


    public void setPrincipal(FXMLPrincipalController principalController){
        fxmlPrincipalController = principalController;
    }

    public void btnSearch(ActionEvent actionEvent) {
        int id = Integer.parseInt(tfFindItemById.getText());

        List<Items> items = fxmlPrincipalController.getItemService().getAll();
        List<Items> itemsFiltradosId = items.stream().filter(i -> i.getIdItem() == id).collect(Collectors.toList());

        try {
            lvItemFound.getItems().clear();
            tfFindItemById.clear();
            for (Items item: itemsFiltradosId) {
                Items itemEncontrado = fxmlPrincipalController.getItemService().getById(item.getIdItem());
                if (itemEncontrado != null) {
                    lvItemFound.getItems().add(itemEncontrado);
                }else{
                    alert("INFO", "No existe", Alert.AlertType.INFORMATION);
                }
            }
        }catch (NumberFormatException e){
            alert("WARNING", "EL ID debe ser numerico", Alert.AlertType.WARNING);
        }catch(Exception e){
            alert("ERROR", "Error en la busqueda", Alert.AlertType.ERROR);
        }



        //esta opcion es para buscar el item con stream, es decir, sin usar el dao
        /*List<Item> listItem = fxmlPrincipalController.getItemService().getAllItems();
        List<Item> itemEncontrado = listItem.stream()
                .filter(item -> item.getIdItem() == id).collect(Collectors.toList());
       if (!itemEncontrado.isEmpty()) {
            lvItemFound.getItems().addAll(itemEncontrado);
        }else {
            alert("ERROR", "No existe un Item con ese Id", Alert.AlertType.ERROR);
        }*/
    }

    private void alert(String titulo, String texto, Alert.AlertType tipo){
        alerta.setTitle(titulo);
        alerta.setContentText(texto);
        alerta.setAlertType(tipo);
        alerta.showAndWait();
    }

    public void refreshPage(){
        lvItemFound.getItems().clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alerta = new Alert(Alert.AlertType.CONFIRMATION);
    }


}

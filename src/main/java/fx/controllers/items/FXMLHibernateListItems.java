package fx.controllers.items;

import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import model.ItemsData;
import model.Items;
import model.Reviews;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

@Getter
public class FXMLHibernateListItems implements Initializable {

    private AnchorPane pantallaEmergente;
    private FXMLPantallaEmergenteItemsController pantallaEmergenteController;

    @FXML
    private RadioButton rbDate;
    @FXML
    private RadioButton rbRating;
    @FXML
    private RadioButton rbAsc;
    @FXML
    private RadioButton rbDesc;
    @FXML
    private TableView<Object> selectedItemTable;
    @FXML
    private TableColumn<ItemsData, Double> priceColumnTable;
    @FXML
    private TableColumn<ItemsData, Integer> purchasesLastMonthColumnTable;
    @FXML
    private TableColumn<ItemsData, Double> avgRatingColumnTable;
    @FXML
    private ListView<Items> lvItems;
    @FXML
    private ListView<Reviews> lvReviewList;

    private FXMLPrincipalController fxmlPrincipalController = new FXMLPrincipalController();


    public void setPrincipal(FXMLPrincipalController principalController){
        fxmlPrincipalController = principalController;
    }

    public void loadItems(){
        List<Items> listItems = fxmlPrincipalController.getItemService().getAll();
        lvItems.getItems().addAll(listItems);
    }

    public void loadClickItem(MouseEvent mouseEvent) {
        try {
            selectedItemTable.getItems().clear();
            Items itemSelected = lvItems.getSelectionModel().getSelectedItem();

            Long purchasesLastMonth = fxmlPrincipalController.getItemService().getPurchasesLastMonth(itemSelected);

            Double avgRating = fxmlPrincipalController.getItemService().getAvgRating(itemSelected);
            System.out.println(avgRating);
            Double nulo = -1.0;
            if (!avgRating.equals(nulo)){
                ItemsData patata = new ItemsData();
                patata.setPrice(itemSelected.getPrice());
                patata.setNumPurchasesLastMonth(purchasesLastMonth);
                patata.setAvgRating(avgRating);
                selectedItemTable.getItems().add(patata);
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setTitle("NULL");
                alert.setContentText("There aren't review of this item");
                alert.showAndWait();
            }

            radioButtons(itemSelected);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void radioButtons(Items itemSelected){
        List<Reviews> reviewsList;
        if (rbDate.isSelected() && rbAsc.isSelected()){
            reviewsList = fxmlPrincipalController.getItemService().getOrderByDateASC(itemSelected);
            lvReviewList.getItems().clear();
            for(Reviews r : reviewsList){
                lvReviewList.getItems().add(r);
            }
        }else if(rbDate.isSelected() && rbDesc.isSelected()){
            reviewsList = fxmlPrincipalController.getItemService().getOrderByDateDESC(itemSelected);
            lvReviewList.getItems().clear();
            for(Reviews r : reviewsList){
                lvReviewList.getItems().add(r);
            }
        }else if(rbRating.isSelected() && rbAsc.isSelected()){
            reviewsList = fxmlPrincipalController.getItemService().getOrderByRatingASC(itemSelected);
            lvReviewList.getItems().clear();
            for(Reviews r : reviewsList){
                lvReviewList.getItems().add(r);
            }
        }else if(rbRating.isSelected() && rbDesc.isSelected()){
            reviewsList = fxmlPrincipalController.getItemService().getOrderByRatingDESC(itemSelected);
            lvReviewList.getItems().clear();
            for(Reviews r : reviewsList){
                lvReviewList.getItems().add(r);
            }
        }else{
            lvReviewList.getItems().clear();
            Collection<Reviews> reviewsCollection =  itemSelected.getReviewsByIdItem();
            for(Reviews r : reviewsCollection){
                lvReviewList.getItems().add(r);
            }
        }
    }

    public void loadPantallaEmergente(MouseEvent mouseEvent) {
        try {
            Reviews r = lvReviewList.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/items/FXMLPantallaEmergente.fxml"));

            Parent root = loader.load();

            FXMLPantallaEmergenteItemsController controller = loader.getController();
            controller.loadReviewData(r);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();


        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void clearLists(){
        lvItems.getItems().clear();
        selectedItemTable.getItems().clear();
        lvReviewList.getItems().clear();

    }

    public void clearRadioButtons(){
        rbDate.setSelected(false);
        rbRating.setSelected(false);
        rbAsc.setSelected(false);
        rbDesc.setSelected(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priceColumnTable.setCellValueFactory(new PropertyValueFactory<ItemsData, Double>("price"));
        purchasesLastMonthColumnTable.setCellValueFactory(new PropertyValueFactory<ItemsData, Integer>("numPurchasesLastMonth"));
        avgRatingColumnTable.setCellValueFactory(new PropertyValueFactory<ItemsData, Double>("avgRating"));


    }


}

package fx.controllers.reviews;

import fx.controllers.FXMLPrincipalController;
import fx.controllers.items.FXMLPantallaEmergenteItemsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Reviews;
import model.ReviewsData2;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLHibernateListReviewsController implements Initializable {

    @FXML
    public ListView<ReviewsData2> lvReviews;
    @FXML
    private ComboBox<String> cbOrderBy;


    private FXMLPrincipalController principalController = new FXMLPrincipalController();

    public void setPrincipal(FXMLPrincipalController fxmlPrincipalController) {
        principalController = fxmlPrincipalController;
    }

    public void loadReviews() {
        List<Reviews> listReviews = principalController.getReviewService().getAllReviews();
        createReviewsData2(listReviews);
    }

    public void loadComboBox () {
            cbOrderBy.getItems().addAll("Item", "Customer", "Rating");
        }

    public void clearLists () {
            lvReviews.getItems().clear();
        }


    public void btnOrderClicked(ActionEvent actionEvent) {
        String selectedInComboBox = cbOrderBy.getSelectionModel().getSelectedItem();
        switch (selectedInComboBox) {
            case "Item":
                lvReviews.getItems().clear();
                List<Reviews> reviewsListOrderByItem = principalController.getReviewService().getReviewsOrderByItem();
                createReviewsData2(reviewsListOrderByItem);
                break;
            case "Customer":
                lvReviews.getItems().clear();
                List<Reviews> reviewsListOrderByCustomer = principalController.getReviewService().getReviewsOrderByCustomer();
                createReviewsData2(reviewsListOrderByCustomer);
                break;
            case "Rating":
                lvReviews.getItems().clear();
                List<Reviews> reviewsListOrderByRating = principalController.getReviewService().getReviewsOrderByRating();
                createReviewsData2(reviewsListOrderByRating);
                break;
            default:
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("INFORMATION");
                alert.setContentText("You must selected one item in the combobox to order the list");
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.showAndWait();
        }

    }



    private void createReviewsData2(List<Reviews> reviewsOrderBy) {
        for(Reviews r : reviewsOrderBy){
            ReviewsData2 reviewsData2 = new ReviewsData2();
            //añadimos todos los datos a ReviewsData2 para posteriormente
            // darselos a la pantallaEmergente, que tiene ReviewsData
            reviewsData2.setIdReview(r.getIdReview());
            reviewsData2.setTitle(r.getTitle());
            reviewsData2.setDescription(r.getDescription());
            reviewsData2.setRating(r.getRating());
            reviewsData2.setPurchase(r.getPurchasesByIdPurchase().getIdPurchase());
            //estos son los que se van a mostrar en pantalla
            reviewsData2.setItem(r.getItemsByIdItem().getName());
            reviewsData2.setCustomer(r.getCustomersByIdCustomer().getName());
            reviewsData2.setDate(r.getDate());
            lvReviews.getItems().add(reviewsData2);
        }
    }

    public void loadPantallaEmergente(MouseEvent mouseEvent) {
        try {
            ReviewsData2 r = lvReviews.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/reviews/FXMLPantallaEmergente.fxml"));

            Parent root = loader.load();

            FXMLPantallaEmergenteReviewsController controller = loader.getController();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

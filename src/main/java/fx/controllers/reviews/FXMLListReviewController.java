package fx.controllers.reviews;


import dao.DAOCustomersHibernate;
import dao.DAOReviewsHibernate;
import fx.controllers.FXMLPrincipalController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import model.Reviews;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLListReviewController implements Initializable {

    @FXML
    private TableView<ReviewsData3> reviewsTable;
    @FXML
    private TableColumn<ReviewsData3, Integer> idReviewColumnTable;
    @FXML
    private TableColumn<ReviewsData3, Integer> ratingColumnTable;
    @FXML
    private TableColumn<ReviewsData3, String> titleColumnTable;
    @FXML
    private TableColumn<ReviewsData3, String> descriptionColumnTable;
    @FXML
    private TableColumn<ReviewsData3, LocalDate> dateColumnTable;
    @FXML
    private TableColumn<ReviewsData3, Integer> idItemColumnTable;
    @FXML
    private TableColumn<ReviewsData3, Integer> idCustomerColumnTable;
    @FXML
    private TableColumn<ReviewsData3, Integer> idPurchaseColumnTable;

    private FXMLPrincipalController fxmlPrincipalController = new FXMLPrincipalController();

    public void setPrincipal(FXMLPrincipalController principalController){
        fxmlPrincipalController = principalController;
    }

    public void loadReviewList(){
        reviewsTable.getItems().clear();
        if (fxmlPrincipalController.getEsAdmin()) {
            List<Reviews> reviewsList = fxmlPrincipalController.getReviewService().getAllReviews();
            createReviewsData3(reviewsList);
        }else{
            DAOCustomersHibernate daoCustomer = new DAOCustomersHibernate();
            DAOReviewsHibernate daoReview = new DAOReviewsHibernate();
            Customers customer = daoCustomer.getCustomerByUserId(fxmlPrincipalController.getUser());
            List<Reviews> reviewsList = daoReview.getReviewByCustomerId(customer);
            createReviewsData3(reviewsList);
        }
    }


    private void createReviewsData3(List<Reviews> reviewsList) {
        for(Reviews r : reviewsList){
            ReviewsData3 reviewData3 = new ReviewsData3();
            reviewData3.setIdReview(r.getIdReview());
            reviewData3.setRating(r.getRating());
            reviewData3.setTitle(r.getTitle());
            reviewData3.setDescription(r.getDescription());
            reviewData3.setDate(r.getDate());
            reviewData3.setIdItem(r.getItemsByIdItem().getIdItem());
            reviewData3.setIdCustomer(r.getCustomersByIdCustomer().getIdCustomer());
            reviewData3.setIdPurchase(r.getPurchasesByIdPurchase().getIdPurchase());
            reviewsTable.getItems().add(reviewData3);
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idReviewColumnTable.setCellValueFactory(new PropertyValueFactory<>("idReview"));
        ratingColumnTable.setCellValueFactory(new PropertyValueFactory<>("rating"));
        titleColumnTable.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumnTable.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateColumnTable.setCellValueFactory(new PropertyValueFactory<>("date"));
        idItemColumnTable.setCellValueFactory(new PropertyValueFactory<ReviewsData3, Integer>("idItem"));
        idCustomerColumnTable.setCellValueFactory(new PropertyValueFactory<>("idCustomer"));
        idPurchaseColumnTable.setCellValueFactory(new PropertyValueFactory<>("idPurchase"));
    }
}

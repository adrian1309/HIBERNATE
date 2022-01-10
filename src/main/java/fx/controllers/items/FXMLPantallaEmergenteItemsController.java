package fx.controllers.items;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.ReviewsData;
import model.Reviews;
import model.ReviewsData2;
import services.ReviewService;
import services.serviceHibernate.ReviewServiceHiber;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLPantallaEmergenteItemsController implements Initializable {

    @FXML
    private ListView<ReviewsData> lvReviewData;

    private ReviewService service = new ReviewServiceHiber();

    public void loadReviewData(Reviews review){
        ListView<ReviewsData> reviewDataList;
        reviewDataList = lvReviewData;
        ReviewsData reviewData = new ReviewsData();
        reviewData.setIdReview(review.getIdReview());
        reviewData.setTitle(review.getTitle());
        reviewData.setDescription(review.getDescription());
        reviewData.setDate(review.getDate());
        reviewDataList.getItems().add(reviewData);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

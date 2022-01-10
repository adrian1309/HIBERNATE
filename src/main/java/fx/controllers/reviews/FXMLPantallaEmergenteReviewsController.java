package fx.controllers.reviews;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Reviews;
import model.ReviewsData;
import model.ReviewsData2;
import services.ReviewService;
import services.serviceHibernate.ReviewServiceHiber;

public class FXMLPantallaEmergenteReviewsController {

    @FXML
    public ListView<ReviewsData> lvReviewData;

    private ReviewService service = new ReviewServiceHiber();

    public void loadReviewData(ReviewsData2 review){
        ListView<ReviewsData> reviewDataList;
        reviewDataList = lvReviewData;
        ReviewsData reviewData = new ReviewsData();
        reviewData.setIdReview(review.getIdReview());
        reviewData.setRating(review.getRating());
        reviewData.setTitle(review.getTitle());
        reviewData.setDescription(review.getDescription());
        reviewData.setDate(review.getDate());
        reviewDataList.getItems().add(reviewData);
    }
}

package services;

import model.Customers;
import model.Items;
import model.Reviews;

import java.util.List;

public interface ReviewService {

    List<Reviews> getAllReviews();

    void deleteReview(Reviews review);

    List<Reviews> searchByItem(Items item);

    void addReview(Reviews review);

    List<Reviews> getReviewByCustomerId(Customers customer);

    List<Reviews> getReviewsOrderByItem();

    List<Reviews> getReviewsOrderByCustomer();

    List<Reviews> getReviewsOrderByRating();
}

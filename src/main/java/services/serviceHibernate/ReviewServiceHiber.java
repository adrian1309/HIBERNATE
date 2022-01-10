package services.serviceHibernate;

import dao.DAOReviewsHibernate;
import model.Customers;
import model.Items;
import model.Reviews;
import services.ReviewService;

import java.util.List;

public class ReviewServiceHiber implements ReviewService {

    @Override
    public List<Reviews> getAllReviews() {
        DAOReviewsHibernate dao = new DAOReviewsHibernate();
        return dao.getAll();
    }

    @Override
    public void addReview(Reviews review) {
        DAOReviewsHibernate dao = new DAOReviewsHibernate();
        dao.add(review);
    }

    @Override
    public void deleteReview(Reviews review) {
        DAOReviewsHibernate dao = new DAOReviewsHibernate();
        dao.delete(review);
    }

    @Override
    public List<Reviews> getReviewByCustomerId(Customers customer){
        DAOReviewsHibernate dao = new DAOReviewsHibernate();
        return dao.getReviewByCustomerId(customer);
    }

    @Override
    public List<Reviews> getReviewsOrderByItem() {
        DAOReviewsHibernate dao = new DAOReviewsHibernate();
        return dao.getReviewsOrderByItem();
    }

    @Override
    public List<Reviews> getReviewsOrderByCustomer() {
        DAOReviewsHibernate dao = new DAOReviewsHibernate();
        return dao.getReviewsOrderByCustomer();
    }

    @Override
    public List<Reviews> getReviewsOrderByRating() {
        DAOReviewsHibernate dao = new DAOReviewsHibernate();
        return dao.getReviewsOrderByRating();
    }

    @Override
    public List<Reviews> searchByItem(Items item) {
        return null;
    }


}

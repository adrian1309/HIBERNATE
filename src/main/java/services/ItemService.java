package services;

import model.Items;
import model.Reviews;

import java.util.List;

public interface ItemService {
    void addItem(Items item);

    List<Items> getAll();

    void deleteItem(Items item);

    void update(Items itemSelected);

    Items getById(int idItem);

    int getPurchasesLastMonth(Items item);

    double getAvgRating(Items item);

    List<Reviews> getOrderByDateASC(Items item);

    List<Reviews> getOrderByDateDESC(Items item);

    List<Reviews> getOrderByRatingASC(Items item);

    List<Reviews> getOrderByRatingDESC(Items item);
}

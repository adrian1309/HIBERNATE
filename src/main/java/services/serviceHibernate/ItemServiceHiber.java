package services.serviceHibernate;

import dao.DAOItemsHibernate;
import model.Items;
import model.Reviews;
import services.ItemService;

import java.util.List;

public class ItemServiceHiber implements ItemService {

    @Override
    public void addItem(Items item) {
        DAOItemsHibernate dao = new DAOItemsHibernate();
        dao.add(item);
    }
    @Override
    public List<Items> getAll(){
        DAOItemsHibernate dao = new DAOItemsHibernate();
        return dao.getAll();
    }

    @Override
    public void deleteItem(Items item) {
        DAOItemsHibernate dao = new DAOItemsHibernate();
        dao.delete(item);
    }

    @Override
    public Items getById(int idItem){
        DAOItemsHibernate dao = new DAOItemsHibernate();
        return dao.getById(idItem);
    }

    @Override
    public void update (Items item){
        DAOItemsHibernate dao = new DAOItemsHibernate();
        dao.update(item);
    }

    @Override
    public int getPurchasesLastMonth(Items item) {
        DAOItemsHibernate dao = new DAOItemsHibernate();
        return dao.getPurchasesLastMonth(item);
    }

    @Override
    public double getAvgRating(Items item){
        DAOItemsHibernate dao = new DAOItemsHibernate();
        return dao.getAvgRating(item);
    }

    @Override
    public List<Reviews> getOrderByDateASC(Items item) {
        DAOItemsHibernate dao = new DAOItemsHibernate();
        return dao.getOrderByDateASC(item);
    }

    @Override
    public List<Reviews> getOrderByDateDESC(Items item) {
        DAOItemsHibernate dao = new DAOItemsHibernate();
        return dao.getOrderByDateDESC(item);
    }

    @Override
    public List<Reviews> getOrderByRatingASC(Items item) {
        DAOItemsHibernate dao = new DAOItemsHibernate();
        return dao.getOrderByRatingASC(item);
    }

    @Override
    public List<Reviews> getOrderByRatingDESC(Items item) {
        DAOItemsHibernate dao = new DAOItemsHibernate();
        return dao.getOrderByRatingDESC(item);
    }
}

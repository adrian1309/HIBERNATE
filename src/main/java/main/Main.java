package main;

import dao.DAOItemsHibernate;
import model.Items;

import java.util.List;

public class Main {

    public static void main(final String[] args) throws Exception {
        DAOItemsHibernate dao = new DAOItemsHibernate();

        // Get all items
        List<Items> list;
        list = dao.getAll();
        list.forEach((c) -> {
            System.out.println(c);
        });

        //Get item by id
        System.out.println("Item number 1: " + dao.getById(1));

        //Add new item
        /*Items item = new Items();
        item.constructor(9, "Computer", "Carglasss", 8);
        dao.add(item);
        System.out.println("Item added");

         */

        // Update item
        //item.setCompany("Lenovo");
        //dao.update(item);
        //System.out.println("Item updated");

        // Delete item
        //dao.delete(item);
    }
}
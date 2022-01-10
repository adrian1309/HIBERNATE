/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers;

import fx.controllers.customers.*;
import fx.controllers.items.*;
import fx.controllers.purchases.*;
import fx.controllers.reviews.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import lombok.Getter;
import model.Users;
import services.CustomerService;
import services.ItemService;
import services.PurchaseService;
import services.ReviewService;
import services.serviceHibernate.CustomerServiceHiber;
import services.serviceHibernate.ItemServiceHiber;
import services.serviceHibernate.PurchaseServiceHiber;
import services.serviceHibernate.ReviewServiceHiber;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
public class FXMLPrincipalController implements Initializable {

    @FXML
    private MenuItem hibernateListCustomerMenuBar;
    @FXML
    private MenuItem hibernateListPurchaseMenuBar;
    @FXML
    private MenuItem hibernateListReviewMenuBar;
    @FXML
    private MenuItem hibernateListItemMenuBar;
    @FXML
    private MenuItem addItemMenuBar;
    @FXML
    private MenuItem listItemMenuBar;
    @FXML
    private MenuItem findItemMenuBar;
    @FXML
    private MenuItem deleteItemMenuBar;
    @FXML
    private MenuItem updateItemMenuBar;
    @FXML
    private MenuItem addPurchaseMenuBar;
    @FXML
    private MenuItem findPurchaseMenuBar;
    @FXML
    private MenuItem deletePurchaseMenuBar;
    @FXML
    private MenuItem addReviewMenuBar;
    @FXML
    private MenuItem listReviewMenuBar;
    @FXML
    private MenuItem findReviewMenuBar;
    @FXML
    private MenuItem deleteReviewMenuBar;
    @FXML
    private Menu customerMenuBar;
    @FXML
    private BorderPane fxRoot;

    public void setFxRoot(BorderPane fxRoot) {
        this.fxRoot = fxRoot;
    }

    @FXML
    private MenuBar fxMenuTop;



    // Get y set of the user to use it wherever we need it
    private Users user;
    public Users getUser() {
        return user;
    }
    public void setUser(Users user) {
        this.user = user;
    }

    private String username;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    private boolean esAdmin;
    public boolean getEsAdmin(){ return esAdmin;}
    public void setEsAdmin(boolean esAdmin){
        this.esAdmin = esAdmin;
    }


    // services
    private final ItemService itemService = new ItemServiceHiber();
    private final CustomerService customerService = new CustomerServiceHiber();
    private final PurchaseService purchaseService = new PurchaseServiceHiber();
    private final ReviewService reviewService = new ReviewServiceHiber();

    // References to other panes to load them and access their controllers
    private AnchorPane login;
    private FXMLLoginController loginController;
    private AnchorPane welcome;
    private FXMLWelcomeController welcomeController;

    private AnchorPane addPurchases;
    private FXMLAddPurchasesController addPurchasesController;
    private AnchorPane deletePurchases;
    private FXMLDeletePurchasesController deletePurchasesController;
    private AnchorPane datePurchases;
    private FXMLDatePurchasesController datePurchasesController;

    private AnchorPane addCustomer;
    private FXMLAddCustomerController addCustomerController;
    private AnchorPane findByIdCustomer;
    private FXMLFindByIdCustomerController findByIdCustomerController;
    private AnchorPane listCustomers;
    private FXMLListCustomersController listCustomersController;
    private AnchorPane deleteCustomer;
    private FXMLDeleteCustomerController deleteCustomerController;


    private AnchorPane addReview;
    private FXMLAddReviewController addReviewController;
    private AnchorPane listReview;
    private FXMLListReviewController listReviewController;
    private AnchorPane findReview;
    private FXMLfindReviewController findReviewController;
    private AnchorPane deleteReview;
    private FXMLdeleteReviewController deleteReviewController;

    private AnchorPane listItems;

    private AnchorPane addItem;
    private FXMLAddItemController addItemController;
    private FXMLListItemsController listItemsController;
    private AnchorPane deleteItem;
    private FXMLDeleteItemsController deleteItemsController;
    private AnchorPane findItemById;
    private FXMLFindItemByIdController findItemByIdController;
    private AnchorPane updateItem;
    private FXMLUpdateItemsController updateItemsController;

    private AnchorPane hibernateListItem;
    private FXMLHibernateListItems hibernateListItemsController;//tambien es controller
    private AnchorPane hibernateListCustomer;
    private FXMLHibernateListCustomersController hibernateListCustomersController;
    private AnchorPane hibernateListPurchase;
    private FXMLHibernateListPurchasesController hibernateListPurchasesController;
    private AnchorPane hibernateListReview;
    private FXMLHibernateListReviewsController hibernateListReviewsController;



    public void preloadLogin() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/FXMLLogin.fxml"));
            login = loaderMenu.load();
            loginController
                    = loaderMenu.getController();

            loginController.setPrincipal(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void preloadWelcome() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/FXMLWelcome.fxml"));
            welcome = loaderMenu.load();
            welcomeController = loaderMenu.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadAddPurchases() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/purchases/FXMLAddPurchases.fxml"));
            addPurchases = loaderMenu.load();
            addPurchasesController = loaderMenu.getController();
            addPurchasesController.setPrincipal(this);

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void preloadDatePurchases() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/purchases/FXMLDatePurchases.fxml"));
            datePurchases = loaderMenu.load();
            datePurchasesController = loaderMenu.getController();
            datePurchasesController.setPrincipal(this);


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void preloadDeletePurchases() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/purchases/FXMLDeletePurchases.fxml"));
            deletePurchases = loaderMenu.load();
            deletePurchasesController = loaderMenu.getController();
            deletePurchasesController.setPrincipal(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadAddCustomer() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/customers/FXMLAddCustomer.fxml"));
            addCustomer = loaderMenu.load();
            addCustomerController = loaderMenu.getController();
            addCustomerController.setPrincipal(this);

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void preloadFindByIdCustomer() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/customers/FXMLFindByIdCustomer.fxml"));
            findByIdCustomer = loaderMenu.load();
            findByIdCustomerController = loaderMenu.getController();


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadListCustomers() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/customers/FXMLListCustomers.fxml"));
            listCustomers = loaderMenu.load();
            listCustomersController = loaderMenu.getController();
            listCustomersController.setPrincipal(this);

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void preloadDeleteCustomer() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/customers/FXMLDeleteCustomer.fxml"));
            deleteCustomer = loaderMenu.load();
            deleteCustomerController = loaderMenu.getController();
            deleteCustomerController.setPrincipal(this);

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadListReview() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/reviews/FXMLListReview.fxml"));
            listReview = loaderMenu.load();
            listReviewController = loaderMenu.getController();
            listReviewController.setPrincipal(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadAddReview() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/reviews/FXMLAddReview.fxml"));
            addReview = loaderMenu.load();
            addReviewController = loaderMenu.getController();
            addReviewController.setPrincipal(this);
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void preloadFindReview() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/reviews/FXMLfindReview.fxml"));
            findReview = loaderMenu.load();
            findReviewController = loaderMenu.getController();

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void preloadDeleteReview() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/reviews/FXMLdeleteReview.fxml"));
            deleteReview = loaderMenu.load();
            deleteReviewController = loaderMenu.getController();
            deleteReviewController.setPrincipal(this);


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadListItems() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/items/FXMLListItems.fxml"));
            listItems = loaderMenu.load();
            listItemsController = loaderMenu.getController();
            listItemsController.setPrincipal(this);

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadAddItem() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/items/FXMLAddItems.fxml"));
            addItem = loaderMenu.load();
            addItemController = loaderMenu.getController();
            addItemController.setPrincipal(this);

        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void preloadDeleteItem() {

        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/items/FXMLDeleteItems.fxml"));
            deleteItem = loaderMenu.load();
            deleteItemsController = loaderMenu.getController();
            deleteItemsController.setPrincipal(this);


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadFindItemById() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/items/FXMLFindItemById.fxml"));
            findItemById = loaderMenu.load();
            findItemByIdController = loaderMenu.getController();
            findItemByIdController.setPrincipal(this);


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadUpdateItem() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/items/FXMLUpdateItems.fxml"));
            updateItem = loaderMenu.load();
            updateItemsController = loaderMenu.getController();
            updateItemsController.setPrincipal(this);


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadHibernateListItem() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/items/FXMLHibernateListItems.fxml"));
            hibernateListItem = loaderMenu.load();
            hibernateListItemsController = loaderMenu.getController();
            hibernateListItemsController.setPrincipal(this);


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadHibernateListCustomer() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/customers/FXMLHibernateListCustomers.fxml"));
            hibernateListCustomer = loaderMenu.load();
            hibernateListCustomersController = loaderMenu.getController();
            hibernateListCustomersController.setPrincipal(this);


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadHibernateListPurchase() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/purchases/FXMLHibernateListPurchases.fxml"));
            hibernateListPurchase = loaderMenu.load();
            hibernateListPurchasesController = loaderMenu.getController();
            hibernateListPurchasesController.setPrincipal(this);


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void preloadHibernateListReview() {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(
                    getClass().getResource(
                            "/fxml/reviews/FXMLHibernateListReviews.fxml"));
            hibernateListReview = loaderMenu.load();
            hibernateListReviewsController = loaderMenu.getController();
            hibernateListReviewsController.setPrincipal(this);


        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void chargeLogin() {
        fxRoot.setCenter(login);
        fxMenuTop.setVisible(false);
    }
    public void chargeWelcome() {
        welcomeController.setLogin(this.getUsername());
        fxMenuTop.setVisible(true);
        fxRoot.setCenter(welcome);
    }
    
    public void chargeAddPurchases() {
        addPurchasesController.loadPurchasesList();
        addPurchasesController.loadCustomersList();
        addPurchasesController.loadItemsList();
        fxRoot.setCenter(addPurchases);
    }
    public void chargeDatePurchases() {
        datePurchasesController.loadPage();
        fxRoot.setCenter(datePurchases);
    }
    public void chargeDeletePurchases() {
        deletePurchasesController.loadPurchases();
        fxRoot.setCenter(deletePurchases);
    }

    public void chargeAddCustomer() {
        fxRoot.setCenter(addCustomer);
    }
    public void chargeFindByIdCustomer() {
        findByIdCustomerController.loadPage();
        fxRoot.setCenter(findByIdCustomer);
    }
    public void chargeListCustomers(){
        listCustomersController.loadCustomerList();
        fxRoot.setCenter(listCustomers);
    }
    public void chargeDeleteCustomer() {
        deleteCustomerController.loadCustomerList();
        fxRoot.setCenter(deleteCustomer);
    }

    public void chargeAddReview() {
        addReviewController.loadCustomers();
        addReviewController.loadRating();
        fxRoot.setCenter(addReview);
    }

    public void chargeListReview(ActionEvent actionEvent) {
        listReviewController.loadReviewList();
        fxRoot.setCenter(listReview);
    }

    public void chargeDeleteReview() {
        deleteReviewController.loadCustomersList();
        fxRoot.setCenter(deleteReview);
    }
    public void chargeFindReview() {
        findReviewController.loadReviewList();
        findReviewController.loadItems();
        fxRoot.setCenter(findReview);
    }

    public void chargeListItems(ActionEvent actionEvent) {
        listItemsController.loadItemsList();
        fxRoot.setCenter(listItems);
    }

    public void chargeAddItems(ActionEvent actionEvent) {
        fxRoot.setCenter(addItem);
    }

    public void chargeDeleteItems(ActionEvent actionEvent) {
        deleteItemsController.loadItemsList();
        fxRoot.setCenter(deleteItem);
    }

    public void chargeFindIdItems (ActionEvent actionEvent){
        findItemByIdController.refreshPage();
        fxRoot.setCenter(findItemById);
    }

    public void chargeUpdateItems (ActionEvent actionEvent){
        updateItemsController.loadItemList();
        fxRoot.setCenter(updateItem);
    }

    public void chargeHibernateItems(ActionEvent actionEvent) {
        hibernateListItemsController.clearLists();
        hibernateListItemsController.clearRadioButtons();
        hibernateListItemsController.loadItems();
        fxRoot.setCenter(hibernateListItem);
    }

    public void chargeHibernateCustomers(ActionEvent actionEvent) {
        hibernateListCustomersController.clearLists();
        hibernateListCustomersController.loadCustomers();
        fxRoot.setCenter(hibernateListCustomer);
    }

    public void chargeHibernatePurchases(ActionEvent actionEvent) {
        hibernateListPurchasesController.clearLists();
        hibernateListPurchasesController.loadPurchases();
        hibernateListPurchasesController.loadComboBox();
        fxRoot.setCenter(hibernateListPurchase);
    }

    public void chargeHibernateReviews(ActionEvent actionEvent) {
        hibernateListReviewsController.clearLists();
        hibernateListReviewsController.loadReviews();
        hibernateListReviewsController.loadComboBox();
        fxRoot.setCenter(hibernateListReview);
    }

    public void esconderMenu(){
        resetearMenu();
        if (!getEsAdmin()){
            addItemMenuBar.setVisible(false);
            deleteItemMenuBar.setVisible(false);
            updateItemMenuBar.setVisible(false);
            findItemMenuBar.setVisible(false);
            customerMenuBar.setVisible(false);
            deletePurchaseMenuBar.setVisible(false);
            findPurchaseMenuBar.setVisible(false);
            findReviewMenuBar.setVisible(false);
        }
    }


    public void resetearMenu(){
        fxMenuTop.getMenus().forEach(it -> {
            if (!it.getItems().isEmpty()){
                it.getItems().forEach(item -> item.setVisible(true));
            }
            it.setVisible(true);
        });
    }




    @Override
    public void initialize(URL url, ResourceBundle rb) {

        preloadWelcome();
        preloadLogin();

        preloadAddPurchases();
        preloadDatePurchases();
        preloadDeletePurchases();

        preloadAddCustomer();
        preloadFindByIdCustomer();
        preloadListCustomers();
        preloadDeleteCustomer();

        preloadAddReview();
        preloadListReview();
        preloadDeleteReview();
        preloadFindReview();


        preloadListItems();

        preloadAddItem();
        preloadDeleteItem();
        preloadFindItemById();
        preloadUpdateItem();

        preloadHibernateListItem();
        preloadHibernateListCustomer();
        preloadHibernateListPurchase();
        preloadHibernateListReview();

        chargeLogin();
        esconderMenu();

    }



}

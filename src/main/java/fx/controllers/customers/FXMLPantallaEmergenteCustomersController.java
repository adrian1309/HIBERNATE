package fx.controllers.customers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Purchases;
import model.PurchasesData;


public class FXMLPantallaEmergenteCustomersController {

    @FXML
    private ListView lvPurchasesData;

    public void loadPurchasesData(Purchases purchase) {
        ListView<PurchasesData> purchasesDataList;
        purchasesDataList = lvPurchasesData;
        PurchasesData purchasesData = new PurchasesData();
        purchasesData.setIdPurchase(purchase.getIdPurchase());
        purchasesData.setDate(purchase.getDate());
        purchasesData.setReviewsByIdPurchase(purchase.getReviewsByIdPurchase());
        purchasesDataList.getItems().add(purchasesData);
    }
}

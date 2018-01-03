package trapx00.lightx00.client.presentation.inventoryui.check;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import trapx00.lightx00.client.bl.inventorybl.PurchaseBillBlInfo;
import trapx00.lightx00.client.bl.inventorybl.factory.PurchaseBillBlInfoFactory;
import trapx00.lightx00.client.bl.salebl.SaleBillBlInfo;
import trapx00.lightx00.client.bl.salebl.factory.SaleBillBlInfoFactory;
import trapx00.lightx00.client.bl.util.FormatDateTime;
import trapx00.lightx00.client.blservice.inventoryblservice.InventoryCheckBlService;
import trapx00.lightx00.client.blservice.inventoryblservice.InventoryCheckBlServiceFactory;
import trapx00.lightx00.client.presentation.helpui.ExternalLoadableUiController;
import trapx00.lightx00.client.presentation.helpui.ExternalLoadedUiPackage;
import trapx00.lightx00.client.presentation.helpui.UiLoader;
import trapx00.lightx00.client.vo.inventorystaff.InventoryViewItem;
import trapx00.lightx00.client.vo.inventorystaff.InventoryViewVo;
import trapx00.lightx00.client.vo.salestaff.PurchaseBillVo;
import trapx00.lightx00.client.vo.salestaff.PurchaseRefundBillVo;
import trapx00.lightx00.client.vo.salestaff.SaleBillVo;
import trapx00.lightx00.client.vo.salestaff.SaleRefundBillVo;
import trapx00.lightx00.shared.queryvo.PurchaseBillQueryVo;
import trapx00.lightx00.shared.queryvo.PurchaseRefundBillQueryVo;
import trapx00.lightx00.shared.queryvo.SaleBillQueryVo;
import trapx00.lightx00.shared.queryvo.SaleRefundBillQueryVo;
import trapx00.lightx00.shared.util.DateHelper;

import java.util.Date;

public class InventoryCheckUiController implements ExternalLoadableUiController {
    @FXML private JFXDatePicker startDatePicker;
    @FXML private JFXDatePicker endDatePicker;
    @FXML private JFXButton btnFilter;
    @FXML private JFXTreeTableView<InventoryViewModel> tableView;
    @FXML private JFXTreeTableColumn<InventoryViewModel, Double> tcinventoryAmount;
    @FXML private JFXTreeTableColumn<InventoryViewModel, Double> tcinventoryMoney;
    @FXML private JFXTreeTableColumn<InventoryViewModel, Double> tcoutOfInventoryAmount;
    @FXML private JFXTreeTableColumn<InventoryViewModel, Double> tcoutOfInVentoryMoney;
    @FXML private JFXTreeTableColumn<InventoryViewModel, Double> tcsum;
    @FXML private JFXTreeTableColumn<InventoryViewModel, Double> tcinSold;
    @FXML private JFXTreeTableColumn<InventoryViewModel, Double> tcoutSold;

    private ObservableList<InventoryViewModel> viewModels = FXCollections.observableArrayList();
    private InventoryCheckBlService blService= InventoryCheckBlServiceFactory.getInstance();
    private SaleBillBlInfo saleBillBlInfo= SaleBillBlInfoFactory.getSaleBillBlInfo();
    private PurchaseBillBlInfo purchaseBillBlInfo= PurchaseBillBlInfoFactory.getPurchaseBillBlInfo();

    public void onBtnFilterClicked(ActionEvent actionEvent) {
        updateItems();
    }

    public void initialize() {
        initTable();
    }
    public void updateItems() {

        InventoryViewVo inventoryViewVo=null;
        Date beginTime=DateHelper.fromLocalDate(startDatePicker.getValue());
        Date endTime=DateHelper.fromLocalDate(endDatePicker.getValue());
            try {
                inventoryViewVo=blService.getInventoryView(
                        beginTime,
                        endTime);
                System.out.println(inventoryViewVo.getId());
                viewModels.add(new InventoryViewModel(new InventoryViewItem(new Date(),inventoryViewVo.getItems().getInventoryAmount(),
                        inventoryViewVo.getItems().getInventoryMoney(),inventoryViewVo.getItems().getOutOfInventoryAmount()
                        ,inventoryViewVo.getItems().getOutOfInVentoryMoney()
                        ,inventoryViewVo.getItems().getSum(),inventoryViewVo.getItems().getInSoldPrice(),inventoryViewVo.getItems().getOutSoldPrice())));
            } catch (Exception ignored) { }



    }

    @FXML
    private void initTable() {
        tcinventoryAmount.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getValue().getInventorViewItem().getInventoryAmount()));
        tcinventoryMoney.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getValue().getInventorViewItem().getInventoryMoney()));
        tcoutOfInventoryAmount.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getValue().getInventorViewItem().getOutOfInventoryAmount()));
        tcoutOfInVentoryMoney.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getValue().getInventorViewItem().getOutOfInVentoryMoney()));
        tcsum.setCellValueFactory(cellData->new SimpleObjectProperty<>(cellData.getValue().getValue().getInventorViewItem().getSum()));
        tcinSold.setCellValueFactory(cellData->new SimpleObjectProperty<>(cellData.getValue().getValue().getInventorViewItem().getInSoldPrice()));
        tcoutSold.setCellValueFactory(cellData->new SimpleObjectProperty<>(cellData.getValue().getValue().getInventorViewItem().getOutOfInVentoryMoney()));

        TreeItem<InventoryViewModel> root = new RecursiveTreeItem<>(viewModels, RecursiveTreeObject::getChildren);
        tableView.setRoot(root);
        tableView.setShowRoot(false);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @Override
    public ExternalLoadedUiPackage load() {
        return new UiLoader("/fxml/inventoryui/check/InventoryCheckUi.fxml").loadAndGetPackageWithoutException();
    }
}

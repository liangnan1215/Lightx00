package trapx00.lightx00.client.presentation.saleui;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import trapx00.lightx00.client.bl.adminbl.EmployeeInfo;
import trapx00.lightx00.client.bl.adminbl.factory.EmployeeInfoFactory;
import trapx00.lightx00.client.blservice.clientblservice.ClientBlService;
import trapx00.lightx00.client.blservice.clientblservice.ClientBlServiceFactory;
import trapx00.lightx00.client.presentation.helpui.BillDetailUi;
import trapx00.lightx00.client.presentation.helpui.ExternalLoadedUiPackage;
import trapx00.lightx00.client.presentation.helpui.FrameworkUiManager;
import trapx00.lightx00.client.presentation.helpui.UiLoader;
import trapx00.lightx00.client.presentation.inventoryui.CommodityItemModel;
import trapx00.lightx00.client.vo.BillVo;
import trapx00.lightx00.client.vo.EmployeeVo;
import trapx00.lightx00.client.vo.salestaff.SaleBillVo;
import trapx00.lightx00.shared.po.salestaff.CommodityItem;

public class SaleRefundBillDetailUiController extends BillDetailUi {
    @FXML
    private JFXTextField tfBillId;
    @FXML
    private JFXTextField tfSalesmanId;
    @FXML
    private JFXTextField tfSalesmanName;
    @FXML
    private JFXTextField tfOperator;
    @FXML
    private JFXTextField tfDate;
    @FXML
    private JFXTextField tfClientId;
    @FXML
    private JFXTextField tfClientName;
    @FXML
    private JFXTextField tfClientLevel;
    @FXML
    private JFXComboBox<String> cbRepository;
    @FXML
    private JFXTextField tfOriginTotal;
    @FXML
    private JFXTextField tfMinusProfits;
    @FXML
    private JFXTextField tfToken;
    @FXML
    private JFXTextField tfUltiTotal;
    @FXML
    private JFXTextField tfPromotionId;
    @FXML
    private JFXTextField tfGiftToken;
    @FXML
    private JFXTextField tfComment;
    @FXML
    private JFXTreeTableView<CommodityItemModel> tbCommodityList;
    @FXML
    private JFXTreeTableColumn<CommodityItemModel, String> tcCommodityIdColumn;
    @FXML
    private JFXTreeTableColumn<CommodityItemModel, String> tcCommodityNameColumn;
    @FXML
    private JFXTreeTableColumn<CommodityItemModel, String> tcCommodityTypeColumn;
    @FXML
    private JFXTreeTableColumn<CommodityItemModel, String> tcCommodityNumberColumn;
    @FXML
    private JFXTreeTableColumn<CommodityItemModel, String> tcCommodityPriceColumn;
    @FXML
    private JFXTreeTableColumn<CommodityItemModel, String> tcCommodityTotalColumn;
    @FXML
    private JFXTreeTableColumn<CommodityItemModel, String> tcCommodityCommentColumn;

    private ObjectProperty<EmployeeVo> currentEmployee = new SimpleObjectProperty<>();
    private EmployeeInfo employeeInfo = EmployeeInfoFactory.getEmployeeInfo();
    private ObservableList<CommodityItemModel> commodityItemModelObservableList = FXCollections.observableArrayList();
    private ClientBlService clientBlService= ClientBlServiceFactory.getInstance();

    @Override
    public ExternalLoadedUiPackage showContent(BillVo arg) {
        currentEmployee.setValue(FrameworkUiManager.getCurrentEmployee());
        SaleBillVo saleBillVo=(SaleBillVo) arg;
        ExternalLoadedUiPackage externalLoadedUiPackage = load();
        SaleBillDetailUiController saleBillDetailUiController = externalLoadedUiPackage.getController();
        saleBillDetailUiController.tfBillId.setText(saleBillVo.getId());
        saleBillDetailUiController.tfDate.setText(saleBillVo.getDate().toString());
        saleBillDetailUiController.tfSalesmanId.setText(saleBillVo.getSalesmanId());
        saleBillDetailUiController.tfSalesmanName.setText(employeeInfo.queryById(saleBillVo.getSalesmanId()).getName());
        saleBillDetailUiController.tfOperator.setText(String.format("%s(id: %s)", currentEmployee.getValue().getName(), currentEmployee.getValue().getId()));
        saleBillDetailUiController.tfClientId.setText(saleBillVo.getClientId());
        saleBillDetailUiController.tfClientName.setText(clientBlService.queryById(saleBillVo.getClientId()).getName());
        saleBillDetailUiController.tfClientLevel.setText(clientBlService.queryById(saleBillVo.getClientId()).getClientLevel()+"");
        saleBillDetailUiController.cbRepository.setValue(saleBillVo.getRepository() + "");
        saleBillDetailUiController.tfOriginTotal.setText(saleBillVo.getOriginTotal() + "");
        saleBillDetailUiController.tfMinusProfits.setText(saleBillVo.getMinusProfits() + "");
        saleBillDetailUiController.tfToken.setText(saleBillVo.getToken() + "");
        saleBillDetailUiController.tfPromotionId.setText(saleBillVo.getPromotionId());
        saleBillDetailUiController.tfGiftToken.setText(saleBillVo.getGiftToken()+"");
        saleBillDetailUiController.tfComment.setText(saleBillVo.getComment());
        saleBillDetailUiController.addCommodityListItems(saleBillVo.getCommodityList());
        return externalLoadedUiPackage;
    }

    public void addCommodityListItems(CommodityItem[] commodityItems) {
        for (CommodityItem commodityItem : commodityItems) {
            commodityItemModelObservableList.add(new CommodityItemModel(commodityItem));
        }
    }

    public void initialize() {
        tcCommodityIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getCommodityItemObjectProperty().getCommodityId()));
        tcCommodityNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getCommodityItemObjectProperty().getName()));
        tcCommodityTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getCommodityItemObjectProperty().getType()));
        tcCommodityNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getCommodityItemObjectProperty().getNumber() + ""));
        tcCommodityPriceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getCommodityItemObjectProperty().getPrice() + ""));
        tcCommodityTotalColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getCommodityItemObjectProperty().getTotal() + ""));
        tcCommodityCommentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getCommodityItemObjectProperty().getComment()));
        TreeItem<CommodityItemModel> root = new RecursiveTreeItem<>(commodityItemModelObservableList, RecursiveTreeObject::getChildren);
        tbCommodityList.setRoot(root);
        tbCommodityList.setShowRoot(false);
    }

    /**
     * Loads the controller.
     *
     * @return external loaded ui controller and component
     */
    @Override
    public ExternalLoadedUiPackage load() {
        return new UiLoader("/fxml/saleui/SaleRefundBillDetailUi.fxml").loadAndGetPackageWithoutException();
    }
}

package trapx00.lightx00.client.presentation.approvalui;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TreeItem;
import trapx00.lightx00.client.Client;
import trapx00.lightx00.client.presentation.financeui.BillTableItemModel;
import trapx00.lightx00.client.presentation.financeui.TradeHistoryUiController;
import trapx00.lightx00.client.presentation.helpui.*;
import trapx00.lightx00.client.presentation.mainui.FrameworkUiController;
import trapx00.lightx00.shared.po.bill.BillState;
import trapx00.lightx00.shared.po.bill.BillType;
import trapx00.lightx00.shared.util.DateHelper;

import java.io.IOException;
import java.util.Date;

public class AuditUiController implements ExternalLoadableUiController {
    public JFXComboBox sortChoiceBox;
    public JFXButton updateButton;
    public JFXButton approveButton;
    public JFXButton detailButton;
    public JFXTreeTableView<BillTableItemModel> billTable;
    public JFXTreeTableColumn<BillTableItemModel, String> tableTypeColumn;
    public JFXTreeTableColumn<BillTableItemModel, String> tableIdColumn;
    public JFXTreeTableColumn<BillTableItemModel, String> tableDateColumn;
    public JFXTreeTableColumn<BillTableItemModel, String> tableStateColumn;

    private ObservableList<BillTableItemModel> billTableItemModels = FXCollections.observableArrayList();
    private FrameworkUiController frameworkController;

    public void initialize() {
        initDraftItem();
        updateItems();
    }

    public void initDraftItem() {
        tableDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(DateHelper.fromDate(cellData.getValue().getValue().getDate())));
        tableTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getType().toString()));
        tableIdColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().idProperty());
        tableStateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getState().toString()));
        TreeItem<BillTableItemModel> root = new RecursiveTreeItem<>(billTableItemModels, RecursiveTreeObject::getChildren);
        billTable.setRoot(root);
        billTable.setShowRoot(false);
        billTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                TreeItem<BillTableItemModel> selectedItem = billTable.getSelectionModel().getSelectedItem();
                showDetail(selectedItem.getValue());
            }
        });
    }

    public void showDetail(BillTableItemModel model) {
        PromptDialogHelper.start("单据详情",null)
                .addCloseButton("完成","DONE",null)
                .addTable(
                        ReadOnlyPairTableHelper.start()
                                .addPair("单据编号",model.getId())
                                .addPair("操作员",model.getOperator())
                                .addPair("银行账户","银行账户1")
                                .addPair("条目","条目1")
                                .addPair("总额","200.00")
                                .addPair("备注","备注")
                                .create())
                .create(frameworkController.dialogContainer)
                .show();
    }

    public void updateItems() {
        billTableItemModels.clear();
        billTableItemModels.add(new BillTableItemModel(new Date(), "XJFYD-20171026201620","hahaha", BillType.FinanceBill, BillState.Draft));
        billTableItemModels.add(new BillTableItemModel(new Date(), "JHD-20171026201620","hahaha",BillType.SaleBill, BillState.Approved));
    }


    public static AuditUiController init(FrameworkUiController frameworkUiController) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Client.class.getResource("/fxml/approvalui/AuditUi.fxml"));
            Parent content = loader.load();
            AuditUiController thisController = loader.getController();
            thisController.frameworkController = frameworkUiController;
            frameworkUiController.setContent(content);
            return thisController;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onRefreshButtonClicked(ActionEvent actionEvent) {
        updateItems();
    }


    public void onApproveClicked(ActionEvent actionEvent) {
        PromptDialogHelper.start("审批通过成功","已发送通知。")
                .addCloseButton("完成","DONE",null)
                .create(frameworkController.dialogContainer).show();
    }

    public void onDetailClicked(ActionEvent actionEvent) {
        try {
            showDetail(billTable.getSelectionModel().getSelectedItem().getValue());
        } catch (Exception ex) {
            PromptDialogHelper.start("错误","请至少选一个条目。")
                    .addCloseButton("好的","DONE",null)
                    .create(frameworkController.dialogContainer).show();
        }

    }
    /**
     * Loads the controller.
     *
     * @return external loaded ui controller and component
     */
    @Override
    public ExternalLoadedUiPackage load() {
        return new UiLoader("/fxml/managerui/.AuditUi.fxml").loadAndGetPackageWithoutException();
    }
}

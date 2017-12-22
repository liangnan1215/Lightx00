package trapx00.lightx00.client.presentation.logui;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TreeItem;
import trapx00.lightx00.client.blservice.logblservice.LogBlService;
import trapx00.lightx00.client.blservice.logblservice.LogBlServiceFactory;
import trapx00.lightx00.client.presentation.helpui.*;
import trapx00.lightx00.client.vo.log.LogVo;
import trapx00.lightx00.shared.queryvo.LogQueryVo;
import trapx00.lightx00.shared.util.DateHelper;

import java.util.ArrayList;

public class LogUiController implements ExternalLoadableUiController {

    public JFXTreeTableView<LogTableItemModel> logTable;
    public JFXTreeTableColumn<LogTableItemModel, String> logDateColumn;
    public JFXTreeTableColumn<LogTableItemModel, String> logSeverityColumn;
    public JFXTreeTableColumn<LogTableItemModel, String> logContentColumn;
    public Label lbResult;
    public JFXDatePicker startDatePicker;
    public JFXDatePicker endDatePicker;
    public JFXCheckBox cbIsEnabled;
    private ObservableList<LogTableItemModel> logTableItemModels = FXCollections.observableArrayList();
    private LogBlService logBlService = LogBlServiceFactory.getInstance();


    public LogVo getSelected() {
        return logTable.getSelectionModel().getSelectedItem().getValue().toLogVo();
    }

    public void initLogItem() {
        logDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(DateHelper.fromDate(cellData.getValue().getValue().getDate())));
        logSeverityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getValue().getSeverity().toString()));
        logContentColumn.setCellValueFactory(cellData -> cellData.getValue().getValue().contentProperty());
        logTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                try {
                    LogVo selected = getSelected();
                    ExternalLoadedUiPackage uiPackage = selected.contentDisplayUi().showContent(selected);
                    PromptDialogHelper.start("日志信息","").setContent(uiPackage.getComponent()).createAndShow();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
        }});
        TreeItem<LogTableItemModel> root = new RecursiveTreeItem<>(logTableItemModels, RecursiveTreeObject::getChildren);
        logTable.setRoot(root);
        logTable.setShowRoot(false);
    }

    public void updateItems() {
        LogQueryVo queryVo = new LogQueryVo();
        if (cbIsEnabled.isSelected()) {
            try {
                queryVo.between("date",
                    DateHelper.fromLocalDate(startDatePicker.getValue()),
                    DateHelper.fromLocalDate(endDatePicker.getValue()));
            } catch (Exception ignored) {

            }
        }

        logTableItemModels.clear();

        for(LogVo logVo : logBlService.query(queryVo)) {
            logTableItemModels.add(new LogTableItemModel(logVo));
        }
    }

    public void initialize() {
        initLogItem();
        updateItems();
    }


    /**
     * Loads the controller.
     *
     * @return external loaded ui controller and component
     */
    @Override
    public ExternalLoadedUiPackage load() {
        return new UiLoader("/fxml/logui/LogUi.fxml").loadAndGetPackageWithoutException();
    }

    public void onBtnSelectClicked(ActionEvent actionEvent) {
        /**
         * 这里是new的，应该提供工厂。工厂应该直接new一个回来。
         */
        new SampleSelectingDialog().showSelectLogDialog(new ArrayList<>(), list -> {
            /**
             * 这个函数就是如何处理选择结束后的数据。参数list就是用户选择的内容。
             * 如果用户按了取消，就不会调用
             */
            StringBuilder result = new StringBuilder("你选择了ID是");
            list.forEach(x -> result.append(x.getId()).append("、"));

            lbResult.setText(result.append("的日志").toString());
        });
    }

    public void onBtnConfirmedClicked(ActionEvent actionEvent) {
        updateItems();
    }
}

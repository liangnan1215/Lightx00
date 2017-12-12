package trapx00.lightx00.client.presentation.financeui.cashbill;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import trapx00.lightx00.client.blservice.financeblservice.CashBillBlService;
import trapx00.lightx00.client.blservice.financeblservice.CashBillBlServiceFactory;
import trapx00.lightx00.client.presentation.adminui.EmployeeSelection;
import trapx00.lightx00.client.presentation.adminui.factory.UserManagementUiFactory;
import trapx00.lightx00.client.presentation.bankaccountui.BankAccountSelection;
import trapx00.lightx00.client.presentation.bankaccountui.factory.BankAccountUiFactory;
import trapx00.lightx00.client.presentation.helpui.*;
import trapx00.lightx00.client.vo.Draftable;
import trapx00.lightx00.client.vo.EmployeeVo;
import trapx00.lightx00.client.vo.Reversible;
import trapx00.lightx00.client.vo.financestaff.BankAccountVo;
import trapx00.lightx00.client.vo.financestaff.CashBillVo;
import trapx00.lightx00.shared.exception.bl.UncheckedRemoteException;
import trapx00.lightx00.shared.exception.database.NoMoreBillException;
import trapx00.lightx00.shared.exception.presentation.NotCompleteException;
import trapx00.lightx00.shared.po.bill.BillState;
import trapx00.lightx00.shared.po.financestaff.CashBillItem;
import trapx00.lightx00.shared.util.BillHelper;
import trapx00.lightx00.shared.util.DateHelper;

import java.util.Date;

/**
 * 财务人员的制定现金费用单UIController。
 * DraftContinueWritableUiController：草稿继续填写UI
 * ExternalLoadableUiController：是功能UI
 * ReversibleUi：红冲UI
 */
public class CashBillUiController implements DraftContinueWritableUiController, ExternalLoadableUiController, ReversibleUi {

    public JFXTextField tfBankaccountId;
    public JFXTextField tfOperator;
    public JFXTextField tfDate;
    public JFXTextField tfId;
    public JFXTreeTableView<CashBillItemModel> tbCashBillItems;
    public JFXTreeTableColumn<CashBillItemModel, String> tcName;
    public JFXTreeTableColumn<CashBillItemModel, String> tcPrice;
    public JFXTreeTableColumn<CashBillItemModel, String> tcComment;
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public Label lbTotal;

    private ObjectProperty<BankAccountVo> currentBankAccount = new SimpleObjectProperty<>();
    private ObjectProperty<Date> currentDate = new SimpleObjectProperty<>();
    private ObjectProperty<EmployeeVo> currentEmployee = new SimpleObjectProperty<>();

    private CashBillBlService blService = CashBillBlServiceFactory.getInstance();

    private BankAccountSelection bankAccountSelection = BankAccountUiFactory.getBankAccountSelectionUi();
    private EmployeeSelection employeeSelection = UserManagementUiFactory.getEmployeeSelectionUi();

    private ObservableList<CashBillItemModel> cashBillItemModelObservableList = FXCollections.observableArrayList();

    /**
     * Start continuing write a draft. Returns a ExternalLoadableUiController. It can be used to set the stage without casting to specific ui controller.
     * Overrides to return a specific ui controller.
     *
     * @param draft draft
     * @return a ExternalLoadableUiController
     */
    @Override
    public ExternalLoadedUiPackage continueWriting(Draftable draft) {
        /**
         * 草稿功能实现。
         * 和对应单据详细界面一样，通过传入的参数初始化对应的控件元素信息。
         */
        CashBillVo cashBillVo = (CashBillVo) draft;
        ExternalLoadedUiPackage externalLoadedUiPackage = load();
        CashBillUiController cashBillDetailUiController = externalLoadedUiPackage.getController();
        cashBillDetailUiController.tfId.setText(cashBillVo.getId());
        cashBillDetailUiController.currentDate.setValue(cashBillVo.getDate());
        cashBillDetailUiController.currentBankAccount.setValue(bankAccountSelection.queryId(cashBillVo.getAccountId()));
        cashBillDetailUiController.currentEmployee.setValue(employeeSelection.queryId(cashBillVo.getOperatorId()));
        cashBillDetailUiController.addCashBillItems(cashBillVo.getItems());
        return externalLoadedUiPackage;
    }

    public void initialize() {
        tcName.setCellValueFactory(cellData -> cellData.getValue().getValue().nameProperty());
        tcPrice.setCellValueFactory(cellData -> new SimpleStringProperty(BillHelper.toFixed(cellData.getValue().getValue().getAmount())));
        tcComment.setCellValueFactory(cellData -> cellData.getValue().getValue().commentProperty());

        currentBankAccount.addListener((observable, oldValue, newValue) -> {
            tfBankaccountId.setText(newValue == null ? "" : String.format("%s(id: %s)", newValue.getId(), newValue.getName()));
        });

        currentDate.addListener(((observable, oldValue, newValue) -> {
            tfDate.setText(newValue == null ? "" : DateHelper.fromDate(newValue));
        }));

        currentEmployee.addListener(((observable, oldValue, newValue) -> {
            tfOperator.setText(newValue == null ? "" : String.format("%s(id: %s)", newValue.getId(), newValue.getName()));
        }));

        cashBillItemModelObservableList.addListener((ListChangeListener<CashBillItemModel>) c -> {
            lbTotal.setText(BillHelper.toFixed(cashBillItemModelObservableList.stream().mapToDouble(CashBillItemModel::getAmount).sum()));
        });



        TreeItem<CashBillItemModel> root = new RecursiveTreeItem<>(cashBillItemModelObservableList, RecursiveTreeObject::getChildren);
        tbCashBillItems.setRoot(root);
        tbCashBillItems.setShowRoot(false);
        tbCashBillItems.setEditable(true);
        tbCashBillItems.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void addCashBillItems(CashBillItem[] cashBillItems) {
        for (CashBillItem cashBillItem : cashBillItems) {
            cashBillItemModelObservableList.add(
                new CashBillItemModel(cashBillItem));
        }

    }

    /**
     * Loads the controller.
     *
     * @return external loaded ui controller and component
     */
    @Override
    public ExternalLoadedUiPackage load() {
        return new UiLoader("/fxml/financeui/CashBillUi.fxml").loadAndGetPackageWithoutException();
    }

    /**
     * Revert a reversible.
     *
     * @param reversible reversible
     * @return External loaded ui package including a controller and the component.
     */
    @Override
    public ExternalLoadedUiPackage revertReversible(Reversible reversible) {
        /**
         * 红冲功能实现。
         * 和草稿继续填写差不多，但是需要：
         * 1. 重新获取ID（因为红冲单子其实是一张新的UI单子）
         * 2. 取反数量。
         */
        CashBillVo cashBillVo = (CashBillVo) reversible;
        ExternalLoadedUiPackage externalLoadedUiPackage = load();
        CashBillUiController cashBillDetailUiController = externalLoadedUiPackage.getController();
        cashBillDetailUiController.tfId.setText(cashBillDetailUiController.blService.getId());
        cashBillDetailUiController.currentEmployee.setValue(FrameworkUiManager.getCurrentEmployee());
        cashBillDetailUiController.currentBankAccount.setValue(bankAccountSelection.queryId(cashBillVo.getAccountId()));
        cashBillDetailUiController.currentDate.setValue(new Date());
        for (CashBillItem item : cashBillVo.getItems()) {
            item.setAmount(-item.getAmount());
        }
        cashBillDetailUiController.addCashBillItems(cashBillVo.getItems());
        return externalLoadedUiPackage;
    }

    public void onBtnAddItemClicked() {
        new AddCashBillItemDialog().show(item -> {
            cashBillItemModelObservableList.add(new CashBillItemModel(item));
        });
    }

    public void onBtnDeleteItemClicked() {
        try {
            int index = tbCashBillItems.getSelectionModel().getSelectedIndex();
            cashBillItemModelObservableList.remove(index);
        } catch (Exception ignored) {

        }
    }

    private CashBillVo getCurrentCashBillVo() {
        if (tfId.getText().length() == 0 || currentEmployee == null || currentDate == null) {
            PromptDialogHelper.start("提交失败！","请选点击自动填写信息以自动填写ID、日期和操作员信息。")
                .addCloseButton("好的","CHECK", null)
                .createAndShow();
            throw new NotCompleteException();
        }
        if (tfBankaccountId.getText().length() == 0) {
            PromptDialogHelper.start("提交失败！","请先选择银行账户。")
                .addCloseButton("好的","CHECK", null)
                .createAndShow();
            throw new NotCompleteException();
        }
        return new CashBillVo(
            tfId.getText(),
            currentDate.getValue(),
            BillState.Draft,
            currentEmployee.getValue().getId(),
            currentBankAccount.getValue().getId(),
            cashBillItemModelObservableList.stream().map(CashBillItemModel::toCashBillItem).toArray(CashBillItem[]::new)
        );
    }

    public void onBtnSubmitClicked() {
        try {
            blService.submit(getCurrentCashBillVo());
            PromptDialogHelper.start("提交成功！", "你的单据已经提交成功")
                .addCloseButton("好的", "CHECK", e -> onBtnResetClicked())
                .createAndShow();
        } catch (NotCompleteException ignored) {
        } catch (UncheckedRemoteException e) {
            PromptDialogHelper.start("提交失败！","网络错误。详细信息：\n" + e.getRemoteException().getMessage())
                .addCloseButton("好的","CHECK", null)
                .createAndShow();
        }
    }

    public void onBtnSaveAsDraftClicked() {
        try {
            blService.saveAsDraft(getCurrentCashBillVo());
            PromptDialogHelper.start("保存草稿成功","你的单据已经保存为草稿。")
                .addCloseButton("好的","CHECK", e -> onBtnResetClicked())
                .createAndShow();
        } catch (NotCompleteException ignored) {
        } catch (UncheckedRemoteException e) {
            PromptDialogHelper.start("提交失败！","网络错误。详细信息：\n" + e.getRemoteException().getMessage())
                .addCloseButton("好的","CHECK", null)
                .createAndShow();
        }
    }

    public void onBtnResetClicked() {
        tfId.setText("");
        currentDate.setValue(null);
        currentEmployee.setValue(null);
        currentBankAccount.setValue(null);
        cashBillItemModelObservableList.clear();
    }

    public void onBtnAutofillClicked() {
        try {
            tfId.setText(blService.getId());
            currentDate.setValue(new Date());
            currentEmployee.setValue(FrameworkUiManager.getCurrentEmployee());
        } catch (NoMoreBillException e) {
            PromptDialogHelper.start("ID不够！","当日ID已经达到99999，无法增加新的单据。")
                .addCloseButton("好的","CHECK", null)
                .createAndShow();
        }

    }

    public void onTfBankAccountClicked(MouseEvent actionEvent) {
        BankAccountUiFactory.getBankAccountSelectionUi()
            .showBankAccountSelectDialog(vo -> this.currentBankAccount.setValue(vo));
    }
}
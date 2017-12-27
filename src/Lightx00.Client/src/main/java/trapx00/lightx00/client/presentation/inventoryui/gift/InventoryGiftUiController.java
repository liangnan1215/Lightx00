package trapx00.lightx00.client.presentation.inventoryui.gift;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import trapx00.lightx00.client.blservice.inventoryblservice.InventoryGiftBlService;
import trapx00.lightx00.client.blservice.inventoryblservice.InventoryGiftBlServiceFactory;
import trapx00.lightx00.client.presentation.commodityui.commodity.CommoditySelection;
import trapx00.lightx00.client.presentation.commodityui.factory.CommodityUiFactory;
import trapx00.lightx00.client.presentation.helpui.*;
import trapx00.lightx00.client.vo.Draftable;
import trapx00.lightx00.client.vo.EmployeeVo;
import trapx00.lightx00.client.vo.Reversible;
import trapx00.lightx00.client.vo.inventorystaff.CommodityVo;
import trapx00.lightx00.client.vo.inventorystaff.InventoryGiftVo;
import trapx00.lightx00.shared.exception.bl.UncheckedRemoteException;
import trapx00.lightx00.shared.exception.database.IdExistsException;
import trapx00.lightx00.shared.exception.database.NoMoreBillException;
import trapx00.lightx00.shared.exception.presentation.NotCompleteException;
import trapx00.lightx00.shared.po.bill.BillState;
import trapx00.lightx00.shared.po.manager.promotion.PromotionCommodity;
import trapx00.lightx00.shared.util.DateHelper;

import java.util.Date;

public class InventoryGiftUiController implements DraftContinueWritableUiController, ExternalLoadableUiController,ReversibleUi {

    public JFXTextField tfOperator;
    public JFXTextField tfDate;
    public JFXTextField tfId;
    public JFXButton btnAdd;
    public JFXButton btnDelete;
    public JFXButton btnSet;
    public JFXTreeTableView<InventoryGiftItemModel> inventoryGiftItems;
    public JFXTreeTableColumn<InventoryGiftItemModel, String> tcName;
    public JFXTreeTableColumn<InventoryGiftItemModel, String> tcPrice;
    public JFXTreeTableColumn<InventoryGiftItemModel, String> tcAmount;
    public JFXTreeTableColumn<InventoryGiftItemModel,String > tfAcutal;

    private ObjectProperty<CommodityVo> currentCommodity = new SimpleObjectProperty<>();
    private ObjectProperty<Date> currentDate = new SimpleObjectProperty<>();
    private ObjectProperty<EmployeeVo> currentEmployee = new SimpleObjectProperty<>();

    private ObservableList<InventoryGiftItemModel> inventoryGiftItemModelObservableList = FXCollections.observableArrayList();
    private InventoryGiftBlService blService= InventoryGiftBlServiceFactory.getInstance();
    private CommoditySelection commoditySelection= CommodityUiFactory.getCommoditySelectionUi();

    /**
     * Start continuing write a draft. Returns a External loaded ui package.
     * Overrides to return a specific ui controller.
     *
     * @param draft draft
     * @return External loaded ui package including a controller and the component.
     */
    @Override
    public ExternalLoadedUiPackage continueWriting(Draftable draft) {
        /**
         * �ݸ幦��ʵ�֡�
         * �Ͷ�Ӧ������ϸ����һ����ͨ������Ĳ�����ʼ����Ӧ�Ŀؼ�Ԫ����Ϣ��
         */
        InventoryGiftVo inventoryGiftVo = (InventoryGiftVo) draft;
        ExternalLoadedUiPackage externalLoadedUiPackage = load();
        InventoryGiftUiController inventoryGiftUiController = (InventoryGiftUiController) externalLoadedUiPackage.getController();
        inventoryGiftUiController.tfId.setText(inventoryGiftVo.getId());
        inventoryGiftUiController.tfDate.setText(DateHelper.fromDate(inventoryGiftVo.getDate()));
        inventoryGiftUiController.tfOperator.setText(inventoryGiftVo.getOperatorId());
        inventoryGiftUiController.addGiftItems(inventoryGiftVo.getGifts());
        return externalLoadedUiPackage;
    }

    public void initialize() {



        tcName.setCellValueFactory(cellData -> new SimpleStringProperty(CommodityUiFactory.getCommoditySelectionUi().queryId(cellData.getValue().getValue().getPromotionCommodityObjectProperty().getCommodityId()).getName()));
        tcAmount.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getValue().getPromotionCommodityObjectProperty().getAmount())));
        tcPrice.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getValue().getPromotionCommodityObjectProperty().getPrice())));

        currentDate.addListener(((observable, oldValue, newValue) -> {
            tfDate.setText(newValue == null ? "" : DateHelper.fromDate(newValue));
        }));

        currentEmployee.addListener(((observable, oldValue, newValue) -> {
            tfOperator.setText(newValue == null ? "" : String.format("%s(id: %s)", newValue.getId(), newValue.getName()));
        }));

        autofill();

        TreeItem<InventoryGiftItemModel> root = new RecursiveTreeItem<>(inventoryGiftItemModelObservableList, RecursiveTreeObject::getChildren);
        inventoryGiftItems.setRoot(root);
        inventoryGiftItems.setShowRoot(false);
        inventoryGiftItems.setEditable(true);
        inventoryGiftItems.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }


    public void addGiftItems(PromotionCommodity[] promotionCommodities) {
        for (PromotionCommodity promotionCommodity : promotionCommodities) {
            inventoryGiftItemModelObservableList.add(new InventoryGiftItemModel(promotionCommodity));
        }

    }
    /**
     * Loads the controller.
     *
     * @return external loaded ui controller and component
     */
    @Override
    public ExternalLoadedUiPackage load() {
        return new UiLoader("/fxml/inventoryui/gift/InventoryGiftUi.fxml").loadAndGetPackageWithoutException();
    }

    @Override
    public ExternalLoadedUiPackage revertReversible(Reversible reversible) {
        /**
         * ��幦��ʵ�֡�
         * �Ͳݸ������д��࣬������Ҫ��
         * 1. ���»�ȡID����Ϊ��嵥����ʵ��һ���µ�UI���ӣ�
         * 2. ȡ��������
         */
        InventoryGiftVo inventoryGiftVo = (InventoryGiftVo) reversible;
        ExternalLoadedUiPackage externalLoadedUiPackage = load();
        InventoryGiftUiController inventoryGiftUiController = externalLoadedUiPackage.getController();
        inventoryGiftUiController.tfId.setText(inventoryGiftUiController.blService.getId());
        inventoryGiftUiController.currentEmployee.setValue(FrameworkUiManager.getCurrentEmployee());
        inventoryGiftUiController.currentDate.setValue(new Date());
        for (PromotionCommodity item : inventoryGiftVo.getGifts()) {
            item.setAmount(-item.getAmount());
        }
        inventoryGiftUiController.addGiftItems(inventoryGiftVo.getGifts());
        return externalLoadedUiPackage;
    }

    public void onBtnAddItemClicked() {
        CommodityUiFactory.getCommoditySelectionUi()
                .showCommoditySelectDialog(vo ->addItem(vo));

    }

    public void addItem(CommodityVo vo){
        InventoryGiftItemModel inventoryGiftItemModel=new InventoryGiftItemModel(new PromotionCommodity(vo.getId(),
                vo.getName(),vo.getRetailPrice(),0));
        new InventoryGiftItemModificationUi().show(vo.getId(),aDouble -> inventoryGiftItemModelObservableList.add(new InventoryGiftItemModel(
                new PromotionCommodity(vo.getId(),vo.getName(),vo.getRecentRetailPrice(),aDouble)
        )));


    }

    public void onBtnSetItemClicked(){
        InventoryGiftItemModel inventoryGiftItemModel=getSelected();
        if(inventoryGiftItemModel!=null){
            new InventoryGiftItemModificationUi().show(inventoryGiftItemModel.getPromotionCommodityObjectProperty().getCommodityId(),aDouble -> inventoryGiftItemModel.getPromotionCommodityObjectProperty().setAmount(aDouble));
            if(inventoryGiftItemModel.getPromotionCommodityObjectProperty().getAmount()>
                   commoditySelection.queryId(inventoryGiftItemModel.getPromotionCommodityObjectProperty().getCommodityId()).getAmount() )
            {
                PromptDialogHelper.start("ʧ��","�������������������");
                inventoryGiftItemModel.getPromotionCommodityObjectProperty().setAmount(0);
            }
        }

    }

    public InventoryGiftItemModel getSelected(){
        try {
            return inventoryGiftItems.getSelectionModel().getSelectedItem().getValue();
        } catch (Exception e) {
            new PromptDialogHelper("δѡ��","����ѡ��һ��������Ʒ��")
                    .addCloseButton("��","CHECK", null)
                    .createAndShow();
            return null;
        }
    }

    public void onBtnDeleteItemClicked() {
        try {
            int index = inventoryGiftItems.getSelectionModel().getSelectedIndex();
            inventoryGiftItemModelObservableList.remove(index);
        } catch (Exception ignored) {

        }
    }

    private InventoryGiftVo getCurrentGiftVo() {
        if (tfId.getText().length() == 0 || currentEmployee == null || currentDate == null) {
            PromptDialogHelper.start("�ύʧ�ܣ�","��ѡ����Զ���д��Ϣ���Զ���дID�����ںͲ���Ա��Ϣ��")
                    .addCloseButton("�õ�","CHECK", null)
                    .createAndShow();
            throw new NotCompleteException();
        }

        return new InventoryGiftVo(
                tfId.getText(),
                currentDate.getValue(),
                BillState.Draft,
                inventoryGiftItemModelObservableList.stream().map(InventoryGiftItemModel::getPromotionCommodityObjectProperty).toArray(PromotionCommodity[]::new),
                currentEmployee.getValue().getId()
        );
    }

    public void onBtnSubmitClicked() {
        try {
            InventoryGiftVo inventoryGiftVo=getCurrentGiftVo();
            PromptDialogHelper.start("ȷ�ϵ���", "").setContent(
                    inventoryGiftVo.billDetailUi().showContent(inventoryGiftVo).getComponent())
                    .addCloseButton("ȷ��", "CHECK", e -> {
                        try {
                            blService.sumbit(inventoryGiftVo);
                            PromptDialogHelper.start("�ύ�ɹ���", "��ĵ����Ѿ��ύ�ɹ���")
                                    .addCloseButton("������д", "EDIT", e1 -> {
                                        onBtnResetClicked();
                                        autofill();
                                    })
                                    .addCloseButton("����������", "CHECK", e1 -> FrameworkUiManager.switchBackToHome())
                                    .createAndShow();
                        } catch (UncheckedRemoteException e1) {
                            PromptDialogHelper.start("�ύʧ�ܣ�", "���������ϸ��Ϣ��\n" + e1.getRemoteException().getMessage())
                                    .addCloseButton("�õ�", "CHECK", null)
                                    .createAndShow();
                        } catch (IdExistsException e1) {
                            PromptDialogHelper.start("�ύʧ��", "ID�Ѿ����ڣ������»�ȡID��")
                                    .addCloseButton("�õ�", "CHECK", null)
                                    .createAndShow();
                        }
                    })
                    .addCloseButton("ȡ��", "CLOSE", null)
                    .createAndShow();
        } catch (NotCompleteException ignored) {

        }
    }

    public void onBtnSaveAsDraftClicked() {
        saveAsDraft();

    }

    public void saveAsDraft(){
        try {
            blService.saveAsDraft(getCurrentGiftVo());
            PromptDialogHelper.start("����ݸ�ɹ�", "��ĵ����Ѿ�����Ϊ�ݸ塣")
                    .addCloseButton("�õ�", "CHECK", e -> FrameworkUiManager.switchBackToHome())
                    .createAndShow();
        } catch (NotCompleteException ignored) {
        } catch (UncheckedRemoteException e) {
            PromptDialogHelper.start("�ύʧ�ܣ�", "���������ϸ��Ϣ��\n" + e.getRemoteException().getMessage())
                    .addCloseButton("�õ�", "CHECK", null)
                    .createAndShow();
        } catch (Exception e) {
            PromptDialogHelper.start("�ύʧ��", "������Ϣ���£�\n" + e.getMessage())
                    .addCloseButton("�õ�", "CHECK", null)
                    .createAndShow();
        }
    }

    public void autofill(){
        try {
            tfId.setText(blService.getId());
            currentDate.setValue(new Date());
            currentEmployee.setValue(FrameworkUiManager.getCurrentEmployee());
        } catch (NoMoreBillException e) {
            PromptDialogHelper.start("ID������","����ID�Ѿ��ﵽ99999���޷������µĵ��ݡ�")
                    .addCloseButton("�õ�","CHECK", null)
                    .createAndShow();
        }
    }
    public void onBtnResetClicked() {
        tfId.setText("");
        tfOperator.setText("");
        tfDate.setText("");
        currentDate.setValue(null);
        currentEmployee.setValue(null);
        currentCommodity.setValue(null);
        inventoryGiftItemModelObservableList.clear();
    }

    public void onBtnCancelClicked() {
        PromptDialogHelper.start("�Ƿ��˳���", "�Ƿ񱣴�ݸ壿")
                .addCloseButton("����", "SAVE", e -> saveAsDraft())
                .addCloseButton("������", "DELETE", e -> FrameworkUiManager.switchBackToHome())
                .addCloseButton("ȡ��", "CLOSE", null)
                .createAndShow();

    }





}

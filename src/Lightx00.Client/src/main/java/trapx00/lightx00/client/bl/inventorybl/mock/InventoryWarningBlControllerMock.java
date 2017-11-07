package trapx00.lightx00.client.bl.inventorybl.mock;

import trapx00.lightx00.client.bl.inventorybl.InventoryWarningBlController;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.queryvo.CommodityQueryVo;
import trapx00.lightx00.client.vo.inventorystaff.CommodityVo;
import trapx00.lightx00.shared.queryvo.InventoryBillQueryVo;
import trapx00.lightx00.client.vo.inventorystaff.InventoryBillVo;

public class InventoryWarningBlControllerMock extends InventoryWarningBlController{
    /**
     * Submits a Bill.
     * @param bill
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage submit(InventoryBillVo bill) {
        return super.submit(bill);
    }

    /**
     * Saves a half-completed Bill as a draft.
     * @param bill
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage saveAsDraft(InventoryBillVo bill) {
        return super.saveAsDraft(bill);
    }

    /**
     * Modifys the warning value of the commodity
     * @param id ,the id of the commodity
     * @param modifyWarning
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage modify(String id, double modifyWarning) {
        return super.modify(id,modifyWarning);
    }

    /**
     * Gets the current Bill
     * @return the current BillVo
     */
    @Override
    public InventoryBillVo getCurrentBill() {
        return super.getCurrentBill();
    }

    /**
     *  Querys a bill
     * @param inventoryBillQueryVo
     * @return InventoryBill that match query condition
     */
    @Override
    public InventoryBillVo[] query(InventoryBillQueryVo inventoryBillQueryVo) {
        return super.query(inventoryBillQueryVo);
    }

    /**
     * Gets the id for the next bill.
     * @return id for the next bill
     */
    @Override
    public String getId() {
        return super.getId();
    }

    /**
     *  Deletes a draft.
     * @param id id of the draft to be deleted
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage deleteDraft(String id) {
        return super.deleteDraft(id);
    }

    /**
     * Abandons a bill.
     * @param id id for the bill
     * @return  whether the operation is done successfully
     */
    @Override
    public ResultMessage abandon(String id) {
        return super.abandon(id);
    }

    /**
     *  Activates a bill that has been approved of.
     * @param id id for the bill
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage activate(String id) {
        return super.activate(id);
    }

    /**
     * Query a commodity
     * @return Commodity that match query condition
     */
    @Override
    public CommodityVo[] queryCommodity(CommodityQueryVo commodityQueryVo) {
        return super.queryCommodity(commodityQueryVo);
    }
}
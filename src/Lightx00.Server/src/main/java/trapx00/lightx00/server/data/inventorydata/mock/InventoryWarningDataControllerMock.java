package trapx00.lightx00.server.data.inventorydata.mock;

import trapx00.lightx00.server.data.inventorydata.InventoryWarningDataController;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.po.inventorystaff.InventoryBillPo;
import trapx00.lightx00.shared.vo.inventorystaff.InventoryBillQueryVo;

public class InventoryWarningDataControllerMock extends InventoryWarningDataController {
    /**
     * Submits a bill
     * @param bill
     * @return  whether the operation is done successfully
     */
    @Override
    public ResultMessage submit(InventoryBillPo bill) {
        return super.submit(bill);
    }
    /**
     *  Modifys the warning value of the commodity
     * @param id
     * @param warningValue
     * @return  whether the operation is done successfully
     */
    @Override
    public ResultMessage modify(String id, double warningValue) {
        return super.modify(id, warningValue);
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
     * Gets the Bill
     * @param ids
     * @return the bill
     */
    @Override
    public InventoryBillPo[] getAlarmByIds(String... ids) {
        return super.getAlarmByIds(ids);
    }
    /**
     * Gets the Bill
     * @param ids
     * @return the bill
     */
    @Override
    public InventoryBillPo[] getOverflowByIds(String... ids) {
        return super.getOverflowByIds(ids);
    }
    /**
     * Gets the Bill
     * @param ids
     * @return the bill
     */
    @Override
    public InventoryBillPo[] getLossByIds(String... ids) {
        return super.getLossByIds(ids);
    }
    /**
     *  Querys a bill
     * @param inventoryBillQueryVo
     * @return InventoryBillVo
     */
    @Override
    public InventoryBillPo[] query(InventoryBillQueryVo inventoryBillQueryVo) {
        return super.query(inventoryBillQueryVo);
    }

}
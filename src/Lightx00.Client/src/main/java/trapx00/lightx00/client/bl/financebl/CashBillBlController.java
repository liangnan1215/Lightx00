package trapx00.lightx00.client.bl.financebl;

import trapx00.lightx00.client.bl.notificationbl.NotificationAbandonService;
import trapx00.lightx00.client.bl.notificationbl.NotificationActivateService;
import trapx00.lightx00.client.blservice.financeblservice.CashBillBlService;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.vo.BillVo;
import trapx00.lightx00.shared.vo.financestaff.CashBillVo;

public class CashBillBlController implements CashBillBlService, NotificationActivateService, NotificationAbandonService {

    /**
     * Abandons a bill.
     *
     * @param bill bill
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage abandon(BillVo bill) {
        return null;
    }

    /**
     * Activates a bill that has been approved of.
     *
     * @param bill bill that has been approved of
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage activate(BillVo bill) {
        return null;
    }

    /**
     * Submits a CashBill.
     *
     * @param bill CashBillVo to be submitted
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage submit(CashBillVo bill) {
        return null;
    }

    /**
     * Saves a half-completed CashBill as draft.
     *
     * @param bill CashBillVo to be saved as a draft
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage saveAsDraft(CashBillVo bill) {
        return null;
    }

    /**
     * Gets the id for the next bill
     *
     * @return id for the next bill
     */
    @Override
    public String getId() {
        return null;
    }
}

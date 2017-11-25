package trapx00.lightx00.client.bl.financebl.mock;

import trapx00.lightx00.client.bl.financebl.PaymentBillBlController;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.po.bill.BillState;
import trapx00.lightx00.shared.po.financestaff.Transcation;
import trapx00.lightx00.shared.queryvo.PaymentBillQueryVo;
import trapx00.lightx00.client.vo.financestaff.PaymentBillVo;

import java.util.Date;

public class PaymentBillBlControllerMock extends PaymentBillBlController {
    /**
     * Deletes a draft.
     *
     * @param id id of the draft to be deleted
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage deleteDraft(String id) {
        return ResultMessage.Success;
    }

    /**
     * Abandons a bill.
     *
     * @param id id for the bill
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage abandon(String id) {
        return ResultMessage.Success;
    }

    /**
     * Activates a bill that has been approved of.
     *
     * @param id id for the bill
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage activate(String id) {
        return ResultMessage.Success;
    }

    /**
     * Submits a PaymentBill.
     *
     * @param bill PaymentBill to be submitted
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage submit(PaymentBillVo bill) {
        return ResultMessage.Success;
    }

    /**
     * Saves a half-completed PaymentBill as a draft.
     *
     * @param bill PaymentBill to be saved as a draft
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage saveAsDraft(PaymentBillVo bill) {
        return ResultMessage.Success;
    }

    /**
     * Gets the id for the next bill.
     *
     * @return id for the next bill
     */
    @Override
    public String getId() {
        return "FXD-20171112-00001";
    }

    /**
     * Queries PaymentBill.
     *
     * @param query query
     * @return PaymentBillVos that match the condition
     */
    @Override
    public PaymentBillVo[] query(PaymentBillQueryVo query) {
        return new PaymentBillVo[]{
                new PaymentBillVo("FXD-20171111-00001",new Date(), BillState.Approved, "123","123",new Transcation[] { new Transcation("123",0,"123")}, 0.0)
        };
    }

    /**
     * When bill is approved, this method is called to modify the state of the bill.
     *
     * @param billId id for the bill
     * @param state  newState. Only BillState.Approved and BillState
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage approvalComplete(String billId, BillState state) {
        return ResultMessage.Success;
    }
}

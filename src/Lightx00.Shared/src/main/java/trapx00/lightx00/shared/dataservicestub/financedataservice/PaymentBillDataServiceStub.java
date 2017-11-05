package trapx00.lightx00.shared.dataservicestub.financedataservice;

import trapx00.lightx00.shared.dataservice.financedataservice.PaymentBillDataService;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.po.financestaff.PaymentBillPo;
import trapx00.lightx00.shared.vo.financestaff.PaymentBillQueryVo;
import trapx00.lightx00.shared.vo.financestaff.PaymentBillVo;

public class PaymentBillDataServiceStub implements PaymentBillDataService {
    /**
     * Submits a bill or saves a bill as a draft
     *
     * @param bill PaymentBill
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage submit(PaymentBillPo bill) {
        return null;
    }

    /**
     * Activates a bill.
     *
     * @param id id for the PaymentBill that have been approved of
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage activate(String id) {
        return null;
    }

    /**
     * Abandons a bill.
     *
     * @param id id for the PaymentBill to be abandoned
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage abandon(String id) {
        return null;
    }

    /**
     * Queries bills
     *
     * @param query query
     * @return PaymentBillVos that match query condition
     */
    @Override
    public PaymentBillVo[] query(PaymentBillQueryVo query) {
        return new PaymentBillVo[0];
    }

    /**
     * Gets the id for the next bill.
     *
     * @return id for the next bill
     */
    @Override
    public String getId() {
        return null;
    }
}

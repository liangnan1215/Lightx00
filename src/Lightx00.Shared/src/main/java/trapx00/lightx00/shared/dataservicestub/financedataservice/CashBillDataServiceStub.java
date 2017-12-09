package trapx00.lightx00.shared.dataservicestub.financedataservice;

import trapx00.lightx00.shared.dataservice.financedataservice.CashBillDataService;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.po.bill.BillState;
import trapx00.lightx00.shared.po.financestaff.CashBillItem;
import trapx00.lightx00.shared.po.financestaff.CashBillPo;
import trapx00.lightx00.shared.queryvo.CashBillQueryVo;

import java.rmi.RemoteException;
import java.util.Date;

public class CashBillDataServiceStub implements CashBillDataService {

    /**
     * Submits a CashBill or save it as a draft.
     *
     * @param bill CashBill
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage submit(CashBillPo bill) {
        return ResultMessage.Success;
    }

    /**
     * Activates a CashBill.
     *
     * @param id id for the CashBill that have been approved of
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage activate(String id) {
        return ResultMessage.Success;
    }

    /**
     * Abandons a CashBill.
     *
     * @param id id for the CashBill to be abandoned
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage abandon(String id) {
        return ResultMessage.Success;
    }


    /**
     * Queries CashBill.
     *
     * @param query query condition
     * @return CashBillVos that match the query condition
     */
    @Override
    public CashBillPo[] query(CashBillQueryVo query) {
        return new CashBillPo[]{
                new CashBillPo("123", new Date(), BillState.Draft, "123",1,new CashBillItem[]{
                        new CashBillItem("123", 0.0, "123")
                })
        };
    }

    /**
     * Changes the state of a bill if approval is completed.
     *
     * @param billId    the id of the bill.
     * @param billState new bill state. Only Approved and Rejected is allowed.
     * @return whether the operation is done successfully.
     */
    @Override
    public ResultMessage approvalComplete(String billId, BillState billState) throws RemoteException {
        return ResultMessage.Success;
    }

    /**
     * Gets the id for the next bill.
     *
     * @return id for the next bill
     */
    @Override
    public String getId() {
        return "XJFKD-20171111-00001";
    }
}

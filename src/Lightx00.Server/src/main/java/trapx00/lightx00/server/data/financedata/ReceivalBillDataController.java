package trapx00.lightx00.server.data.financedata;

import com.j256.ormlite.dao.Dao;
import com.sun.org.apache.regexp.internal.RE;
import trapx00.lightx00.server.data.financedata.factory.FinanceDataDaoFactory;
import trapx00.lightx00.server.data.util.CommonBillDataController;
import trapx00.lightx00.shared.dataservice.financedataservice.ReceivalBillDataService;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.po.financestaff.ReceivalBillPo;
import trapx00.lightx00.shared.queryvo.ReceivalBillQueryVo;

import java.rmi.RemoteException;
import java.rmi.server.RMISocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ReceivalBillDataController extends UnicastRemoteObject implements ReceivalBillDataService {
    /**
     * Creates and exports a new UnicastRemoteObject object using an
     * anonymous port.
     * <p>
     * <p>The object is exported with a server socket
     * created using the {@link RMISocketFactory} class.
     *
     * @throws RemoteException if failed to export object
     * @since JDK1.1
     */
    public ReceivalBillDataController() throws RemoteException {

    }

    private Dao<ReceivalBillPo, String> dao = FinanceDataDaoFactory.getReceivalBillDao();
    private CommonBillDataController<ReceivalBillPo> commonBillDataController = new CommonBillDataController<>(dao);
    /**
     * Submits a ReceivalBillPo or save it as a draft.
     * If there is a bill with the same id as passed-in parameter do,
     *    if the existing bill is in BillState.Draft state, it will be updated/replaced by parameter.
     *    otherwise a IdExistsException would be thrown.
     *
     * @param bill bill
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage submit(ReceivalBillPo bill) {
        return commonBillDataController.submit(bill);
    }

    /**
     * Activates a ReceivalBillPo.
     * The bill must be in BillState.WaitingForApproval state.
     * Otherwise a BillInvalidStateException will be thrown.
     *
     * @param id id for the bill that have been approved of
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage activate(String id) {
        return commonBillDataController.activate(id);
    }

    /**
     * Abandons a ReceivalBillPo.
     * If a Bill is in BillState.Draft, it will be deleted.
     * If a Bill is in BillState.Rejected/Approved/WaitingForApproval, it will be changed as Abandoned.
     * If a bill is in other state, a BillInvalidStateException will be thrown.
     * @param id id for the CashBill to be abandoned
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage abandon(String id) {
        return commonBillDataController.abandon(id);
    }

    /**
     * Queries ReceivalBillPos.
     *
     * @param query query
     * @return ReceivalBillVos that match query condition
     */
    @Override
    public ReceivalBillPo[] query(ReceivalBillQueryVo query) {
        List<ReceivalBillPo> results = commonBillDataController.query(query);
        return results.toArray(new ReceivalBillPo[results.size()]);
    }

    /**
     * Gets the id for the next bill.
     * If there are already 99999 bills for this day, a NoMoreBillException will be thrown.
     *
     * @return id for the next bill
     */
    @Override
    public String getId() {
        return commonBillDataController.getId("SKD");
    }
}

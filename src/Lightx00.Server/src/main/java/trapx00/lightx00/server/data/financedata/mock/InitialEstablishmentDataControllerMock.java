package trapx00.lightx00.server.data.financedata.mock;

import trapx00.lightx00.server.data.financedata.InitialEstablishmentDataController;
import trapx00.lightx00.shared.dataservicestub.financedataservice.InitialEstablishmentDataServiceStub;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.po.bill.BillState;
import trapx00.lightx00.shared.po.client.ClientPo;
import trapx00.lightx00.shared.po.client.ClientType;
import trapx00.lightx00.shared.po.financestaff.BankAccountPo;
import trapx00.lightx00.shared.po.financestaff.SystemSnapshotPo;
import trapx00.lightx00.shared.po.inventorystaff.CommodityPo;
import trapx00.lightx00.shared.queryvo.SystemSnapshotQueryVo;

import java.rmi.RemoteException;
import java.rmi.server.RMISocketFactory;
import java.util.Date;

public class InitialEstablishmentDataControllerMock extends InitialEstablishmentDataController {
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
    public InitialEstablishmentDataControllerMock() throws RemoteException {
    }

    /**
     * Submits a snapshot.
     *
     * @param snapshot snapshot to be submitted
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage submit(SystemSnapshotPo snapshot) {
        return ResultMessage.Success;
    }

    /**
     * Activates a SystemSnapshot.
     *
     * @param id id for the SystemSnapshot
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage activate(String id) {
        return ResultMessage.Success;
    }

    /**
     * Deletes a draft.
     *
     * @param id id for a draft
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage abandon(String id) {
        return ResultMessage.Success;
    }

    /**
     * Gets the id for the next snapshot.
     *
     * @return id for the next snapshot
     */
    @Override
    public String getId() {
        return "QCJZ-20171111-00020";
    }

    /**
     * Queries SystemSnapshot.
     *
     * @param query query
     * @return SystemSnapshotVos that match the condition
     */
    @Override
    public SystemSnapshotPo[] query(SystemSnapshotQueryVo query) {
        return  new SystemSnapshotPo[]{
                new SystemSnapshotPo("123", new Date(), BillState.Approved,
                    new CommodityPo[]{new CommodityPo("123", "123", "123", 10.0, new Date(), "123", "123", 10.0, 10.0, 10.0, 10.0, 10.0)},
                    new ClientPo[]{new ClientPo("123", ClientType.Retailer, 5, "123", "123", "123", "123", "123@gmail.com", 10.0, 10.0, null)},
                    new BankAccountPo[]{ new BankAccountPo("123", 10.0, new Date())}
                )
        };
    }
}

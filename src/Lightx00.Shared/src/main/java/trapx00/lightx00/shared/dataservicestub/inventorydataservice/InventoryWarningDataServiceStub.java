package trapx00.lightx00.shared.dataservicestub.inventorydataservice;

import trapx00.lightx00.shared.dataservice.inventorydataservice.InventoryWarningDataService;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.po.bill.BillState;
import trapx00.lightx00.shared.po.inventorystaff.InventoryBillPo;
import trapx00.lightx00.shared.po.inventorystaff.InventoryBillType;
import trapx00.lightx00.shared.po.inventorystaff.InventoryDetailBillPo;
import trapx00.lightx00.shared.queryvo.InventoryBillQueryVo;

import java.rmi.RemoteException;
import java.util.Date;

public class InventoryWarningDataServiceStub implements InventoryWarningDataService {

    @Override
    public ResultMessage submit(InventoryDetailBillPo bill) {
        return ResultMessage.Success;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public ResultMessage approvalComplete(String billId, BillState billState) throws RemoteException {
        return ResultMessage.Success;
    }

    @Override
   public ResultMessage modify(String id, double warningValue) {
        return ResultMessage.Success;
    }



    @Override
    public InventoryDetailBillPo[] query(InventoryBillQueryVo inventoryBillQueryVo) {
        InventoryDetailBillPo inventoryBillVo=new InventoryDetailBillPo("L0001", new Date(),BillState.Approved,InventoryBillType.Overflow,null
        ,null,null,null);
        InventoryDetailBillPo[]input={inventoryBillVo};
        return  input;
    }

    @Override
    public ResultMessage activate(String id) throws RemoteException {
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage abandon(String id) throws RemoteException {
        return ResultMessage.Success;
    }


}

package trapx00.lightx00.server.test.data.approvaldata;

import com.j256.ormlite.dao.Dao;
import org.junit.Test;
import trapx00.lightx00.server.data.approvaldata.factory.AuditDataDaoFactory;
import trapx00.lightx00.server.data.approvaldata.factory.AuditDataFactory;
import trapx00.lightx00.server.data.util.db.BaseDatabaseFactory;
import trapx00.lightx00.shared.dataservice.approvaldataservice.AuditDataService;
import trapx00.lightx00.shared.po.manager.BillInfoPo;
import trapx00.lightx00.shared.po.bill.BillPo;
import trapx00.lightx00.shared.po.bill.BillState;
import trapx00.lightx00.shared.po.bill.BillType;
import trapx00.lightx00.shared.queryvo.BillInfoQueryVo;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AuditDataControllerTest {
    static {
        try {
            BaseDatabaseFactory.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Dao<BillInfoPo, String> dao = AuditDataDaoFactory.getAuditDao();
    private AuditDataService service = AuditDataFactory.getService();
    private final BillPo bill = new BillPo(BillType.FinanceBill,"XJFYD-20171112-00001", new Date(), BillState.WaitingForApproval);

    @Test
    public void query() throws Exception {
        BillInfoPo billInfo1 = new BillInfoPo("XJFYD-20171112-00001", BillType.FinanceBill,new Date(), BillState.WaitingForApproval);
        BillInfoPo billInfo2 = new BillInfoPo("XJFYD-20171112-00002", BillType.FinanceBill,new Date(), BillState.WaitingForApproval);
        dao.create(billInfo2);
        try {
            assertEquals(2, service.query(new BillInfoQueryVo()).length);
            assertEquals(2, service.query(new BillInfoQueryVo(q->q.where().eq("type",BillType.FinanceBill).prepare())).length);
        } finally {
            dao.deleteById(billInfo1.getId());
            dao.deleteById(billInfo2.getId());
        }
    }

    @Test
    public void reject() throws Exception {
        BillInfoPo billInfo2 = new BillInfoPo("XJFYD-20171112-00002", BillType.FinanceBill,new Date(), BillState.WaitingForApproval);
        dao.create(billInfo2);
        try {
            service.reject(billInfo2);
            assertFalse(dao.idExists(billInfo2.getId()));
        } finally {
            dao.deleteById(billInfo2.getId());
        }
    }

    @Test
    public void pass() throws Exception {
        BillInfoPo billInfo2 = new BillInfoPo("XJFYD-20171112-00002", BillType.FinanceBill,new Date(), BillState.WaitingForApproval);
        dao.create(billInfo2);
        try {
            service.pass(billInfo2);
            assertFalse(dao.idExists(billInfo2.getId()));
        } finally {
            dao.deleteById(billInfo2.getId());
        }
    }

    @Test
    public void requestApproval() throws Exception {
        try {
            service.requestApproval(bill);
            assertTrue(dao.idExists(bill.getId()));
        } finally {
            dao.deleteById(bill.getId());
        }
    }

}
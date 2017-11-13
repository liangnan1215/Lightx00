package trapx00.lightx00.client.test.bl.approvalbl;

import org.junit.Test;
import trapx00.lightx00.client.bl.approvalbl.AuditBlController;
import trapx00.lightx00.client.bl.approvalbl.mock.AuditBlControllerMock;
import trapx00.lightx00.client.vo.financestaff.CashBillVo;
import trapx00.lightx00.client.vo.financestaff.FinanceBillVo;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.po.bill.BillState;

import java.util.Date;

import static org.junit.Assert.*;

public class AuditBlControllerTest {
    private AuditBlController controller = new AuditBlControllerMock();
    private CashBillVo bill = new CashBillVo("XJFYD-20171112-00001",new Date(), BillState.WaitingForApproval,"0001","0002",null);
    @Test
    public void reject() throws Exception {
        assertEquals(ResultMessage.Success,controller.reject(bill));
    }

    @Test
    public void pass() throws Exception {
        assertEquals(ResultMessage.Success,controller.pass(bill));
    }

    @Test
    public void requestMessage() throws Exception {
        assertEquals(ResultMessage.Success,controller.requestMessage(bill));
    }

}
package trapx00.lightx00.client.presentationdriver.bankaccountblservice;

import org.junit.Test;
import trapx00.lightx00.client.blservice.bankaccountblservice.BankAccountManagementBlService;
import trapx00.lightx00.client.blservicestub.bankaccountblservice.BankAccountManagementBlServiceStub;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.vo.financestaff.BankAccountQueryVo;

import static org.junit.Assert.*;

public class BankAccountManagementBlServiceDriver {
    BankAccountManagementBlService service = new BankAccountManagementBlServiceStub();

    @Test
    public void add() throws Exception {
        assertEquals(ResultMessage.Success, service.add(null));
    }

    @Test
    public void modify() throws Exception {
        assertEquals(ResultMessage.Success, service.modify(null));
    }

    @Test
    public void query() throws Exception {
        assertEquals("123",service.query(new BankAccountQueryVo("123","123"))[0].getId());
    }

    @Test
    public void delete() throws Exception {
        assertEquals(ResultMessage.Success, service.delete(null));
    }

}
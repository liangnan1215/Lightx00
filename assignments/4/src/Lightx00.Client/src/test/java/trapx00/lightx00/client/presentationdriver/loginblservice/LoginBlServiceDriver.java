package trapx00.lightx00.client.presentationdriver.loginblservice;

import org.junit.Test;
import trapx00.lightx00.client.blservice.loginblservice.LoginBlService;
import trapx00.lightx00.client.blservicestub.loginblservice.LoginBlServiceStub;
import trapx00.lightx00.shared.vo.EmployeeVo;

import static org.junit.Assert.assertEquals;

public class LoginBlServiceDriver {

    LoginBlService service = new LoginBlServiceStub();
    @Test
    public void testLogin(){
        EmployeeVo expected = service.login("123","123");
        assertEquals(expected.getId(), "123");

    }

}

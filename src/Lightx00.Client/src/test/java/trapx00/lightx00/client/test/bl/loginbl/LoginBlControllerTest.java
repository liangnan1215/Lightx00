package trapx00.lightx00.client.test.bl.loginbl;

import org.junit.Test;
import trapx00.lightx00.client.bl.loginbl.LoginBlController;
import trapx00.lightx00.client.bl.loginbl.factory.LoginBlFactory;
import trapx00.lightx00.shared.po.ResultMessage;

import static org.junit.Assert.*;

public class LoginBlControllerTest {
    private LoginBlController controller = LoginBlFactory.getController();
    @Test
    public void login() throws Exception {
        assertNotNull(controller.login("123","123"));
    }

    @Test
    public void getCurrentUser() throws Exception {
        assertEquals("123", controller.getCurrentUser().getId());
    }

}
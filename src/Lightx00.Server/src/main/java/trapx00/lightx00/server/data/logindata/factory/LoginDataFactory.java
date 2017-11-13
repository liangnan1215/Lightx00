package trapx00.lightx00.server.data.logindata.factory;

import trapx00.lightx00.server.data.logindata.mock.LoginDataControllerMock;
import trapx00.lightx00.shared.dataservice.logindataservice.LoginDataService;

import java.rmi.RemoteException;

public class LoginDataFactory {
    private static LoginDataService service;

    static {
        try {
            service = new LoginDataControllerMock();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static LoginDataService getService() {
        return service;
    }
}

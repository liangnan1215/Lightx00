package trapx00.lightx00.server.data.clientdata.factory;

import trapx00.lightx00.server.data.clientdata.mock.ClientDataControllerMock;
import trapx00.lightx00.shared.dataservice.clientdataservice.ClientDataService;

import java.rmi.RemoteException;

public class ClientDataFactory {
    private static ClientDataService service;

    static {
        try {
            service = new ClientDataControllerMock();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static ClientDataService getService() {
        return service;
    }
}
package trapx00.lightx00.server.data.logdata.factory;

import trapx00.lightx00.server.data.logdata.LogBackupDataController;
import trapx00.lightx00.server.data.logdata.LogDataController;
import trapx00.lightx00.shared.dataservice.logdataservice.LogBackupDataService;
import trapx00.lightx00.shared.dataservice.logdataservice.LogDataService;

import java.rmi.RemoteException;

public class LogBackupDataFactory {
    private static LogBackupDataService service;

    static {
        try {
            service = new LogBackupDataController();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static LogBackupDataService getService() {
        return service;
    }
}

package trapx00.lightx00.client.datafactory.logdataservicefactory;

import trapx00.lightx00.shared.dataservice.logdataservice.LogBackupDataService;
import trapx00.lightx00.shared.dataservice.logdataservice.LogDataService;
import trapx00.lightx00.shared.dataservicestub.logdataservice.LogBackupDataServiceStub;
import trapx00.lightx00.shared.dataservicestub.logdataservice.LogDataServiceStub;

import static trapx00.lightx00.client.datafactory.DataServiceFactory.lookupService;

public class LogBackupDataServiceFactory {
    private static LogBackupDataService service = new LogBackupDataServiceStub();

    public static void initRmi() {
        service = lookupService(LogBackupDataService.class);
    }

    public static LogBackupDataService getService() {
        initRmi();
        return service;
    }
}
package trapx00.lightx00.client.datafactory.admindataservicefactory;

import trapx00.lightx00.client.datafactory.DataServiceFactory;
import trapx00.lightx00.shared.dataservice.admindataservice.UserManagementDataService;
import trapx00.lightx00.shared.util.RmiHelper;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class UserManagementDataServiceFactory extends DataServiceFactory {

    private static UserManagementDataService userManagementDataService;

    private static void initRmi() {
        userManagementDataService = lookupService(UserManagementDataService.class);
    }

    public static UserManagementDataService getService() {
        initRmi();
        return userManagementDataService;
    }
}

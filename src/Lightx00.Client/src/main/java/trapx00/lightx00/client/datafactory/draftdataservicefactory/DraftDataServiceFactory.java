package trapx00.lightx00.client.datafactory.draftdataservicefactory;

import trapx00.lightx00.client.bl.draftbl.DraftDeleteService;
import trapx00.lightx00.client.datafactory.DataServiceFactory;
import trapx00.lightx00.shared.dataservice.draftdataservice.DraftDataService;
import trapx00.lightx00.shared.dataservicestub.draftdataservice.DraftDataServiceStub;
import trapx00.lightx00.shared.util.RmiHelper;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class DraftDataServiceFactory extends DataServiceFactory {
    private static DraftDataService service = new DraftDataServiceStub();

    private static void initRmi() {

        service = lookupService(DraftDataService.class);
    }

    public static DraftDataService getService() {
        //initRmi(); //when the rmi is functional, uncomment this to use rmi instead of stub.
        return service;
    }
}

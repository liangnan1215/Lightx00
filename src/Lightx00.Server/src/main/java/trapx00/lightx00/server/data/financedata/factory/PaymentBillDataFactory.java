package trapx00.lightx00.server.data.financedata.factory;

import trapx00.lightx00.server.data.financedata.mock.PaymentBillDataControllerMock;
import trapx00.lightx00.shared.dataservice.financedataservice.PaymentBillDataService;

import java.rmi.RemoteException;

public class PaymentBillDataFactory {
    private static PaymentBillDataService service;

    static {
        try {
            service = new PaymentBillDataControllerMock();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static PaymentBillDataService getService() {
        return service;
    }
}

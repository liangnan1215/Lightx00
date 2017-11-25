package trapx00.lightx00.server.data.logindata.mock;

import trapx00.lightx00.server.data.logindata.LoginDataController;
import trapx00.lightx00.shared.dataservicestub.logdataservice.LogDataServiceStub;
import trapx00.lightx00.shared.dataservicestub.logindataservice.LoginDataServiceStub;
import trapx00.lightx00.shared.po.employee.EmployeePo;
import trapx00.lightx00.shared.po.employee.EmployeePosition;

import java.rmi.RemoteException;
import java.rmi.server.RMISocketFactory;
import java.util.Date;

public class LoginDataControllerMock extends LoginDataController {


    /**
     * Creates and exports a new UnicastRemoteObject object using an
     * anonymous port.
     * <p>
     * <p>The object is exported with a server socket
     * created using the {@link RMISocketFactory} class.
     *
     * @throws RemoteException if failed to export object
     * @since JDK1.1
     */
    public LoginDataControllerMock() throws RemoteException {
    }
    /**
     * Login.
     *
     * @param username username
     * @param password password
     * @return EmployeePo if login is successful
     */
    @Override
    public String login(String username, String password) {
//        return new EmployeePo(username, "123", new Date(), username,password, EmployeePosition.Admin);
        return "1";
    }

}
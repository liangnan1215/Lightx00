package trapx00.lightx00.server;

import trapx00.lightx00.server.data.saledata.SaleBillDataController;
import trapx00.lightx00.shared.dataservice.saledataservice.SaleBillDataService;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

    public static void main(String[] args){
        try {
            SaleBillDataService saleBillDataService = new SaleBillDataController();
            LocateRegistry.createRegistry(8888);
            Naming.bind("rmi://localhost:8888/SaleBillDataService",saleBillDataService);
            System.out.println(">>>>>INFO:远程SaleBillDataController对象绑定成功！");
        } catch (RemoteException e) {
            System.out.println("创建远程对象发生异常！");
            e.printStackTrace();
        } catch (AlreadyBoundException e){
            System.out.println("发生重复绑定对象异常！");
            e.printStackTrace();
        }
        catch (MalformedURLException e) {
            System.out.println("发生URL畸形异常！");
            e.printStackTrace();
        }
    }
}

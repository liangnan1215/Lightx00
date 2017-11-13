package trapx00.lightx00.client.bl.commoditybl.factory;

import trapx00.lightx00.client.bl.commoditybl.InventoryModificationService;
import trapx00.lightx00.client.bl.commoditybl.mock.CommodityBlControllerMock;

public class InventoryModificationServiceFactory {
    private static InventoryModificationService service=new CommodityBlControllerMock();

    public static InventoryModificationService getService(){
        return service;
    }
}

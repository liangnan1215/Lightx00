package trapx00.lightx00.client.blservice.commodityblservice;

import trapx00.lightx00.client.bl.commoditybl.CommodityBlController;

public class CommodityBlServiceFactory {
    private static  CommodityBlService instance =new CommodityBlController();
    /**
     * Gets a CommodityBlService instance.
     * @return CommodityBlService instance
     */
    public static CommodityBlService getInstance(){
        return instance;
    }
}

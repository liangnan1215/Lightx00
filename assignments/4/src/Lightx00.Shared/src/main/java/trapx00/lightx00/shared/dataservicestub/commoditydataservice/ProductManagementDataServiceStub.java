package trapx00.lightx00.shared.dataservicestub.commoditydataservice;

import trapx00.lightx00.shared.dataservice.commoditydataservice.ProductManagementDataService;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.po.inventorystaff.CommoditySortPo;

public class ProductManagementDataServiceStub implements ProductManagementDataService {
    String commodityIdList[]={"C0001","C0002"};
    String nextIds[]={"S0002"};
    String lowNextIds[]={""};

    @Override
    public ResultMessage add(CommoditySortPo newCSort, CommoditySortPo parentSort) {
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage modify(CommoditySortPo commoditySort) {
        return ResultMessage.Success;
    }

    @Override
    public CommoditySortPo[] query(String id, String name) {
        return new CommoditySortPo[]{
                new CommoditySortPo("S0001","Led", null,
                        "", nextIds)
        };
    }

    @Override
    public ResultMessage delete(CommoditySortPo commoditySort) {
        return ResultMessage.Success;
    }

    @Override
    public CommoditySortPo[] display() {
        return new CommoditySortPo[]{
                new CommoditySortPo("S0001","Led", null,
                        "", nextIds)
        };
    }

    @Override
    public CommoditySortPo[] dispaly(CommoditySortPo commoditySort) {
        return new CommoditySortPo[]{
                new CommoditySortPo("S0002","Led", null,
                        "", nextIds)
        };
    }

    @Override
    public void init() {

    }
}

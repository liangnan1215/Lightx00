package trapx00.lightx00.client.blservicestub.commodityblservice;

import trapx00.lightx00.client.blservice.commodityblservice.GoodsManagementBlService;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.vo.inventorystaff.CommodityVo;

import java.util.Date;

public class GoodsManagementBlServiceStub implements GoodsManagementBlService {

    Date date=new Date();

    @Override
    public ResultMessage add(CommodityVo newCommodity) {
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage modify(CommodityVo updateCommodity) {
        return ResultMessage.Success;
    }

    @Override
    public CommodityVo[] query(String id, String name) {
        return new CommodityVo[]{
            new CommodityVo("C0001", "LedLight", "Led", 98, date,
                    "1", "2", 56, 60, 90, 99, 50)
        };
    }

    @Override
    public ResultMessage delete(CommodityVo commodity) {
        return ResultMessage.Success;
    }
}

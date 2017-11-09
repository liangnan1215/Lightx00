package trapx00.lightx00.client.bl.commoditybl;

import trapx00.lightx00.shared.queryvo.CommodityQueryVo;
import trapx00.lightx00.client.vo.inventorystaff.CommodityVo;

public interface CommodityService {
    /**
     * Query a commodity
     * @param commodityQueryVo
     * @return Commodity that match query condition
     */
    CommodityVo[] queryCommodity(CommodityQueryVo commodityQueryVo);

}

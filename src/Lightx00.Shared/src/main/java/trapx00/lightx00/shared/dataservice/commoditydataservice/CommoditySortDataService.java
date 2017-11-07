package trapx00.lightx00.shared.dataservice.commoditydataservice;

import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.po.inventorystaff.CommoditySortPo;
import trapx00.lightx00.client.vo.inventorystaff.CommoditySortQueryVo;

public interface CommoditySortDataService {

    /**
     * Create a new commoditySort attaching to a parentSort
     * @param newCSort
     * @param parentSort
     * @return whether the operation is done successfully
     */
    ResultMessage add(CommoditySortPo newCSort, CommoditySortPo parentSort);//增加分类

    /**
     * Modify a commoditySort
     * @param commoditySort
     * @return whether the operation is done successfully
     */
    ResultMessage modify(CommoditySortPo commoditySort);//修改分类

    /**
     *  Query a commodifySort
     * @param commoditySortQueryVo
     * @return the list of the commoditySort
     */
    CommoditySortPo[] query(CommoditySortQueryVo commoditySortQueryVo);//查询

    /**
     *  Delete a commoditySort
     * @param commoditySort
     * @return whether the operation is done successfully
     */
    ResultMessage delete(CommoditySortPo commoditySort);//删除

    /**
     * Display all commoditySorts
     * @return the list of commoditySort
     */
    CommoditySortPo[] display();//

    /**
     *  Get the childsoft of a commoditySort
     * @param commoditySort
     * @return the childCommoditySort
     */
    CommoditySortPo[] dispaly(CommoditySortPo commoditySort);

    void init();

}
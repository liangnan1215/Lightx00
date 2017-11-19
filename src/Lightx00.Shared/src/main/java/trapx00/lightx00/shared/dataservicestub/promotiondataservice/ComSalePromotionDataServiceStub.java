package trapx00.lightx00.shared.dataservicestub.promotiondataservice;

import trapx00.lightx00.shared.dataservice.promotiondataservice.ComSalePromotionDataService;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.po.manager.promotion.ComSalePromotionPo;
import trapx00.lightx00.shared.po.manager.promotion.PromotionState;
import trapx00.lightx00.shared.queryvo.PromotionQueryVo;

import java.util.Date;

public class ComSalePromotionDataServiceStub implements ComSalePromotionDataService {
    /**
     * Submit a completed ComSalePromotionPo.
     * @param promotion the ComSalePromotionPo to be submitted
     * @return whether the operation is done successfully
     */
    public ResultMessage submit(ComSalePromotionPo promotion) {
        return ResultMessage.Success;
    }

    /**
     * Filter ComSalePromotionPo.
     * @param query the filter conditions
     * @return array of ComSalePromotionPo which match the conditions
     */
    public ComSalePromotionPo[] queryPromotion(PromotionQueryVo query) {
        return new ComSalePromotionPo[]{new ComSalePromotionPo("0001",new Date(),new Date(),PromotionState.Waiting,null,0)};
    }


    /**
     * Delete a useless ComSalePromotionPo.
     * @param promotion the ComSalePromotionPo to be deleted
     * @return whether the operation is done successfully
     */
    public ResultMessage delete(ComSalePromotionPo promotion) {
        return ResultMessage.Success;
    }

    /**
     * Get id for the next promotion.
     *
     * @return id for the next promotion
     */
    public String getId() {
        return "0001";
    }
}

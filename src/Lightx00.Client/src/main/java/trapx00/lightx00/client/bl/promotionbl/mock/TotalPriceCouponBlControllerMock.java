package trapx00.lightx00.client.bl.promotionbl.mock;

import trapx00.lightx00.client.bl.promotionbl.TotalPriceCouponBlController;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.client.vo.manager.promotion.PromotionQueryVo;
import trapx00.lightx00.client.vo.manager.promotion.TotalPriceCouponVo;

public class TotalPriceCouponBlControllerMock extends TotalPriceCouponBlController {
    /**
     * submit a TotalPriceCouponVo
     * @param newPromotion the TotalPriceCouponVo to be submitted
     * @return whether the operation is done successfully
     */
    public ResultMessage submit(TotalPriceCouponVo newPromotion) {
        return super.submit(newPromotion);
    }

    /**
     * save a half-completed TotalPriceCouponVo as a draft
     * @param promotion the TotalPriceCouponVo to be saved as a draft
     * @return whether the operation is done successfully
     */
    public ResultMessage saveAsDraft(TotalPriceCouponVo promotion) {
        return super.saveAsDraft(promotion);
    }

    /**
     * delete a overdue or needless TotalPriceCouponVo
     * @param promotion the TotalPriceCouponVo to be deleted
     * @return whether the operation is done successfully
     */
    public ResultMessage delete(TotalPriceCouponVo promotion) {
        return super.delete(promotion);
    }

    /**
     * filter some TotalPriceCouponVos
     * @param query the filter conditions
     * @return array of TotalPriceCouponVos which match the conditions
     */
    public TotalPriceCouponVo[] queryPromotion(PromotionQueryVo query) {
        return super.queryPromotion(query);
    }

    /**
     * get id for the next TotalPriceCouponVo
     * @return id for the next TotalPriceCouponVo
     */
    public String getId() {
        return super.getId();
    }
}
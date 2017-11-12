package trapx00.lightx00.client.bl.promotionbl.mock;

import trapx00.lightx00.client.bl.promotionbl.TotalPricePromotionBlController;
import trapx00.lightx00.client.vo.manager.promotion.TotalPricePromotionVo;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.po.manager.promotion.PromotionState;
import trapx00.lightx00.shared.queryvo.PromotionQueryVo;

import java.util.Date;

public class TotalPricePromotionBlControllerMock extends TotalPricePromotionBlController {
    /**
     * submit a TotalPricePromotionVo
     * @param promotion the TotalPricePromotionVo to be submitted
     * @return whether the operation is done successfully
     */
    public ResultMessage submit(TotalPricePromotionVo promotion) {
        return ResultMessage.Success;
    }

    /**
     * save a half-completed TotalPricePromotionVo as a draft
     * @param promotion the TotalPricePromotionVo to be saved as a draft
     * @return whether the operation is done successfully
     */
    public ResultMessage saveAsDraft(TotalPricePromotionVo promotion) {
        return ResultMessage.Success;
    }

    /**
     * filter TotalPricePromotionVo
     * @param query the filter conditions
     * @return array of TotalPricePromotionVo which match the conditions
     */
    public TotalPricePromotionVo[] queryPromotion(PromotionQueryVo query) {
        return new TotalPricePromotionVo[]{
                new TotalPricePromotionVo("0001",new Date(),new Date(), PromotionState.Waiting,200,1000,null)
        };
    }

    /**
     * delete a overdue or needless TotalPricePromotionVo
     * @param promotion the TotalPricePromotionVo to be deleted
     * @return whether the operation is done successfully
     */
    public ResultMessage delete(TotalPricePromotionVo promotion) {
        return ResultMessage.Success;
    }

    /**
     * get id for the next TotalPricePromotionVo
     * @return id for the next TotalPricePromotionVo
     */
    public String getId() {
        return super.getId();
    }

    /**
     * delete a draft
     * @param id id of the draft to be deleted
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage deleteDraft(String id) {
        return ResultMessage.Success;
    }

}

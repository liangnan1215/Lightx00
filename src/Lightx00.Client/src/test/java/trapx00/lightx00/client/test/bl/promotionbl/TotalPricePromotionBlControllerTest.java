package trapx00.lightx00.client.test.bl.promotionbl;

import org.junit.Test;
import trapx00.lightx00.client.bl.promotionbl.TotalPricePromotionBlController;
import trapx00.lightx00.client.bl.promotionbl.mock.TotalPricePromotionBlControllerMock;
import trapx00.lightx00.client.vo.manager.promotion.TotalPricePromotionVo;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.po.manager.promotion.PromotionState;

import java.util.Date;

import static org.junit.Assert.*;

public class TotalPricePromotionBlControllerTest {
    private TotalPricePromotionBlController controller = new TotalPricePromotionBlControllerMock();
    private TotalPricePromotionVo promotion = new TotalPricePromotionVo("0001",new Date(),new Date(), PromotionState.Waiting,200,1000,null);

    @Test
    public void submit() throws Exception {
        assertEquals(ResultMessage.Success,controller.submit(promotion));
    }

    @Test
    public void saveAsDraft() throws Exception {
        assertEquals(ResultMessage.Success,controller.saveAsDraft(promotion));
    }

    @Test
    public void queryPromotion() throws Exception {
        assertEquals("0001",controller.queryPromotion(x->true)[0].getId());
    }

    @Test
    public void delete() throws Exception {
        assertEquals(ResultMessage.Success,controller.delete(promotion));
    }

    @Test
    public void getId() throws Exception {
        assertEquals("0001",controller.getId());
    }

    @Test
    public void deleteDraft() throws Exception {
        assertEquals(ResultMessage.Success,controller.deleteDraft("0001"));
    }

}
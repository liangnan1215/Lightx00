package trapx00.lightx00.server.test.data.promotiondata;

import com.j256.ormlite.dao.Dao;
import org.junit.Before;
import org.junit.Test;
import trapx00.lightx00.server.data.promotiondata.factory.ClientPromotionDataFactory;
import trapx00.lightx00.server.data.promotiondata.factory.PromotionDataDaoFactory;
import trapx00.lightx00.server.data.util.db.BaseDatabaseFactory;
import trapx00.lightx00.shared.dataservice.promotiondataservice.ClientPromotionDataService;
import trapx00.lightx00.shared.exception.database.PromotionInvalidStateException;
import trapx00.lightx00.shared.po.manager.promotion.ClientPromotionPo;
import trapx00.lightx00.shared.po.manager.promotion.PromotionState;
import trapx00.lightx00.shared.queryvo.promotion.ClientPromotionQueryVo;
import trapx00.lightx00.shared.util.BillHelper;

import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientPromotionDataControllerTest {
    static {
        try {
            BaseDatabaseFactory.initTest();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Dao<ClientPromotionPo, String> dao = PromotionDataDaoFactory.getClientPromotionDao();
    private ClientPromotionDataService service = ClientPromotionDataFactory.getService();
    private final ClientPromotionPo promotion = new ClientPromotionPo("KHCXCL-20171112-00001",new Date(),new Date(), PromotionState.Draft,1,200,null,0);

    @Before
    public void setUp() throws Exception {
        dao.deleteById(promotion.getId());
    }

    @Test
    public void submitOne() throws Exception {
        try {
            service.submit(promotion);
            assertTrue(dao.idExists(promotion.getId()));
        } finally {
            dao.deleteById(promotion.getId());
        }

    }

    @Test
    public void deleteWaitingPromotion() throws Exception {
        try {
            promotion.setState(PromotionState.Waiting);
            dao.create(promotion);
            service.delete(promotion.getId());
            assertEquals(PromotionState.Abandoned, dao.queryForEq("id", promotion.getId()).get(0).getState());
        } finally {
            dao.deleteById(promotion.getId());
        }
    }

    @Test (expected = PromotionInvalidStateException.class)
    public void deleteActivePromotion() throws Exception {
        try {
            promotion.setState(PromotionState.Active);
            dao.create(promotion);
            service.delete(promotion.getId());
        } finally {
            dao.deleteById(promotion.getId());
        }
    }

    @Test
    public void deleteOverduePromotion() throws Exception {
        try {
            promotion.setState(PromotionState.Overdue);
            dao.create(promotion);
            service.delete(promotion.getId());
            assertEquals(PromotionState.Abandoned, dao.queryForEq("id", promotion.getId()).get(0).getState());
        } finally {
            dao.deleteById(promotion.getId());
        }
    }

    @Test
    public void query() throws Exception {
        try {
            dao.create(promotion);
            assertEquals(1, service.queryPromotion(new ClientPromotionQueryVo().eq("id", promotion.getId())).length);
            assertEquals(0, service.queryPromotion(new ClientPromotionQueryVo().eq("id", "12")).length);
        } finally {
            dao.deleteById(promotion.getId());
        }
    }

    @Test
    public void getId() throws Exception {
        try {
            promotion.setId(String.format("KHCXCL-%s-00001", BillHelper.currentDateStringForBill()));
            service.submit(promotion);
            assertEquals("00002", service.getId().split("-")[2]);
        } finally {
            dao.deleteById(promotion.getId());
        }
    }

}
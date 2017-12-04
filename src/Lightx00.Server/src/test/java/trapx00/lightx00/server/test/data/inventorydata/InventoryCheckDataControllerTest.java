package trapx00.lightx00.server.test.data.inventorydata;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import org.junit.Test;
import trapx00.lightx00.server.data.util.db.BaseDatabaseFactory;
import trapx00.lightx00.shared.queryvo.InventoryPictureQueryVo;
import trapx00.lightx00.shared.queryvo.InventoryViewQueryVo;

import java.sql.SQLException;
import java.util.Date;

public class InventoryCheckDataControllerTest {

    static {
        try {
            BaseDatabaseFactory.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Dao<InventoryViewPo, String> inventoryViewDao = InventoryOtherDataDaoFactory.getInventoryViewDao();
    private Dao<InventoryPicturePo,String> inventoryPictureDao=InventoryOtherDataDaoFactory.getInventoryPictureDao();
    private InventoryCheckDataService inventoryCheckDataService=InventoryCheckDataFactory.getService();
    private InventoryViewPo inventoryViewPo=new InventoryViewPo("VIEW-0001-0001",new Date(),null);
    private InventoryPicturePo inventoryPicturePo=new InventoryPicturePo("PICT-0001-0001",new Date(),null);

    private void resetTable1() throws Exception {
        TableUtils.dropTable(inventoryViewDao.getConnectionSource(),InventoryViewPo.class,true);
        TableUtils.createTable(inventoryViewDao.getConnectionSource(), InventoryViewPo.class);

    }
    private void resetTable2()throws Exception{
        TableUtils.dropTable(inventoryPictureDao.getConnectionSource(),InventoryPicturePo.class,true);
        TableUtils.createTable(inventoryPictureDao.getConnectionSource(), InventoryPicturePo.class);
    }

    @Test
    public void picture() throws Exception {
        inventoryPictureDao.create(inventoryPicturePo);
        assertEquals(1, inventoryCheckDataService.getInventoryPicture(new
                InventoryPictureQueryVo().eq("id","PICT-0001-0001")).length);
        resetTable2();
    }

    @Test
    public void view() throws Exception {
        inventoryViewDao.create(inventoryViewPo);
        assertEquals(1, inventoryCheckDataService.getInventoryView(new InventoryViewQueryVo().
                eq("id","VIEW-0001-0001")).length);
        resetTable1();
    }

}
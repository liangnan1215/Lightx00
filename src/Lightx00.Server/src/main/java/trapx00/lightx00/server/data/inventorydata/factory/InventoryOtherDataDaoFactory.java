package trapx00.lightx00.server.data.inventorydata.factory;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import trapx00.lightx00.server.data.util.db.BaseDatabaseFactory;
import trapx00.lightx00.shared.po.inventorystaff.InventoryDetailBillPo;
import trapx00.lightx00.shared.po.inventorystaff.InventoryGiftPo;
import trapx00.lightx00.shared.po.inventorystaff.InventoryPicturePo;
import trapx00.lightx00.shared.po.inventorystaff.InventoryViewPo;

import java.sql.SQLException;

public class InventoryOtherDataDaoFactory  extends BaseDatabaseFactory{

    static {

        initTable(InventoryViewPo.class);
        initTable(InventoryPicturePo.class);
    }


    private static Dao<InventoryViewPo,String> inventoryViewDao;
    private static Dao<InventoryPicturePo, String> inventoryPictureDao;





    public static Dao<InventoryViewPo, String> getInventoryViewDao() {
        if (inventoryViewDao == null) {
            try {
                inventoryViewDao = DaoManager.createDao(connectionSource, InventoryViewPo.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return inventoryViewDao;
    }

    public static Dao<InventoryPicturePo, String> getInventoryPictureDao() {
        if (inventoryPictureDao == null) {
            try {
                inventoryPictureDao = DaoManager.createDao(connectionSource, InventoryPicturePo.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return inventoryPictureDao;
    }
}
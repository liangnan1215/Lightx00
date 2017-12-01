package trapx00.lightx00.server.data.saledata.factory;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import trapx00.lightx00.server.data.util.db.BaseDatabaseFactory;
import trapx00.lightx00.shared.po.salestaff.SaleBillPo;
import trapx00.lightx00.shared.po.salestaff.SaleRefundBillPo;

import java.sql.SQLException;

public class SaleRefundBillDataDaoFactory extends BaseDatabaseFactory {
    private static Dao<SaleRefundBillPo, String> saleRefundBillDao;


    public static Dao<SaleRefundBillPo, String> getSaleRefundBillDao() {
        if (saleRefundBillDao == null) {
            saleRefundBillDao = createDao(SaleRefundBillPo.class);
        }
        return saleRefundBillDao;
    }
}

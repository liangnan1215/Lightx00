package trapx00.lightx00.server.data.notificationdata.factory;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import trapx00.lightx00.server.data.util.db.BaseDatabaseFactory;
import trapx00.lightx00.shared.po.notification.NotificationPo;

import java.sql.SQLException;

public class NotificationDataDaoFactory extends BaseDatabaseFactory {
    private static Dao<NotificationPo, Integer> dao;

    public static Dao<NotificationPo, Integer> getDao() {
        if (dao == null) {
            dao = createDao(NotificationPo.class);
        }
        return dao;
    }
}

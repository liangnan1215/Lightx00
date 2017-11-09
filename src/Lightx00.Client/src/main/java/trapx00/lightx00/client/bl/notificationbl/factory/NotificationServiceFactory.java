package trapx00.lightx00.client.bl.notificationbl.factory;

import trapx00.lightx00.client.bl.notificationbl.NotificationBlController;
import trapx00.lightx00.client.bl.notificationbl.NotificationService;

public class NotificationServiceFactory {
    private static NotificationService instance;

    /**
     * Gets a NotificationService instance
     * @return a NotificationService instance
     */
    public static NotificationService getInstance() {
        return instance;
    }
}

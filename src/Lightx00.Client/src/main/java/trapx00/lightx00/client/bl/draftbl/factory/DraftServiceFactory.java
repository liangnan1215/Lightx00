package trapx00.lightx00.client.bl.draftbl.factory;

import trapx00.lightx00.client.bl.draftbl.DraftBlController;
import trapx00.lightx00.client.bl.draftbl.DraftService;
import trapx00.lightx00.client.bl.draftbl.mock.DraftBlControllerMock;

public class DraftServiceFactory {
    private static DraftBlController controller = new DraftBlControllerMock();

    private static DraftService draftService = controller;

    /**
     * Gets a DraftService instance.
     * @return DraftService instance
     */
    public static DraftService getDraftService() {
        return draftService;
    }

    public static DraftBlController getController() {
        return controller;
    }
}

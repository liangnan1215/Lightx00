package trapx00.lightx00.server.data.financedata.mock;

import trapx00.lightx00.server.data.financedata.InitialEstablishmentDataController;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.po.financestaff.SystemSnapshotPo;
import trapx00.lightx00.shared.queryvo.SystemSnapshotQueryVo;

public class InitialEstablishmentDataControllerMock extends InitialEstablishmentDataController {
    /**
     * Submits a snapshot.
     *
     * @param snapshot snapshot to be submitted
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage submit(SystemSnapshotPo snapshot) {
        return super.submit(snapshot);
    }

    /**
     * Deletes a draft.
     *
     * @param id id for a draft
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage abandon(String id) {
        return super.abandon(id);
    }

    /**
     * Gets the id for the next snapshot.
     *
     * @return id for the next snapshot
     */
    @Override
    public String getId() {
        return super.getId();
    }

    /**
     * Queries SystemSnapshot.
     *
     * @param query query
     * @return SystemSnapshotVos that match the condition
     */
    @Override
    public SystemSnapshotPo[] query(SystemSnapshotQueryVo query) {
        return super.query(query);
    }
}
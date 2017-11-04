package trapx00.lightx00.client.bl.financebl;

import trapx00.lightx00.client.blservice.financeblservice.InitialEstablishmentBlService;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.vo.financestaff.SystemSnapshotVo;

public class InitialEstablishmentBlController implements InitialEstablishmentBlService {

    /**
     * Auto fills some contents with current system state.
     *
     * @return SystemSnapshot with current system state
     */
    @Override
    public SystemSnapshotVo autofill() {
        return null;
    }

    /**
     * Submits the system snapshot.
     *
     * @param snapshot SystemSnapVo to be submitted
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage submit(SystemSnapshotVo snapshot) {
        return null;
    }

    /**
     * Saves a half-completed system snapshot as a draft.
     *
     * @param snapshot SystemSnapshotVo to be saved as draft
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage saveAsDraft(SystemSnapshotVo snapshot) {
        return null;
    }
}

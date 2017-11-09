package trapx00.lightx00.client.bl.draftbl;

import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.client.vo.Draftable;

public interface DraftService {
    /**
     * Saves a draftable as a draft
     * @param draft draft
     * @return whether operation is done successfully
     */
    ResultMessage saveAsDraft(Draftable draft);
}

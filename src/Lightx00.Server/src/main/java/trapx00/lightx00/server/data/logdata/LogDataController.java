package trapx00.lightx00.server.data.logdata;

import trapx00.lightx00.shared.dataservice.logdataservice.LogDataService;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.po.log.LogPo;
import trapx00.lightx00.shared.po.log.LogSeverity;
import trapx00.lightx00.shared.queryvo.LogQueryVo;

public class LogDataController implements LogDataService {
    /**
     * Writes log.
     *
     * @param severity Log severity
     * @param content  content
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage log(LogSeverity severity, String content) {
        return null;
    }

    /**
     * Queries log.
     *
     * @param query query
     * @return LogVos that match query condition
     */
    @Override
    public LogPo[] query(LogQueryVo query) {
        return new LogPo[0];
    }
}

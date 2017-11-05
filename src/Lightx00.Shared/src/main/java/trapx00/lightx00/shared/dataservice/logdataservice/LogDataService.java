package trapx00.lightx00.shared.dataservice.logdataservice;

import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.po.log.LogSeverity;
import trapx00.lightx00.shared.vo.log.LogQueryVo;
import trapx00.lightx00.shared.vo.log.LogVo;

public interface LogDataService {

    /**
     * Writes log.
     * @param severity Log severity
     * @param content content
     * @return whether the operation is done successfully
     */
    ResultMessage log(LogSeverity severity, String content);

    /**
     * Queries log.
     * @param query query
     * @return LogVos that match query condition
     */
    LogVo[] query(LogQueryVo query);
}

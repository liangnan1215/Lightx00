package trapx00.lightx00.client.blservicestub.financeblservice;

import trapx00.lightx00.client.blservice.financeblservice.TradeSituationBlService;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.vo.financestaff.TradeSituationVo;

import java.util.Date;

public class TradeSituationBlServiceStub implements TradeSituationBlService {
    @Override
    public TradeSituationVo query(Date start, Date end) {
        return new TradeSituationVo(123,123,123,123,123);
    }

    @Override
    public ResultMessage export(TradeSituationVo situation) {
        return ResultMessage.Success;
    }
}

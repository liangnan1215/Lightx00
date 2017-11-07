package trapx00.lightx00.shared.dataservicestub.bankaccountdataservice;

import trapx00.lightx00.shared.dataservice.bankaccountdataservice.BankAccountDataService;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.shared.po.financestaff.BankAccountPo;
import trapx00.lightx00.client.vo.financestaff.BankAccountQueryVo;

import java.util.Date;

public class BankAccountDataServiceStub implements BankAccountDataService {
    @Override
    public BankAccountPo[] query(BankAccountQueryVo query) {
        return new BankAccountPo[] {
                new BankAccountPo("123","123",0,new Date())
        };
    }

    @Override
    public ResultMessage add(BankAccountPo account) {
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage modify(BankAccountPo account) {
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage delete(BankAccountPo account) {
        return ResultMessage.Success;
    }
}

package trapx00.lightx00.shared.po.financestaff;

import java.util.Date;
import trapx00.lightx00.shared.po.bill.BillState;


public class  ReceivalBillPo extends ReceivalPaymentBillPoBase {
    public ReceivalBillPo(String id, Date date, BillState state,
                          String clientId, String operatorId, Transcation[] transcations, double total) {
        super(id, date, state, FinanceBillType.ReceivalBill, clientId, operatorId, transcations, total);
    }
}

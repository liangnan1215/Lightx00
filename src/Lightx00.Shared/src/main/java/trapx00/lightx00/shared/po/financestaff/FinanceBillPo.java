package trapx00.lightx00.shared.po.financestaff;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import trapx00.lightx00.shared.po.bill.BillPo;
import trapx00.lightx00.shared.po.bill.BillState;
import trapx00.lightx00.shared.po.bill.BillType;


public class FinanceBillPo extends BillPo {
    @DatabaseField
    private FinanceBillType financeBillType;


    public FinanceBillType getFinanceBillType() {
        return financeBillType;
    }


    public FinanceBillPo(String id, Date date, BillState state, FinanceBillType financeBillType, String operatorId) {
        super(BillType.FinanceBill, id, date, state, operatorId);
        this.financeBillType = financeBillType;
    }

    public FinanceBillPo() {
    }

    @Override
    public String toString() {
        return "FinanceBillPo{" +
            "financeBillType=" + financeBillType +
            "} " + super.toString();
    }
}

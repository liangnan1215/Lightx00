package trapx00.lightx00.client.bl.approvalbl;

import trapx00.lightx00.client.blservice.approvalblservice.AuditBlService;
import trapx00.lightx00.shared.po.ResultMessage;
import trapx00.lightx00.client.vo.BillVo;
import trapx00.lightx00.client.vo.manager.BillQueryVo;

public class AuditBlController implements AuditBlService,ApprovalRequest {

    /**
     * filter some bills to audit
     * @param query the filter conditions
     * @return array of bills whitch match the conditions
     */
    @Override
    public BillVo[] query(BillQueryVo query) {
        return new BillVo[0];
    }

    /**
     * reject to approve the bill
     * @param bill bill to be audited
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage reject(BillVo bill) {
        return null;
    }

    /**
     * approve the bill
     * @param bill bill to be audited
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage pass(BillVo bill) {
        return null;
    }

    /**
     * submitted bills request approval
     * @param bill bill has been submitted
     * @return whether the operation is done successfully
     */
    @Override
    public ResultMessage requestMessage(BillVo bill) {
        return null;
    }
}
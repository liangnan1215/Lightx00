package trapx00.lightx00.client.presentation.bankaccountui;

import trapx00.lightx00.client.vo.financestaff.BankAccountVo;

public class BankAccountUiController implements BankAccountInfoUi {
    /**
     * Shows a dialog for selecting dialog and returns what have been chosen.
     *
     * @return Shows a dialog for selecting dialog and returns what have been chosen.
     */
    @Override
    public BankAccountVo[] showBankAccountSelectDialog() {
        return new BankAccountVo[0];
    }
}
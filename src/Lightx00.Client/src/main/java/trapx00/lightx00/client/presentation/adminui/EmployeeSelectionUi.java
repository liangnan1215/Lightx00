package trapx00.lightx00.client.presentation.adminui;

import trapx00.lightx00.client.presentation.helpui.ExternalLoadedUiPackage;
import trapx00.lightx00.client.presentation.helpui.SelectingDialog;
import trapx00.lightx00.client.vo.EmployeeVo;

import java.util.function.Consumer;

public class EmployeeSelectionUi extends SelectingDialog implements EmployeeSelection {
    @Override
    public void showEmployeeSelectDialog(Consumer<EmployeeVo> callback) {

    }

    @Override
    public EmployeeVo queryId(int id) {
        return null;
    }

    @Override
    public ExternalLoadedUiPackage load() {
        return null;
    }
}

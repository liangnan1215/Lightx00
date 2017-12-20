package trapx00.lightx00.client.presentation.loginui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import trapx00.lightx00.client.Client;
import trapx00.lightx00.client.presentation.helpui.ExternalLoadedUiPackage;
import trapx00.lightx00.client.presentation.helpui.FrameworkUiManager;
import trapx00.lightx00.client.presentation.helpui.StageManager;
import trapx00.lightx00.client.presentation.helpui.UiLoader;
import trapx00.lightx00.client.presentation.mainui.FrameworkUiController;
import trapx00.lightx00.client.vo.EmployeeVo;
import trapx00.lightx00.shared.po.employee.EmployeePosition;

import java.io.IOException;

public class FinishLoginLogic {
    public static void finishLogin(EmployeeVo employeeVo) {
        try {
            FrameworkUiManager.setCurrentEmployee(employeeVo);
            StageManager.closeStage();
            Stage newStage = new Stage();

            UiLoader loader = new UiLoader(selectUrl(employeeVo.getPosition()));

            Scene scene = new Scene(loader.load());


            FrameworkUiController controller = loader.getController();

            FrameworkUiManager.setFrameworkUiController(controller);


            newStage.initStyle(StageStyle.UNDECORATED);

            newStage.setScene(scene);
            newStage.setHeight(900);
            newStage.setWidth(1600);

            controller.setStage(newStage);
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String selectUrl(EmployeePosition position) {
        switch (position) {
            case InventoryStaff:
                return "/fxml/mainui/InventoryStaffUI.fxml";
            case Manager:
            case Admin:
            case SaleStaff:
                return "/fxml/mainui/SaleStaffUi.fxml";
            case FinanceStaff:
                return "/fxml/mainui/FinanceStaffUi.fxml";
            default:
                return null;
        }
    }

}

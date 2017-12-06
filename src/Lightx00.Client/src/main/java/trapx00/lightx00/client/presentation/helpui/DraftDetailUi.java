package trapx00.lightx00.client.presentation.helpui;

import trapx00.lightx00.client.vo.Draftable;

public abstract class DraftDetailUi implements ContentDisplayUi<Draftable>, ExternalLoadableUiController {
    protected void onClose() {
        FrameworkUiManager.getCurrentDialogStack().closeCurrentAndPopAndShowNext();
    }
}

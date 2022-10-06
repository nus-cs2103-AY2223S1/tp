package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.module.Module;

public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "ModuleListCard.fxml";
    @FXML
    private Label id;

    @FXML
    private Label moduleCode;

    public ModuleCard(Module module, int position) {
        super(FXML);
        id.setText(position + ". ");
        moduleCode.setText(module.getModuleCode().moduleCode);
    }
}

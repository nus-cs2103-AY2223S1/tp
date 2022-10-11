package nus.climods.ui.module;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import nus.climods.model.module.Module;
import nus.climods.ui.UiPart;
import nus.climods.ui.common.Pill;

/**
 * An UI component that displays information of a {@code Module}.
 */
public class SavedModuleCard extends UiPart<Region> {

    private static final String FXML = "SavedModuleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As a consequence, UI
     * elements' variable names cannot be set to such keywords or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private static final String AY_SEMESTER_BG_COLOR = "#E5C07B";

    private static final String AY_SEMESTER_TEXT_COLOR = "#2E333D";

    public final Module module;

    @FXML
    private HBox cardPane;
    @FXML
    private Label moduleCode;
    @FXML
    private Label tutorial;
    @FXML
    private Label lecture;
    @FXML
    private FlowPane ayData;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public SavedModuleCard(Module module) {
        super(FXML);
        this.module = module;
        moduleCode.setText(module.getCode());
        tutorial.setText("TBI");
        lecture.setText("TBI");
        ayData.getChildren().add(new Pill("TBI", AY_SEMESTER_BG_COLOR, AY_SEMESTER_TEXT_COLOR));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SavedModuleCard)) {
            return false;
        }

        // state check
        SavedModuleCard card = (SavedModuleCard) other;
        return moduleCode.getText().equals(card.moduleCode.getText())
            && module.equals(card.module);
    }
}

package nus.climods.ui.module;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import nus.climods.model.module.DummyModule;
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

    public final DummyModule module;

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
    public SavedModuleCard(DummyModule module) {
        super(FXML);
        this.module = module;
        moduleCode.setText(module.getModuleCode());
        tutorial.setText(module.getTutorial());
        lecture.setText(module.getLecture());
        ayData.getChildren().add(new Pill(module.getAcademicYear(), "#E5C07B", "#2E333D"));
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

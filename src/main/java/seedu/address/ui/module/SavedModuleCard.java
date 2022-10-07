package seedu.address.ui.module;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * An UI component that displays information of a {@code Module}.
 */
public class SavedModuleCard extends UiPart<Region> {

    private static final String FXML = "SavedModuleListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

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

    private String buttonStyle = "-fx-border-width:0;-fx-background-radius: 10px;-fx-padding: 3;"
           + "-fx-font-size: 8;-fx-font-weight:bold;";

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public SavedModuleCard(Module module) {
        super(FXML);
        this.module = module;
        moduleCode.setText(module.getModuleCode());
        tutorial.setText(module.getTutorial());
        lecture.setText(module.getLecture());
        // TODO: Find a better display for the buttons
        ayData.getChildren().add(new Button(module.getaydata()));
        ayData.getChildren().forEach(child -> child.setStyle("-fx-background-color:#E5C07B;-fx-text-fill: #2E333D;"
                + buttonStyle));

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

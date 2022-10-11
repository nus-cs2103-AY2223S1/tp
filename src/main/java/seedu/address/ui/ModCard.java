package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.SubjectModule;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class ModCard extends UiPart<Region> {

    private static final String FXML = "ModListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final SubjectModule module;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label modName;
    @FXML
    private Label isTaking;

    /**
     * Creates a {@code ModCode} with the given {@code Mod} and index to display.
     */
    public ModCard(SubjectModule module, int displayedIndex) {
        super(FXML);
        this.module = module;
        id.setText(displayedIndex + ". ");
        modName.setText(module.getModName().moduleName);
        if (module.getTakingStatus()) {
            isTaking.setText("Taking");
        } else {
            isTaking.setText("Taken");
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModCard)) {
            return false;
        }

        // state check
        ModCard card = (ModCard) other;
        return id.getText().equals(card.id.getText())
                && this.module.equals(card.module);
    }
}

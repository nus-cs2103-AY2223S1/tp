package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Mod;

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

    public final Mod module;

    @FXML
    private HBox cardPane;
    @FXML
    private Label modId;
    @FXML
    private Label modName;
    @FXML
    private Label modCategory;
    @FXML
    private Label takenStatus;

    /**
     * Creates a {@code ModCode} with the given {@code Mod} and index to display.
     */
    public ModCard(Mod module, int displayedIndex) {
        super(FXML);
        this.module = module;
        modId.setText(displayedIndex + ". ");
        modName.setText(module.getModName());

        Mod.ModCategory modCat = module.getModCategory();
        modCategory.setText(modCat.toString());

        // set color of category tag
        if (modCat.equals(Mod.ModCategory.COMP)) {
            modCategory.setStyle("-fx-background-color: #b32436;");
        } else if (modCat.equals(Mod.ModCategory.MATH)) {
            modCategory.setStyle("-fx-background-color: #d7a006;");
        } else if (modCat.equals(Mod.ModCategory.SCI)) {
            modCategory.setStyle("-fx-background-color: #0F9D58;");
        } else if (modCat.equals(Mod.ModCategory.COMMS)) {
            modCategory.setStyle("-fx-background-color: #185abd;");
        } else if (modCat.equals(Mod.ModCategory.GE)) {
            modCategory.setStyle("-fx-background-color: #ce532f;");
        } else if (modCat.equals(Mod.ModCategory.UE)) {
            modCategory.setStyle("-fx-background-color: #7e49d5;");
        }

        boolean hasTaken = module.getModStatus();
        if (hasTaken) {
            takenStatus.setStyle("-fx-background-color: #00b400;");
            takenStatus.setText("Taken");
        } else {
            takenStatus.setStyle("-fx-background-color: #adadad;");
            takenStatus.setText("Taking");
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
        return modId.getText().equals(card.modId.getText())
                && this.module.equals(card.module);
    }
}

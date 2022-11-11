package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.group.Group;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class GroupCard extends UiPart<Region> {

    private static final String FXML = "GroupListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     *      issue on AddressBook level 4</a>
     */

    public final Group grp;

    @FXML
    private HBox cardPane;
    @FXML
    private VBox parentContainer;
    @FXML
    private Label name;
    @FXML
    private Label id;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to
     * display.
     */
    public GroupCard(Group grp, int displayedIndex) {
        super(FXML);
        this.grp = grp;
        id.setText(displayedIndex + ". ");
        name.setText(grp.getFullPath());
        grp.getAttributes().forEach(attr -> parentContainer.getChildren().add(attr.getJavaFxRepresentation()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupCard)) {
            return false;
        }

        // state check
        GroupCard card = (GroupCard) other;
        return id.getText().equals(card.id.getText())
                && grp.equals(card.grp);
    }
}

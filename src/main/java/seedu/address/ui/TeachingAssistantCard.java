package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.ta.TeachingAssistant;

/**
 * An UI component that displays information of a {@code TeachingAssistant}.
 */
public class TeachingAssistantCard extends UiPart<Region> {

    private static final String FXML = "TeachingAssistantListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final TeachingAssistant teachingAssistant;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label stuId;
    @FXML
    private Label id;

    public TeachingAssistantCard(TeachingAssistant teachingAssistant, int displayedIndex) {
        super(FXML);
        this.teachingAssistant = teachingAssistant;
        id.setText(displayedIndex + ". ");
        name.setText(teachingAssistant.getName().fullName);
        stuId.setText(teachingAssistant.getId().id);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TeachingAssistantCard)) {
            return false;
        }

        // state check
        TeachingAssistantCard card = (TeachingAssistantCard) other;
        return id.getText().equals(card.id.getText())
                && teachingAssistant.equals(card.teachingAssistant);
    }
}

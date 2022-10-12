package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.person.tutor.Tutor;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class TutorCard extends PersonCard {

    private static final String FXML = "TutorCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Tutor tutor;
    @FXML
    protected Label institution;
    @FXML
    protected Label qualification;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TutorCard(Tutor tutor, int displayedIndex) {
        super(tutor, displayedIndex, FXML);
        this.tutor = tutor;
        institution.setText("Institution: " + tutor.getInstitution().institution);
        qualification.setText("Qualification: " + tutor.getQualification().qualification);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TutorCard)) {
            return false;
        }

        // state check
        TutorCard card = (TutorCard) other;
        return id.getText().equals(card.id.getText())
                && tutor.equals(card.tutor);
    }
}

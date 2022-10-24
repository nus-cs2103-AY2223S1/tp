package seedu.uninurse.ui;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.PatientPair;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class RedoCard extends UiPart<Region> {

    private static final String FXML = "RedoCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on UninurseBook level 4</a>
     */

    public final Optional<Patient> originalPatient;
    public final Optional<Patient> updatedPatient;

    @FXML
    private Label oldlabel;
    @FXML
    private Label newlabel;
    @FXML
    private StackPane oldPersonPlaceholder;
    @FXML
    private StackPane newPersonPlaceholder;
    @FXML
    private Separator horizontalSeparator;

    /**
     * Creates a RedoCard with {@code patientPair}.
     */
    public RedoCard(PatientPair patientPair) {
        super(FXML);
        originalPatient = patientPair.getOriginalPatient();
        updatedPatient = patientPair.getUpdatedPatient();
        oldlabel.setText("Original Patient:");
        newlabel.setText("Updated Patient:");

        if (originalPatient.isPresent()) {
            oldPersonPlaceholder.getChildren().add(new UpdatedPatientCard(originalPatient.get(), "").getRoot());
        } else {
            oldPersonPlaceholder.getChildren().add(new Label("DELETED"));
        }

        if (updatedPatient.isPresent()) {
            newPersonPlaceholder.getChildren().add(new UpdatedPatientCard(updatedPatient.get(), "").getRoot());
        } else {
            newPersonPlaceholder.getChildren().add(new Label("DELETED"));
        }
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UndoCard)) {
            return false;
        }

        // state check
        RedoCard card = (RedoCard) other;
        return originalPatient.equals(card.originalPatient)
                && updatedPatient.equals(card.updatedPatient);
    }
}

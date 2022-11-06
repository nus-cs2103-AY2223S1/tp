package seedu.uninurse.ui;

import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import seedu.uninurse.model.PersonListTracker;
import seedu.uninurse.model.person.Person;

/**
 * An UI component that displays information of an undone command.
 */
public class UndoCard extends UiPart<Region> {

    private static final String FXML = "UndoCard.fxml";
    private static final String RED_STYLE = "-fx-background-color: #ffc0bf;"
            + "-fx-border-radius: 2;"
            // + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);"
            + "-fx-background-radius: 5;"
            + "-fx-padding: 2;";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on UninurseBook level 4</a>
     */

    public final Optional<List<Person>> originalPersons;
    public final Optional<List<Person>> updatedPersons;

    @FXML
    private Label oldlabel;
    @FXML
    private Label newlabel;
    @FXML
    private VBox oldlabelPane;
    @FXML
    private VBox newlabelPane;
    @FXML
    private StackPane oldPersonPlaceholder;
    @FXML
    private StackPane newPersonPlaceholder;
    @FXML
    private Separator horizontalSeparator;

    /**
     * Creates a UndoCard with {@code personListTracker}.
     */
    public UndoCard(PersonListTracker personListTracker) {
        super(FXML);
        updatedPersons = personListTracker.getAddedPersons();
        originalPersons = personListTracker.getDeletedPersons();

        oldlabel.setText("Original Patients:");
        newlabel.setText("Updated Patients:");
        if (originalPersons.isPresent()) {
            oldPersonPlaceholder.getChildren().add(new UpdatedPersonListPanel(
                    FXCollections.observableList(originalPersons.get())).getRoot());
        } else {
            oldPersonPlaceholder.getChildren().add(new Label("DELETED"));
            oldPersonPlaceholder.setStyle(RED_STYLE);
        }
        if (updatedPersons.isPresent()) {
            newPersonPlaceholder.getChildren().add(new UpdatedPersonListPanel(
                    FXCollections.observableList(updatedPersons.get())).getRoot());
        } else {
            newPersonPlaceholder.getChildren().add(new Label("DELETED"));
            newPersonPlaceholder.setStyle(RED_STYLE);
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
        UndoCard card = (UndoCard) other;
        return originalPersons.equals(card.originalPersons)
                && updatedPersons.equals(card.updatedPersons);
    }

}

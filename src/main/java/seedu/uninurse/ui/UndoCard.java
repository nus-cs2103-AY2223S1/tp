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
     * Creates a UndoCard with personListTracker.
     */
    public UndoCard(PersonListTracker personListTracker) {
        super(FXML);
        this.updatedPersons = personListTracker.getAddedPersons();
        this.originalPersons = personListTracker.getDeletedPersons();

        oldlabel.setText("Original Patients:");
        newlabel.setText("Updated Patients:");

        if (originalPersons.isPresent()) {
            oldPersonPlaceholder.getChildren().add(new UpdatedPersonListPanel(
                    FXCollections.observableList(originalPersons.get())).getRoot());
        } else {
            oldPersonPlaceholder.getChildren().add(new Label("DELETED"));
            oldPersonPlaceholder.setId("red_bordered_box");
        }
        if (updatedPersons.isPresent()) {
            newPersonPlaceholder.getChildren().add(new UpdatedPersonListPanel(
                    FXCollections.observableList(updatedPersons.get())).getRoot());
        } else {
            newPersonPlaceholder.getChildren().add(new Label("DELETED"));
            newPersonPlaceholder.setId("red_bordered_box");
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
        UndoCard o = (UndoCard) other;
        return originalPersons.equals(o.originalPersons)
                && updatedPersons.equals(o.updatedPersons);
    }

}

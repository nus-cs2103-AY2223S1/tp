package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.address.model.person.Patient;

/**
 *
 */
public class OutputPanel extends UiPart<Region> {
    private static final String FXML = "OutputPanel.fxml";

    @FXML
    private StackPane output;

    /**
     * Creates a {@code OutputPanel} with the given {@code OutputPanel} to display.
     */
    public OutputPanel() {
        super(FXML);

    }

    public void clear() {
        output.getChildren().clear();
    }

    /**
     * Updates the output panel accordingly if commandResult is task related.
     * @param patient patient of interest.
     */
    public void handleTask(Patient patient) {
        output.getChildren().clear();
        output.getChildren().add(new TaskListCard(patient.getTasks().toString()).getRoot());
    }

    /**
     * Updates the output panel accordingly if executed command is AddPatient.
     * @param patient patient of interest.
     */
    public void handleAddPatient(Patient patient) {
        output.getChildren().clear();
        output.getChildren().add(new UpdatedPersonCard(patient, "Added Patient:").getRoot());
    }

    /**
     * Updates the output panel accordingly if executed command is EditPatient.
     * @param patient patient of interest.
     */
    public void handleEditPatient(Patient patient) {
        output.getChildren().clear();
        output.getChildren().add(new UpdatedPersonCard(patient, "Edited Patient:").getRoot());
    }

    /**
     * Updates the output panel accordingly if executed command is DeletePatient.
     * @param patient patient of interest.
     */
    public void handleDeletePatient(Patient patient) {
        output.getChildren().clear();
        output.getChildren().add(new UpdatedPersonCard(patient, "Deleted Patient:").getRoot());
    }


}

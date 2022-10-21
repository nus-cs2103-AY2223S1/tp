package seedu.uninurse.ui;


import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.uninurse.model.person.Patient;

/**
 *
 */
public class OutputPanel extends UiPart<Region> {
    private static final String FXML = "OutputPanel.fxml";

    @FXML
    private StackPane outputView;

    /**
     * Creates a {@code OutputPanel} with the given {@code OutputPanel} to display.
     */
    public OutputPanel() {
        super(FXML);

    }

    public void clear() {
        outputView.getChildren().clear();
    }

    /**
     * Updates the outputView panel accordingly if commandResult is task related.
     * @param patient patient of interest.
     */
    public void handleTask(Patient patient) {
        outputView.getChildren().clear();
        outputView.getChildren().add(new TaskListPanel(patient).getRoot());
    }

    /**
     * Updates the outputView panel accordingly if executed command is AddPatient.
     * @param patient patient of interest.
     */
    public void handleAddPatient(Patient patient) {
        outputView.getChildren().clear();
        outputView.getChildren().add(new UpdatedPersonCard(patient, "Added Patient:").getRoot());
    }

    /**
     * Updates the outputView panel accordingly if executed command is EditPatient.
     * @param patient patient of interest.
     */
    public void handleEditPatient(Patient patient) {
        outputView.getChildren().clear();
        outputView.getChildren().add(new UpdatedPersonCard(patient, "Edited Patient:").getRoot());
    }

    /**
     * Updates the outputView panel accordingly if executed command is DeletePatient.
     * @param patient patient of interest.
     */
    public void handleDeletePatient(Patient patient) {
        outputView.getChildren().clear();
        outputView.getChildren().add(new UpdatedPersonCard(patient, "Deleted Patient:").getRoot());
    }




}

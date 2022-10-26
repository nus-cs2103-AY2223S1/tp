package seedu.uninurse.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.uninurse.model.PatientListTracker;
import seedu.uninurse.model.Schedule;
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
     * Updates the outputView panel accordingly with a schedule if commandResult is schedule related.
     */
    public void handleSchedule(Schedule schedule) {
        ScheduleListPanel scheduleListPanel = new ScheduleListPanel(schedule);
        scheduleListPanel.getRoot().prefWidthProperty().bind(this.getRoot().widthProperty());
        scheduleListPanel.getRoot().prefHeightProperty().bind(this.getRoot().heightProperty());

        outputView.getChildren().clear();
        outputView.getChildren().add(scheduleListPanel.getRoot());
    }

    /**
     * Updates the outputView panel accordingly with {@code patient} if commandResult is task related.
     */
    public void handleTask(Patient patient) {
        TaskListPanel taskListPanel = new TaskListPanel(patient);
        taskListPanel.getRoot().prefWidthProperty().bind(this.getRoot().widthProperty());
        taskListPanel.getRoot().prefHeightProperty().bind(this.getRoot().heightProperty());

        outputView.getChildren().clear();
        outputView.getChildren().add(taskListPanel.getRoot());
    }

    /**
     * Updates the outputView panel accordingly with {@code patient} if executed command is ViewPatient.
     */
    public void handleViewPatient(Patient patient) {
        handlePatient(patient, "Patient:");
    }

    /**
     * Updates the outputView panel accordingly with {@code patient} if executed command is AddPatient.
     */
    public void handleAddPatient(Patient patient) {
        handlePatient(patient, "Added Patient:");
    }

    /**
     * Updates the outputView panel accordingly with {@code patient} if executed command is EditPatient.
     */
    public void handleEditPatient(Patient patient) {
        handlePatient(patient, "Edited Patient:");
    }

    /**
     * Updates the outputView panel accordingly with {@code patient} if executed command is DeletePatient.
     */
    public void handleDeletePatient(Patient patient) {
        handlePatient(patient, "Deleted Patient:");
    }

    /**
     * Private method that updates the outputView panel
     * accordingly with {@code patient} and {@code headerString} if executed command is patient related.
     */
    private void handlePatient(Patient patient, String headerString) {
        UpdatedPatientCard updatedPersonCard = new UpdatedPatientCard(patient, headerString);
        updatedPersonCard.getRoot().prefWidthProperty().bind(this.getRoot().widthProperty());
        updatedPersonCard.getRoot().prefHeightProperty().bind(this.getRoot().heightProperty());

        outputView.getChildren().clear();
        outputView.getChildren().add(updatedPersonCard.getRoot());
    }

    /**
     * Updates the outputView panel accordingly with {@code patientListTracker} if executed command is Undo.
     */
    public void handleUndo(PatientListTracker patientListTracker) {
        outputView.getChildren().clear();
        UndoCard undoCard = new UndoCard(patientListTracker);
        outputView.getChildren().add(undoCard.getRoot());
    }

    /**
     * Updates the outputView panel accordingly with {@code patientListTracker} if executed command is Redo.
     */
    public void handleRedo(PatientListTracker patientListTracker) {
        outputView.getChildren().clear();
        RedoCard redoCard = new RedoCard(patientListTracker);
        outputView.getChildren().add(redoCard.getRoot());
    }

}

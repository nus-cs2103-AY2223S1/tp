package seedu.uninurse.ui;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.uninurse.model.PersonListTracker;
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
     * Updates the outputView panel accordingly with a list of {@code patient} if executed command is ListTask.
     */
    public void handleListTask(List<Patient> patients) {
        TruncatedTaskListPanel truncatedTaskListPanel = new TruncatedTaskListPanel(patients);
        truncatedTaskListPanel.getRoot().prefWidthProperty().bind(this.getRoot().widthProperty());
        truncatedTaskListPanel.getRoot().prefHeightProperty().bind(this.getRoot().heightProperty());

        outputView.getChildren().clear();
        outputView.getChildren().add(truncatedTaskListPanel.getRoot());
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
    public void handleUndo(PersonListTracker patientListTracker) {
        outputView.getChildren().clear();
        if (patientListTracker.isSinglePatient()) {
            outputView.getChildren().add(new ModifiedPatientCard(patientListTracker, true, false).getRoot());
        }

        if (patientListTracker.isMultiplePatients()) {
            outputView.getChildren().add(new UndoCard(patientListTracker).getRoot());
        }
    }

    /**
     * Updates the outputView panel accordingly with {@code patientListTracker} if executed command is Redo.
     */
    public void handleRedo(PersonListTracker patientListTracker) {
        outputView.getChildren().clear();
        if (patientListTracker.isSinglePatient()) {
            outputView.getChildren().add(new ModifiedPatientCard(patientListTracker, false, true).getRoot());
        }

        if (patientListTracker.isMultiplePatients()) {
            outputView.getChildren().add(new RedoCard(patientListTracker).getRoot());
        }
    }

    /**
     * Updates the outputView panel accordingly with {@code patients} if executed command is Find.
     */
    public void handleFind(List<Patient> patients) {
        outputView.getChildren().clear();
        outputView.getChildren().add(new UpdatedPersonListPanel(FXCollections.observableList(patients)).getRoot());
    }
}

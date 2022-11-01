package seedu.uninurse.ui;

import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.uninurse.model.ListModificationPair;
import seedu.uninurse.model.PatientListTracker;
import seedu.uninurse.model.condition.Condition;
import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.medication.Medication;
import seedu.uninurse.model.medication.MedicationList;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.remark.Remark;
import seedu.uninurse.model.remark.RemarkList;
import seedu.uninurse.model.task.RecurringTask;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.model.task.TaskList;

/**
 * An UI component that displays information of a {@code Patient} without index.
 * ModifiedPatientCard to be used for output panel when undoing and redoing results
 * in adding, editing, or deleting a patient.
 */
public class ModifiedPatientCard extends UiPart<Region> {
    private static final String FXML = "ModifiedPatientCard.fxml";

    private static final String RED_STYLE = "-fx-background-color: #ffc0bf;"
            + "-fx-border-radius: 2;"
            // + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);"
            + "-fx-background-radius: 5;"
            + "-fx-padding: 2;";
    private static final String GREEN_STYLE = "-fx-background-color: #c9ffdf;"
            + "-fx-border-radius: 2;"
            // + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);"
            + "-fx-background-radius: 5;"
            + "-fx-padding: 2;";

    private Patient patient;

    @FXML
    private VBox cardPane;
    @FXML
    private VBox headerPane;
    @FXML
    private Label header;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;
    @FXML
    private Label conditionHeader;
    @FXML
    private VBox conditionContainer;
    @FXML
    private Label medicationHeader;
    @FXML
    private VBox medicationContainer;
    @FXML
    private Label taskHeader;
    @FXML
    private VBox taskContainer;
    @FXML
    private Label remarkHeader;
    @FXML
    private VBox remarkContainer;
    @FXML
    private VBox conditionHeaderPane;
    @FXML
    private VBox medicationHeaderPane;
    @FXML
    private VBox taskHeaderPane;
    @FXML
    private VBox remarkHeaderPane;

    /**
     * Creates a {@code UpdatedPatientCard} with the given {@code Patient}.
     */
    public ModifiedPatientCard(PatientListTracker patientListTracker, boolean isUndo, boolean isRedo) {
        super(FXML);

        cardPane.setStyle("-fx-padding: 2;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 2;"
                + "-fx-border-radius: 2;" + "-fx-border-color: black;");

        // Undo add or Redo delete
        if (patientListTracker.isAdd() && isUndo || patientListTracker.isDelete() && isRedo) {
            this.headerPane.setStyle(RED_STYLE);
            this.header.setText("Removed previously added Patient:");
            if (isUndo) {
                this.patient = patientListTracker.getAddedPatients().get().get(0);
            }
            if (isRedo) {
                this.patient = patientListTracker.getDeletedPatients().get().get(0);
            }
        }

        // Undo delete or Redo add
        if (patientListTracker.isDelete() && isUndo || patientListTracker.isAdd() && isRedo) {
            this.headerPane.setStyle(GREEN_STYLE);
            this.header.setText("Added previously removed Patient:");
            if (isUndo) {
                this.patient = patientListTracker.getDeletedPatients().get().get(0);
            }
            if (isRedo) {
                this.patient = patientListTracker.getAddedPatients().get().get(0);
            }
        }

        Patient editedPatient = this.patient;
        if (patientListTracker.isEdit() && isUndo) {
            this.header.setStyle("");
            this.header.setText("Modified Patient:");
            this.patient = patientListTracker.getDeletedPatients().get().get(0);
            editedPatient = patientListTracker.getAddedPatients().get().get(0);
        }

        if (patientListTracker.isEdit() && isRedo) {
            this.header.setStyle("");
            this.header.setText("Modified Patient:");
            this.patient = patientListTracker.getAddedPatients().get().get(0);
            editedPatient = patientListTracker.getDeletedPatients().get().get(0);
        }

        name.setText(patient.getName().getValue());
        phone.setText(patient.getPhone().getValue());
        address.setText(patient.getAddress().getValue());
        email.setText(patient.getEmail().getValue());
        patient.getTags().getInternalList()
                .forEach(tag -> tags.getChildren().add(new Label(tag.getValue())));

        /* Conditions */
        conditionHeader.setText("Conditions");
        if (patient.getConditions().isEmpty()) {
            conditionContainer.getChildren().add(getEmptyConditionBox());
        } else {
            ConditionList conditionList = patient.getConditions();

            // only supports singular add/delete/edit operation checking
            List<ListModificationPair> modifiedConditions = conditionList.getDiff(editedPatient.getConditions());
            boolean hasModifiedConditions = !(modifiedConditions.isEmpty());

            for (int i = 0; i < conditionList.size(); i++) {
                HBox conditionBox = getConditionBox(i + 1, conditionList.get(i));

                if (hasModifiedConditions && modifiedConditions.get(0).getIndex() == i) {
                    if (modifiedConditions.get(0).isDelete()) {
                        conditionBox.setStyle(GREEN_STYLE);
                    } else if (modifiedConditions.get(0).isEdit()) {
                        conditionBox.setStyle(GREEN_STYLE);
                        HBox previousEditedConditionBox = getConditionBox(i + 1, editedPatient.getConditions().get(i));
                        previousEditedConditionBox.setStyle(RED_STYLE);

                        conditionContainer.getChildren().add(previousEditedConditionBox);
                    }
                }
                conditionContainer.getChildren().add(conditionBox);
            }

            if (hasModifiedConditions && modifiedConditions.get(0).isAdd()) {
                HBox previousEditedConditionBox = getConditionBox(
                        conditionList.size() + 1,
                        editedPatient.getConditions().get(modifiedConditions.get(0).getIndex()));
                previousEditedConditionBox.setStyle(RED_STYLE);

                conditionContainer.getChildren().add(previousEditedConditionBox);
            }
        }

        /* Medications */
        medicationHeader.setText("Medications");
        if (patient.getMedications().isEmpty()) {
            medicationContainer.getChildren().add(getEmptyMedicationBox());
        } else {
            MedicationList medicationList = patient.getMedications();

            // only supports singular add/delete/edit operation checking
            List<ListModificationPair> modifiedMedications = medicationList.getDiff(editedPatient.getMedications());
            boolean hasModifiedMedications = !(modifiedMedications.isEmpty());

            for (int i = 0; i < medicationList.size(); i++) {
                HBox medicationBox = getMedicationBox(i + 1, medicationList.get(i));

                if (hasModifiedMedications && modifiedMedications.get(0).getIndex() == i) {
                    if (modifiedMedications.get(0).isDelete()) {
                        medicationBox.setStyle(GREEN_STYLE);
                    } else if (modifiedMedications.get(0).isEdit()) {
                        medicationBox.setStyle(GREEN_STYLE);
                        HBox previousEditedMedicationBox =
                                getMedicationBox(i + 1, editedPatient.getMedications().get(i));
                        previousEditedMedicationBox.setStyle(RED_STYLE);

                        medicationContainer.getChildren().add(previousEditedMedicationBox);
                    }
                }
                medicationContainer.getChildren().add(medicationBox);
            }

            if (hasModifiedMedications && modifiedMedications.get(0).isAdd()) {
                HBox previousEditedMedicationBox = getMedicationBox(
                        medicationList.size() + 1,
                        editedPatient.getMedications().get(modifiedMedications.get(0).getIndex()));
                previousEditedMedicationBox.setStyle(RED_STYLE);

                medicationContainer.getChildren().add(previousEditedMedicationBox);
            }
        }

        /* Tasks */
        taskHeader.setText("Tasks");
        if (patient.getTasks().isEmpty()) {
            taskContainer.getChildren().add(getEmptyTaskBox());
        } else {
            TaskList taskList = patient.getTasks();

            // only supports singular add/delete/edit operation checking
            List<ListModificationPair> modifiedTasks = taskList.getDiff(editedPatient.getTasks());
            boolean hasModifiedTasks = !(modifiedTasks.isEmpty());

            for (int i = 0; i < taskList.size(); i++) {
                HBox taskBox = getTaskBox(i + 1, taskList.get(i));

                if (hasModifiedTasks && modifiedTasks.get(0).getIndex() == i) {
                    if (modifiedTasks.get(0).isDelete()) {
                        taskBox.setStyle(GREEN_STYLE);
                    } else if (modifiedTasks.get(0).isEdit()) {
                        taskBox.setStyle(GREEN_STYLE);
                        HBox previousEditedTaskBox = getTaskBox(i + 1, editedPatient.getTasks().get(i));
                        previousEditedTaskBox.setStyle(RED_STYLE);

                        taskContainer.getChildren().add(previousEditedTaskBox);
                    }
                }
                taskContainer.getChildren().add(taskBox);
            }

            if (hasModifiedTasks && modifiedTasks.get(0).isAdd()) {
                HBox previousEditedTaskBox = getTaskBox(
                        taskList.size() + 1, editedPatient.getTasks().get(modifiedTasks.get(0).getIndex()));
                previousEditedTaskBox.setStyle(RED_STYLE);

                taskContainer.getChildren().add(previousEditedTaskBox);
            }
        }

        /* Remarks */
        remarkHeader.setText("Remarks");
        if (patient.getRemarks().isEmpty()) {
            remarkContainer.getChildren().add(getEmptyRemarkBox());
        } else {
            RemarkList remarkList = patient.getRemarks();

            // only supports singular add/delete/edit operation checking
            List<ListModificationPair> modifiedRemarks = remarkList.getDiff(editedPatient.getRemarks());
            boolean hasModifiedRemarks = !(modifiedRemarks.isEmpty());

            for (int i = 0; i < remarkList.size(); i++) {
                HBox remarkBox = getRemarkBox(remarkList.get(i));

                if (hasModifiedRemarks && modifiedRemarks.get(0).getIndex() == i) {
                    if (modifiedRemarks.get(0).isDelete()) {
                        remarkBox.setStyle(GREEN_STYLE);
                    } else if (modifiedRemarks.get(0).isEdit()) {
                        remarkBox.setStyle(GREEN_STYLE);
                        HBox previousEditedRemarkBox = getRemarkBox(editedPatient.getRemarks().get(i));
                        previousEditedRemarkBox.setStyle(RED_STYLE);

                        remarkContainer.getChildren().add(previousEditedRemarkBox);
                    }
                }
                remarkContainer.getChildren().add(remarkBox);
            }

            if (hasModifiedRemarks && modifiedRemarks.get(0).isAdd()) {
                HBox previousEditedRemarkBox = getRemarkBox(
                        editedPatient.getRemarks().get(modifiedRemarks.get(0).getIndex()));
                previousEditedRemarkBox.setStyle(RED_STYLE);

                remarkContainer.getChildren().add(previousEditedRemarkBox);
            }
        }
    }

    private HBox getIndexBox(int index) {
        HBox indexBox = new HBox();
        indexBox.setMinWidth(20.0);
        indexBox.setMaxWidth(20.0);
        indexBox.setAlignment(Pos.CENTER);
        indexBox.setStyle("-fx-background-color: #bfcddb;"
                + "-fx-padding: 0 2 0 2;" + "-fx-border-radius: 2;"
                // + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);"
                + "-fx-background-radius: 5;");

        Label indexLabel = new Label(String.valueOf(index));
        indexLabel.setStyle("-fx-font-family: \"Open Sans Semibold\";"
                + "-fx-font-size: 13px;"
                + "-fx-text-fill: black;");

        indexBox.getChildren().add(indexLabel);
        return indexBox;
    }


    private HBox getEmptyConditionBox() {
        HBox conditionBox = new HBox();

        Label emptyConditionLabel = new Label("No conditions");
        emptyConditionLabel.setWrapText(true);
        emptyConditionLabel.setStyle("-fx-font-family: \"Open Sans Semibold\";"
                + "-fx-font-size: 13px;"
                + "-fx-text-fill: black;");

        conditionBox.getChildren().add(emptyConditionLabel);
        return conditionBox;
    }

    private HBox getConditionBox(int conditionIndex, Condition condition) {
        HBox conditionBox = new HBox();
        conditionBox.setSpacing(2.5);

        Label conditionLabel = new Label(condition.toString());
        conditionLabel.setWrapText(true);
        conditionLabel.setStyle("-fx-font-family: \"Open Sans Semibold\";"
                + "-fx-font-size: 13px;"
                + "-fx-text-fill: black;");

        conditionBox.getChildren().addAll(getIndexBox(conditionIndex), conditionLabel);
        return conditionBox;
    }

    private HBox getEmptyMedicationBox() {
        HBox medicationBox = new HBox();

        Label emptyMedicationLabel = new Label("No medications");
        emptyMedicationLabel.setWrapText(true);
        emptyMedicationLabel.setStyle("-fx-font-family: \"Open Sans Semibold\";"
                + "-fx-font-size: 13px;"
                + "-fx-text-fill: black;");

        medicationBox.getChildren().add(emptyMedicationLabel);
        return medicationBox;
    }

    private HBox getMedicationBox(int medicationIndex, Medication medication) {
        HBox medicationBox = new HBox();
        medicationBox.setSpacing(2.5);

        HBox medicationTypeBox = new HBox();
        medicationTypeBox.setStyle("-fx-background-color: #c2a8ff;"
                + "-fx-padding: 0 2 0 2;" + "-fx-border-radius: 2;"
                // + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);"
                + "-fx-background-radius: 5;");
        Label medicationTypeLabel = new Label(medication.getType());
        medicationTypeLabel.setWrapText(true);
        medicationTypeLabel.setStyle("-fx-font-family: \"Open Sans Semibold\";"
                + "-fx-font-size: 13px;"
                + "-fx-text-fill: black;");
        medicationTypeBox.getChildren().add(medicationTypeLabel);

        Label medicationDosageLabel = new Label(medication.getDosage());
        medicationDosageLabel.setWrapText(true);
        medicationDosageLabel.setStyle("-fx-font-family: \"Open Sans Semibold\";"
                + "-fx-font-size: 13px;"
                + "-fx-text-fill: black;");

        medicationBox.getChildren().addAll(getIndexBox(medicationIndex), medicationTypeBox, medicationDosageLabel);
        return medicationBox;
    }

    private HBox getEmptyTaskBox() {
        HBox taskBox = new HBox();

        Label emptyTaskLabel = new Label("No tasks");
        emptyTaskLabel.setWrapText(true);
        emptyTaskLabel.setStyle("-fx-font-family: \"Open Sans Semibold\";"
                + "-fx-font-size: 13px;"
                + "-fx-text-fill: black;");

        taskBox.getChildren().add(emptyTaskLabel);
        return taskBox;
    }

    private HBox getTaskBox(int taskIndex, Task task) {
        HBox taskBox = new HBox();
        taskBox.setSpacing(2.5);

        Label taskNameLabel = new Label(task.getTaskDescription());
        taskNameLabel.setWrapText(true);
        taskNameLabel.setStyle("-fx-font-family: \"Open Sans Semibold\";"
                + "-fx-font-size: 13px;"
                + "-fx-text-fill: black;");

        HBox taskDateBox = new HBox();
        taskDateBox.setMinWidth(72.5);
        taskDateBox.setStyle("-fx-background-color: #95b3e8;"
                + "-fx-padding: 0 2 0 2;" + "-fx-border-radius: 2;"
                // + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);"
                + "-fx-background-radius: 5;");
        Label taskDateLabel = new Label(task.getDateTime().getDate());
        taskDateLabel.setStyle("-fx-font-family: \"Open Sans Semibold\";"
                + "-fx-font-size: 13px;"
                + "-fx-text-fill: black;");
        taskDateBox.getChildren().add(taskDateLabel);

        HBox taskTimeBox = new HBox();
        taskTimeBox.setMinWidth(62.5);
        taskTimeBox.setStyle("-fx-background-color: #95b3e8;"
                + "-fx-padding: 0 2 0 2;" + "-fx-border-radius: 2;"
                // + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);"
                + "-fx-background-radius: 5;");
        Label taskTimeLabel = new Label(task.getDateTime().getTime());
        taskTimeLabel.setStyle("-fx-font-family: \"Open Sans Semibold\";"
                + "-fx-font-size: 13px;"
                + "-fx-text-fill: black;");
        taskTimeBox.getChildren().add(taskTimeLabel);

        if (task.getDateTime().isPastDate()) {
            taskDateBox.setStyle("-fx-background-color: #ebc000;"
                    + "-fx-padding: 0 2 0 2;" + "-fx-border-radius: 2;"
                    // + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);"
                    + "-fx-background-radius: 5;");
            taskTimeBox.setStyle("-fx-background-color: #ebc000;"
                    + "-fx-padding: 0 2 0 2;" + "-fx-border-radius: 2;"
                    // + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);"
                    + "-fx-background-radius: 5;");
        }

        // shit code quality be like
        /* TODO: improve shit */
        if (task instanceof RecurringTask) {
            task = (RecurringTask) task;
            HBox taskRecurrenceBox = new HBox();
            taskRecurrenceBox.setMinWidth(62.5);
            taskRecurrenceBox.setStyle("-fx-background-color: #95b3e8;"
                    + "-fx-padding: 0 2 0 2;" + "-fx-border-radius: 2;"
                    // + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);"
                    + "-fx-background-radius: 5;");
            Label taskRecurrenceLabel = new Label(task.getRecurrenceString());
            taskRecurrenceLabel.setStyle("-fx-font-family: \"Open Sans Semibold\";"
                    + "-fx-font-size: 13px;"
                    + "-fx-text-fill: black;");
            taskRecurrenceBox.getChildren().add(taskRecurrenceLabel);

            if (task.getDateTime().isPastDate()) {
                taskRecurrenceBox.setStyle("-fx-background-color: #ebc000;"
                        + "-fx-padding: 0 2 0 2;" + "-fx-border-radius: 2;"
                        // + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);"
                        + "-fx-background-radius: 5;");
            }
            taskBox.getChildren().addAll(
                    getIndexBox(taskIndex), taskDateBox, taskTimeBox, taskRecurrenceBox, taskNameLabel);
            return taskBox;
        }
        /* */

        taskBox.getChildren().addAll(getIndexBox(taskIndex), taskDateBox, taskTimeBox, taskNameLabel);
        return taskBox;
    }

    private HBox getEmptyRemarkBox() {
        HBox remarkBox = new HBox();
        remarkBox.setStyle("-fx-background-color: #b6ecfa;"
                + "-fx-padding: 5;" + "-fx-border-radius: 2;"
                // + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);"
                + "-fx-background-radius: 5;");

        Label remarkLabel = new Label("No remarks");
        remarkLabel.setWrapText(true);
        remarkLabel.setStyle("-fx-font-family: \"Open Sans Semibold\";"
                + "-fx-font-size: 13px;"
                + "-fx-text-fill: black;");

        remarkBox.getChildren().add(remarkLabel);
        return remarkBox;
    }

    private HBox getRemarkBox(Remark remark) {
        HBox remarkBox = new HBox();
        remarkBox.setStyle("-fx-background-color: #b6ecfa;"
                + "-fx-padding: 5;" + "-fx-border-radius: 2;"
                // + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);"
                + "-fx-background-radius: 5;");

        Label remarkLabel = new Label(remark.getValue());
        remarkLabel.setWrapText(true);
        remarkLabel.setStyle("-fx-font-family: \"Open Sans Semibold\";"
                + "-fx-font-size: 13px;"
                + "-fx-text-fill: black;");

        remarkBox.getChildren().add(remarkLabel);
        return remarkBox;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdatedPatientCard)) {
            return false;
        }

        // state check
        UpdatedPatientCard card = (UpdatedPatientCard) other;
        return patient.equals(card.patient);
    }
}

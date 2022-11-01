package seedu.uninurse.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.uninurse.model.condition.Condition;
import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.medication.Medication;
import seedu.uninurse.model.medication.MedicationList;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.remark.Remark;
import seedu.uninurse.model.task.RecurringTask;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.model.task.TaskList;

/**
 * An UI component that displays information of a {@code Patient} without index.
 * UpdatedPersonCard to be used for output panel when adding, editing, or deleting a patient.
 */
public class UpdatedPatientCard extends UiPart<Region> {

    private static final String FXML = "UpdatedPatientCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Patient patient;

    @FXML
    private VBox cardPane;
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
     * Creates a {@code UpdatedPatientCard} with the given {@code Patient} and {@code headerString}.
     */
    public UpdatedPatientCard(Patient patient, String headerString) {
        super(FXML);
        header.setText(headerString);

        cardPane.setStyle("-fx-padding: 2;" + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;" + "-fx-border-insets: 2;"
                + "-fx-border-radius: 2;" + "-fx-border-color: black;");
        this.patient = patient;

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
            for (int i = 0; i < conditionList.size(); i++) {
                Condition condition = conditionList.get(i);
                conditionContainer.getChildren().add(getConditionBox(i + 1, condition));
            }
        }

        /* Medications */
        medicationHeader.setText("Medications");
        if (patient.getMedications().isEmpty()) {
            medicationContainer.getChildren().add(getEmptyMedicationBox());
        } else {
            MedicationList medicationList = patient.getMedications();
            for (int i = 0; i < medicationList.size(); i++) {
                Medication medication = medicationList.get(i);
                medicationContainer.getChildren().add(getMedicationBox(i + 1, medication));
            }
        }

        /* Tasks */
        taskHeader.setText("Tasks");
        if (patient.getTasks().isEmpty()) {
            taskContainer.getChildren().add(getEmptyTaskBox());
        } else {
            TaskList taskList = patient.getTasks();
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                HBox taskBox = getTaskBox(i + 1, task);
                taskBox.prefWidthProperty().bind(taskContainer.widthProperty());
                taskContainer.getChildren().add(taskBox);
            }
        }

        /* Remarks */
        remarkHeader.setText("Remarks");
        if (patient.getRemarks().isEmpty()) {
            remarkContainer.getChildren().add(getEmptyRemarkBox());
        } else {
            for (Remark remark : patient.getRemarks().getInternalList()) {
                remarkContainer.getChildren().add(getRemarkBox(remark));
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

    private VBox getEmptyRemarkBox() {
        VBox remarkBox = new VBox();
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

    private VBox getRemarkBox(Remark remark) {
        VBox remarkBox = new VBox();
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

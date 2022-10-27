package seedu.uninurse.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.remark.Remark;
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
    private Label conditions;
    @FXML
    private Label medicationHeader;
    @FXML
    private Label medications;
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
    public UpdatedPatientCard(Patient patient, String headerString) {
        super(FXML);
        header.setText(headerString);

        cardPane.setSpacing(2);
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

        conditionHeader.setText("Conditions:");
        conditions.setText(getListAsText(patient.getConditions().toString(), "conditions"));

        medicationHeader.setText("Medications:");
        medications.setText(getListAsText(patient.getMedications().toString(), "medications"));

        taskHeader.setText("Tasks:");
        if (patient.getTasks().isEmpty()) {
            taskContainer.getChildren().add(getTaskBox(0, "No tasks", ""));
        } else {
            TaskList taskList = patient.getTasks();
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                taskContainer.getChildren().add(
                        getTaskBox(i + 1, task.getTaskDescription(), task.getDateTime().toString()));
            }
        }

        remarkHeader.setText("Remarks:");
        if (patient.getRemarks().isEmpty()) {
            remarkContainer.getChildren().add(getRemarkBox("No remarks"));
        } else {
            for (Remark remark : patient.getRemarks().getInternalList()) {
                remarkContainer.getChildren().add(getRemarkBox(remark.getValue()));
            }
        }
    }

    private String getListAsText(String list, String listType) {
        return list.isEmpty() ? "No " + listType : list;
    }

    private HBox getTaskBox(int taskIndex, String taskName, String taskTime) {
        HBox taskBox = new HBox();
        taskBox.setSpacing(2.5);

        Label taskNameLabel = new Label(taskName);
        taskNameLabel.setWrapText(true);
        taskNameLabel.setStyle("-fx-font-family: \"Open Sans Semibold\";"
                + "-fx-font-size: 13px;"
                + "-fx-text-fill: black;");

        if (taskIndex == 0) {
            taskBox.getChildren().add(taskNameLabel);
            return taskBox;
        }
        Label taskIndexLabel = new Label(String.valueOf(taskIndex));
        taskIndexLabel.setStyle("-fx-font-family: \"Open Sans Semibold\";"
                + "-fx-font-size: 13px;"
                + "-fx-text-fill: black;");

        VBox taskTimeBox = new VBox();
        taskTimeBox.setStyle("-fx-background-color: #95b3e8;"
                + "-fx-padding: 0 2 0 2;" + "-fx-border-radius: 2;"
                // + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);"
                + "-fx-background-radius: 5;");
        Label taskTimeLabel = new Label(taskTime);
        taskTimeLabel.setStyle("-fx-font-family: \"Open Sans Semibold\";"
                + "-fx-font-size: 13px;"
                + "-fx-text-fill: black;");
        taskTimeBox.getChildren().add(taskTimeLabel);

        taskBox.getChildren().addAll(taskIndexLabel, taskTimeBox, taskNameLabel);
        return taskBox;
    }

    private VBox getRemarkBox(String remark) {
        VBox remarkBox = new VBox();
        remarkBox.setStyle("-fx-background-color: #95b3e8;"
                + "-fx-padding: 5;" + "-fx-border-radius: 2;"
                // + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);"
                + "-fx-background-radius: 5;");

        Label remarkLabel = new Label(remark);
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

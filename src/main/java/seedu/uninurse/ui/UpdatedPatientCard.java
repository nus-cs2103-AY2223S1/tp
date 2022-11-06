package seedu.uninurse.ui;

import static seedu.uninurse.ui.UiUtil.getConditionBox;
import static seedu.uninurse.ui.UiUtil.getEmptyConditionBox;
import static seedu.uninurse.ui.UiUtil.getEmptyMedicationBox;
import static seedu.uninurse.ui.UiUtil.getEmptyRemarkBox;
import static seedu.uninurse.ui.UiUtil.getEmptyTaskBox;
import static seedu.uninurse.ui.UiUtil.getMedicationBox;
import static seedu.uninurse.ui.UiUtil.getRemarkBox;
import static seedu.uninurse.ui.UiUtil.getTaskBox;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.uninurse.model.condition.Condition;
import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.medication.Medication;
import seedu.uninurse.model.medication.MedicationList;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.remark.Remark;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.model.task.TaskList;

/**
 * An UI component that displays information of a Patient without index.
 * UpdatedPersonCard to be used for output panel when adding, editing, or deleting a patient.
 */
public class UpdatedPatientCard extends UiPart<Region> {
    private static final String FXML = "UpdatedPatientCard.fxml";

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
     * Creates a UpdatedPatientCard with the given Patient and headerString.
     */
    public UpdatedPatientCard(Patient patient, String headerString) {
        super(FXML);
        this.patient = patient;

        this.header.setText(headerString);
        this.cardPane.setId("patient_card");

        this.name.setText(patient.getName().getValue());
        this.phone.setText(patient.getPhone().getValue());
        this.address.setText(patient.getAddress().getValue());
        this.email.setText(patient.getEmail().getValue());
        patient.getTags().getInternalList()
                .forEach(tag -> tags.getChildren().add(new Label(tag.getValue())));

        /* Conditions */
        this.conditionHeader.setText("Conditions");
        if (patient.getConditions().isEmpty()) {
            this.conditionContainer.getChildren().add(getEmptyConditionBox());
        } else {
            ConditionList conditionList = patient.getConditions();
            for (int i = 0; i < conditionList.size(); i++) {
                Condition condition = conditionList.get(i);
                this.conditionContainer.getChildren().add(getConditionBox(i + 1, condition));
            }
        }

        /* Medications */
        this.medicationHeader.setText("Medications");
        if (patient.getMedications().isEmpty()) {
            this.medicationContainer.getChildren().add(getEmptyMedicationBox());
        } else {
            MedicationList medicationList = patient.getMedications();
            for (int i = 0; i < medicationList.size(); i++) {
                Medication medication = medicationList.get(i);
                this.medicationContainer.getChildren().add(getMedicationBox(i + 1, medication));
            }
        }

        /* Tasks */
        this.taskHeader.setText("Tasks");
        if (patient.getTasks().isEmpty()) {
            this.taskContainer.getChildren().add(getEmptyTaskBox());
        } else {
            TaskList taskList = patient.getTasks();
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                this.taskContainer.getChildren().add(getTaskBox(i + 1, task));
            }
        }

        /* Remarks */
        this.remarkHeader.setText("Remarks");
        if (patient.getRemarks().isEmpty()) {
            this.remarkContainer.getChildren().add(getEmptyRemarkBox());
        } else {
            for (Remark remark : patient.getRemarks().getInternalList()) {
                this.remarkContainer.getChildren().add(getRemarkBox(remark));
            }
        }
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
        UpdatedPatientCard o = (UpdatedPatientCard) other;
        return patient.equals(o.patient);
    }
}

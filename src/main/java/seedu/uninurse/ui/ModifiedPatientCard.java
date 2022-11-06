package seedu.uninurse.ui;

import static seedu.uninurse.ui.UiUtil.getConditionBox;
import static seedu.uninurse.ui.UiUtil.getEmptyConditionBox;
import static seedu.uninurse.ui.UiUtil.getEmptyMedicationBox;
import static seedu.uninurse.ui.UiUtil.getEmptyRemarkBox;
import static seedu.uninurse.ui.UiUtil.getEmptyTaskBox;
import static seedu.uninurse.ui.UiUtil.getMedicationBox;
import static seedu.uninurse.ui.UiUtil.getRemarkBox;
import static seedu.uninurse.ui.UiUtil.getTaskBox;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.uninurse.model.ListModificationPair;
import seedu.uninurse.model.PersonListTracker;
import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.medication.MedicationList;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.remark.RemarkList;
import seedu.uninurse.model.task.TaskList;

/**
 * An UI component that displays information of a Patient without index.
 * ModifiedPatientCard to be used for output panel when undoing and redoing results
 * in adding, editing, or deleting a patient.
 */
public class ModifiedPatientCard extends UiPart<Region> {
    private static final String FXML = "ModifiedPatientCard.fxml";

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
    public ModifiedPatientCard(PersonListTracker personListTracker, boolean isUndo, boolean isRedo) {
        super(FXML);

        this.cardPane.setId("patient_card");

        // Undo add or Redo delete
        if (personListTracker.isAdd() && isUndo || personListTracker.isDelete() && isRedo) {
            this.headerPane.setId("red_bordered_box");
            this.header.setText("Removed previously added Patient:");
            if (isUndo) {
                this.patient = (Patient) personListTracker.getAddedPersons().get().get(0);
            }
            if (isRedo) {
                this.patient = (Patient) personListTracker.getDeletedPersons().get().get(0);
            }
        }

        // Undo delete or Redo add
        if (personListTracker.isDelete() && isUndo || personListTracker.isAdd() && isRedo) {
            this.headerPane.setId("green_bordered_box");
            this.header.setText("Added previously removed Patient:");
            if (isUndo) {
                this.patient = (Patient) personListTracker.getDeletedPersons().get().get(0);
            }
            if (isRedo) {
                this.patient = (Patient) personListTracker.getAddedPersons().get().get(0);
            }
        }

        Patient editedPatient = this.patient;
        if (personListTracker.isEdit() && isUndo) {
            this.header.setStyle("");
            this.header.setText("Modified Patient:");
            this.patient = (Patient) personListTracker.getDeletedPersons().get().get(0);
            editedPatient = (Patient) personListTracker.getAddedPersons().get().get(0);
        }

        if (personListTracker.isEdit() && isRedo) {
            this.header.setStyle("");
            this.header.setText("Modified Patient:");
            this.patient = (Patient) personListTracker.getAddedPersons().get().get(0);
            editedPatient = (Patient) personListTracker.getDeletedPersons().get().get(0);
        }

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

            // only supports singular add/delete/edit operation checking
            List<ListModificationPair> modifiedConditions = conditionList.getDiff(editedPatient.getConditions());
            boolean hasModifiedConditions = !(modifiedConditions.isEmpty());

            for (int i = 0; i < conditionList.size(); i++) {
                HBox conditionBox = getConditionBox(i + 1, conditionList.get(i));

                if (hasModifiedConditions && modifiedConditions.get(0).getIndex() == i) {
                    if (modifiedConditions.get(0).isDelete()) {
                        conditionBox.setId("green_bordered_box");
                    } else if (modifiedConditions.get(0).isEdit()) {
                        conditionBox.setId("green_bordered_box");
                        HBox previousEditedConditionBox = getConditionBox(i + 1, editedPatient.getConditions().get(i));
                        previousEditedConditionBox.setId("red_bordered_box");

                        this.conditionContainer.getChildren().add(previousEditedConditionBox);
                    }
                }
                this.conditionContainer.getChildren().add(conditionBox);
            }

            if (hasModifiedConditions && modifiedConditions.get(0).isAdd()) {
                HBox previousEditedConditionBox = getConditionBox(
                        conditionList.size() + 1,
                        editedPatient.getConditions().get(modifiedConditions.get(0).getIndex()));
                previousEditedConditionBox.setId("red_bordered_box");

                this.conditionContainer.getChildren().add(previousEditedConditionBox);
            }
        }

        /* Medications */
        this.medicationHeader.setText("Medications");
        if (patient.getMedications().isEmpty()) {
            this.medicationContainer.getChildren().add(getEmptyMedicationBox());
        } else {
            MedicationList medicationList = patient.getMedications();

            // only supports singular add/delete/edit operation checking
            List<ListModificationPair> modifiedMedications = medicationList.getDiff(editedPatient.getMedications());
            boolean hasModifiedMedications = !(modifiedMedications.isEmpty());

            for (int i = 0; i < medicationList.size(); i++) {
                HBox medicationBox = getMedicationBox(i + 1, medicationList.get(i));

                if (hasModifiedMedications && modifiedMedications.get(0).getIndex() == i) {
                    if (modifiedMedications.get(0).isDelete()) {
                        medicationBox.setId("green_bordered_box");
                    } else if (modifiedMedications.get(0).isEdit()) {
                        medicationBox.setId("green_bordered_box");
                        HBox previousEditedMedicationBox =
                                getMedicationBox(i + 1, editedPatient.getMedications().get(i));
                        previousEditedMedicationBox.setId("red_bordered_box");

                        this.medicationContainer.getChildren().add(previousEditedMedicationBox);
                    }
                }
                this.medicationContainer.getChildren().add(medicationBox);
            }

            if (hasModifiedMedications && modifiedMedications.get(0).isAdd()) {
                HBox previousEditedMedicationBox = getMedicationBox(
                        medicationList.size() + 1,
                        editedPatient.getMedications().get(modifiedMedications.get(0).getIndex()));
                previousEditedMedicationBox.setId("red_bordered_box");

                this.medicationContainer.getChildren().add(previousEditedMedicationBox);
            }
        }

        /* Tasks */
        this.taskHeader.setText("Tasks");
        if (patient.getTasks().isEmpty()) {
            this.taskContainer.getChildren().add(getEmptyTaskBox());
        } else {
            TaskList taskList = patient.getTasks();

            // only supports singular add/delete/edit operation checking
            List<ListModificationPair> modifiedTasks = taskList.getDiff(editedPatient.getTasks());
            boolean hasModifiedTasks = !(modifiedTasks.isEmpty());

            for (int i = 0; i < taskList.size(); i++) {
                HBox taskBox = getTaskBox(i + 1, taskList.get(i));

                if (hasModifiedTasks && modifiedTasks.get(0).getIndex() == i) {
                    if (modifiedTasks.get(0).isDelete()) {
                        taskBox.setId("green_bordered_box");
                    } else if (modifiedTasks.get(0).isEdit()) {
                        taskBox.setId("green_bordered_box");
                        HBox previousEditedTaskBox = getTaskBox(i + 1, editedPatient.getTasks().get(i));
                        previousEditedTaskBox.setId("red_bordered_box");

                        this.taskContainer.getChildren().add(previousEditedTaskBox);
                    }
                }
                this.taskContainer.getChildren().add(taskBox);
            }

            if (hasModifiedTasks && modifiedTasks.get(0).isAdd()) {
                HBox previousEditedTaskBox = getTaskBox(
                        taskList.size() + 1, editedPatient.getTasks().get(modifiedTasks.get(0).getIndex()));
                previousEditedTaskBox.setId("red_bordered_box");

                this.taskContainer.getChildren().add(previousEditedTaskBox);
            }
        }

        /* Remarks */
        this.remarkHeader.setText("Remarks");
        if (patient.getRemarks().isEmpty()) {
            this.remarkContainer.getChildren().add(getEmptyRemarkBox());
        } else {
            RemarkList remarkList = patient.getRemarks();

            // only supports singular add/delete/edit operation checking
            List<ListModificationPair> modifiedRemarks = remarkList.getDiff(editedPatient.getRemarks());
            boolean hasModifiedRemarks = !(modifiedRemarks.isEmpty());

            for (int i = 0; i < remarkList.size(); i++) {
                HBox remarkBox = getRemarkBox(remarkList.get(i));

                if (hasModifiedRemarks && modifiedRemarks.get(0).getIndex() == i) {
                    if (modifiedRemarks.get(0).isDelete()) {
                        remarkBox.setId("green_bordered_box");
                    } else if (modifiedRemarks.get(0).isEdit()) {
                        remarkBox.setId("green_bordered_box");
                        HBox previousEditedRemarkBox = getRemarkBox(editedPatient.getRemarks().get(i));
                        previousEditedRemarkBox.setId("red_bordered_box");

                        this.remarkContainer.getChildren().add(previousEditedRemarkBox);
                    }
                }
                this.remarkContainer.getChildren().add(remarkBox);
            }

            if (hasModifiedRemarks && modifiedRemarks.get(0).isAdd()) {
                HBox previousEditedRemarkBox = getRemarkBox(
                        editedPatient.getRemarks().get(modifiedRemarks.get(0).getIndex()));
                previousEditedRemarkBox.setId("red_bordered_box");

                this.remarkContainer.getChildren().add(previousEditedRemarkBox);
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

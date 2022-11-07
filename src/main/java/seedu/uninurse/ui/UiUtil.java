package seedu.uninurse.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.uninurse.model.condition.Condition;
import seedu.uninurse.model.medication.Medication;
import seedu.uninurse.model.remark.Remark;
import seedu.uninurse.model.task.Task;

/**
 * Utility class to provide dynamically generated Ui parts.
 */
public class UiUtil {
    public static final double LIST_VIEW_OFFSET = 20.0;

    private UiUtil() {} // prevents instantiation

    public static HBox getIndexBox(int index) {
        HBox indexBox = new HBox();
        indexBox.setMinWidth(20.0);
        indexBox.setMaxWidth(20.0);
        indexBox.setAlignment(Pos.CENTER);
        indexBox.setId("index_box");

        Label indexLabel = new Label(String.valueOf(index));
        indexLabel.setId("attribute_box_label");

        indexBox.getChildren().add(indexLabel);
        return indexBox;
    }

    public static HBox getEmptyConditionBox() {
        HBox conditionBox = new HBox();
        conditionBox.setMinHeight(HBox.USE_PREF_SIZE);

        Label emptyConditionLabel = new Label("No conditions");
        emptyConditionLabel.setWrapText(true);
        emptyConditionLabel.setId("attribute_box_label");

        conditionBox.getChildren().add(emptyConditionLabel);
        return conditionBox;
    }

    public static HBox getConditionBox(int conditionIndex, Condition condition) {
        HBox conditionBox = new HBox();
        conditionBox.setMinHeight(HBox.USE_PREF_SIZE);
        conditionBox.setSpacing(2.5);

        Label conditionLabel = new Label(condition.toString());
        conditionLabel.setWrapText(true);
        conditionLabel.setId("attribute_box_label");

        conditionBox.getChildren().addAll(getIndexBox(conditionIndex), conditionLabel);
        return conditionBox;
    }

    public static HBox getEmptyMedicationBox() {
        HBox medicationBox = new HBox();
        medicationBox.setMinHeight(HBox.USE_PREF_SIZE);

        Label emptyMedicationLabel = new Label("No medications");
        emptyMedicationLabel.setWrapText(true);
        emptyMedicationLabel.setId("attribute_box_label");

        medicationBox.getChildren().add(emptyMedicationLabel);
        medicationBox.setMinHeight(HBox.USE_PREF_SIZE);
        return medicationBox;
    }

    public static HBox getMedicationBox(int medicationIndex, Medication medication) {
        HBox medicationBox = new HBox();
        medicationBox.setMinHeight(HBox.USE_PREF_SIZE);
        medicationBox.setSpacing(2.5);

        HBox medicationTypeBox = new HBox();
        medicationTypeBox.setId("medication_type_box");

        Label medicationTypeLabel = new Label(medication.getType());
        medicationTypeLabel.setWrapText(true);
        medicationTypeLabel.setId("attribute_box_label");
        medicationTypeBox.getChildren().add(medicationTypeLabel);

        Label medicationDosageLabel = new Label(medication.getDosage());
        medicationDosageLabel.setWrapText(true);
        medicationDosageLabel.setMinWidth(50.0);
        medicationDosageLabel.setId("attribute_box_label");

        medicationBox.getChildren().addAll(getIndexBox(medicationIndex), medicationTypeBox, medicationDosageLabel);
        return medicationBox;
    }

    public static HBox getEmptyTaskBox() {
        HBox taskBox = new HBox();
        taskBox.setMinHeight(HBox.USE_PREF_SIZE);

        Label emptyTaskLabel = new Label("No tasks");
        emptyTaskLabel.setWrapText(true);
        emptyTaskLabel.setId("attribute_box_label");

        taskBox.getChildren().add(emptyTaskLabel);
        return taskBox;
    }

    public static HBox getTaskBox(int taskIndex, Task task) {
        HBox taskBox = new HBox();
        taskBox.setMinHeight(HBox.USE_PREF_SIZE);
        taskBox.setSpacing(2.5);

        Label taskNameLabel = new Label(task.getTaskDescription());
        taskNameLabel.setWrapText(true);
        taskNameLabel.setId("attribute_box_label");

        HBox taskDateBox = new HBox();
        taskDateBox.setMinWidth(72.5);
        taskDateBox.setId("task_date_time_box");
        Label taskDateLabel = new Label(task.getDateTime().getDate());
        taskDateLabel.setId("attribute_box_label");
        taskDateBox.getChildren().add(taskDateLabel);

        HBox taskTimeBox = new HBox();
        taskTimeBox.setMinWidth(62.5);
        taskTimeBox.setId("task_date_time_box");
        Label taskTimeLabel = new Label(task.getDateTime().getTime());
        taskTimeLabel.setId("attribute_box_label");
        taskTimeBox.getChildren().add(taskTimeLabel);

        if (task.getDateTime().isPastDate()) {
            taskDateBox.setId("task_date_time_box_overdue");
            taskTimeBox.setId("task_date_time_box_overdue");
        }

        if (!(task.getRecurrenceString().equals("One-Time"))) {
            HBox taskRecurrenceBox = new HBox();
            taskRecurrenceBox.setMinWidth(65.0);
            taskRecurrenceBox.setId("task_date_time_box");
            Label taskRecurrenceLabel = new Label(task.getRecurrenceString());
            taskRecurrenceLabel.setWrapText(true);
            taskRecurrenceLabel.setId("attribute_box_label");
            taskRecurrenceBox.getChildren().add(taskRecurrenceLabel);

            if (task.getDateTime().isPastDate()) {
                taskRecurrenceBox.setId("task_date_time_box_overdue");
            }
            taskBox.getChildren().addAll(
                    getIndexBox(taskIndex), taskDateBox, taskTimeBox, taskRecurrenceBox, taskNameLabel);
            return taskBox;
        }

        taskBox.getChildren().addAll(getIndexBox(taskIndex), taskDateBox, taskTimeBox, taskNameLabel);
        return taskBox;
    }

    public static HBox getEmptyRemarkBox() {
        HBox remarkBox = new HBox();
        remarkBox.setMinHeight(HBox.USE_PREF_SIZE);
        remarkBox.setId("remark_box");

        Label remarkLabel = new Label("No remarks");
        remarkLabel.setWrapText(true);
        remarkLabel.setId("attribute_box_label");

        remarkBox.getChildren().add(remarkLabel);
        return remarkBox;
    }

    public static HBox getRemarkBox(Remark remark) {
        HBox remarkBox = new HBox();
        remarkBox.setMinHeight(HBox.USE_PREF_SIZE);
        remarkBox.setId("remark_box");

        Label remarkLabel = new Label(remark.getValue());
        remarkLabel.setWrapText(true);
        remarkLabel.setId("attribute_box_label");

        remarkBox.getChildren().add(remarkLabel);
        return remarkBox;
    }
}

/*
package seedu.uninurse.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.model.task.TaskList;

import java.util.Comparator;

//TODO: implement this

public class ScheduleCard extends UiPart<Region> {

    @FXML
    private StackPane taskListCardContainer;
    @FXML
    private Label name;
    @FXML
    private FlowPane tags;

    public ScheduleCard(Patient patient, Task task) {
        super(FXML);
        taskListCardContainer.getChildren().add((new TaskListCard(task, 1)).getRoot());
        name.setText(patient.getName().fullName);
        patient.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}


 */

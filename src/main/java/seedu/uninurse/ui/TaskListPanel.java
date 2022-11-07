package seedu.uninurse.ui;

import static seedu.uninurse.ui.UiUtil.LIST_VIEW_OFFSET;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.Task;

/**
 * Panel containing the list of tasks.
 */
public class TaskListPanel extends UiPart<Region> {
    private static final String FXML = "TaskListPanel.fxml";

    @FXML
    private ListView<Task> taskListView;
    @FXML
    private Label name;
    @FXML
    private FlowPane tags;
    @FXML
    private Label header;

    /**
     * Creates a TaskListPanel with the given Patient.
     */
    public TaskListPanel(Patient patient) {
        super(FXML);
        this.taskListView.setItems(FXCollections.observableList(patient.getTasks().getInternalList()));
        this.taskListView.setCellFactory(listview -> new TaskListViewCell());

        this.header.setText("Tasks:");
        this.name.setText(patient.getName().toString());
        patient.getTags().getInternalList()
                .forEach(tag -> tags.getChildren().add(new Label(tag.getValue())));
    }

    /**
     * Custom ListCell that displays the graphics of a Task using a TaskListCard.
     */
    class TaskListViewCell extends ListCell<Task> {
        TaskListViewCell() {
            super();
            setStyle("-fx-padding: 0 5 0 0");
            prefWidthProperty().bind(taskListView.widthProperty().subtract(LIST_VIEW_OFFSET));
        }

        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TaskListCard(task, getIndex() + 1).getRoot());
            }
        }
    }
}

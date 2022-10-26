package swift.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import swift.commons.core.LogsCenter;
import swift.model.task.Task;

/**
 * Panel containing the list of persons.
 */
public class PersonTaskListPanel extends UiPart<Region> {
    private static final String FXML = "PersonTaskListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonTaskListPanel.class);

    @FXML
    private ListView<Task> personTaskListView;
    @FXML
    private Label heading;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonTaskListPanel(ObservableList<Task> taskList) {
        super(FXML);
        personTaskListView.setItems(taskList);
        personTaskListView.setCellFactory(listView -> new PersonTaskListViewCell());
    }

    /**
     * Remove heading from the panel.
     */
    protected void removeHeading() {
        heading.setText("");
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonTaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonTaskCard(task, getIndex() + 1).getRoot());
            }
        }
    }
}

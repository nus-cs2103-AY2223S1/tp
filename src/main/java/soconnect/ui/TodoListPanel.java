package soconnect.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import soconnect.commons.core.LogsCenter;
import soconnect.model.todo.Todo;

/**
 * Panel containing the list of todos.
 */
public class TodoListPanel extends UiPart<Region> {
    private static final String FXML = "TodoListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TodoListPanel.class);

    @FXML
    private Label header;
    @FXML
    private ListView<Todo> todoListView;

    /**
     * Creates a {@code TodoListPanel} with the given todoHeader and {@code ObservableList}.
     */
    public TodoListPanel(String todoHeader, ObservableList<Todo> todoList) {
        super(FXML);
        header.setText(todoHeader);
        todoListView.setItems(todoList);
        todoListView.setCellFactory(listView -> new TodoListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Todo} using {@code TodoCard}.
     */
    class TodoListViewCell extends ListCell<Todo> {
        @Override
        protected void updateItem(Todo todo, boolean empty) {
            super.updateItem(todo, empty);

            if (empty || todo == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TodoCard(todo, getIndex() + 1).getRoot());
            }
        }
    }
}

package soconnect.ui;

import static java.util.Objects.requireNonNull;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import soconnect.logic.commands.todo.TodoShowCommand;
import soconnect.model.todo.Todo;

/**
 * Panel containing the list of todos.
 */
public class TodoListPanel extends UiPart<Region> {
    public static final String TODAY_HEADER = TodoShowCommand.TODAY_CONDITION;

    public static final String ALL_HEADER = "All";

    private static final String FXML = "TodoListPanel.fxml";

    @FXML
    private Label header;
    @FXML
    private ListView<Todo> todoListView;

    /**
     * Creates a {@code TodoListPanel} with the given todoHeader and {@code ObservableList}.
     */
    public TodoListPanel(SimpleStringProperty todoListHeader, ObservableList<Todo> todoList) {
        super(FXML);
        header.textProperty().bind(todoListHeader);
        todoListView.setItems(todoList);
        todoListView.setCellFactory(listView -> new TodoListViewCell());
    }

    /**
     * Formats the header displayed on the {@code TodoListPanel}, the first character
     * of the given {@code header} will be capitalized and the rest of the string remains unchanged.
     *
     * @param header The header of the {@code TodoListPanel}.
     * @return The formatted header.
     */
    public static String formatTodoHeader(String header) {
        requireNonNull(header);
        if (header.isEmpty()) {
            return header;
        }

        return header.substring(0, 1).toUpperCase() + header.substring(1);
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

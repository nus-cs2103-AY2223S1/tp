package soconnect.model;

import javafx.collections.ObservableList;
import soconnect.model.todo.Todo;

/**
 * Unmodifiable view of a {@code TodoList}.
 */
public interface ReadOnlyTodoList {

    /**
     * Returns an unmodifiable view of the {@code TodoList}.
     * This list will not contain any duplicate {@code Todo}s.
     */
    ObservableList<Todo> getTodoList();
}

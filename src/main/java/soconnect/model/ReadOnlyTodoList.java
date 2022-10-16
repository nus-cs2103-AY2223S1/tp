package soconnect.model;

import javafx.collections.ObservableList;
import soconnect.model.todo.Todo;

/**
 * Unmodifiable view of a todo list.
 */
public interface ReadOnlyTodoList {

    /**
     * Returns an unmodifiable view of the todo list.
     * This list will not contain any duplicate todos.
     */
    ObservableList<Todo> getTodoList();
}

package soconnect.model;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import soconnect.model.todo.Todo;
import soconnect.model.todo.UniqueTodoList;

/**
 * Wraps all data at the TodoList level.
 * Duplicate todo is not allowed (using .equals comparison).
 */
public class TodoList implements ReadOnlyTodoList {

    private final UniqueTodoList todos;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        todos = new UniqueTodoList();
    }

    public TodoList() {}

    /**
     * Creates an TodoList using the Todos in the {@code toBeCopied}.
     */
    public TodoList(ReadOnlyTodoList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the todo list with {@code todos}.
     * {@code todos} must not contain duplicate todos.
     */
    public void setTodos(List<Todo> todos) {
        this.todos.setTodos(todos);
    }

    /**
     * Resets the existing data of this {@code SoConnect} with {@code newData}.
     */
    public void resetData(ReadOnlyTodoList newData) {
        requireNonNull(newData);

        setTodos(newData.getTodoList());
    }

    //// todo-level operations

    /**
     * Returns true if a todo identical to {@code todo} exists in the TodoList.
     */
    public boolean hasTodo(Todo todo) {
        requireNonNull(todo);
        return todos.contains(todo);
    }

    /**
     * Adds a todo to the TodoList.
     * The todo must not already exist in the TodoList.
     */
    public void addTodo(Todo todo) {
        todos.add(todo);
    }

    /**
     * Replaces the given todo {@code target} in the list with {@code editedTodo}.
     * {@code target} must exist in the TodoList.
     * {@code editedTodo} must not already exist in the TodoList.
     */
    public void setTodo(Todo target, Todo editedTodo) {
        requireAllNonNull(target, editedTodo);

        todos.setTodo(target, editedTodo);
    }

    /**
     * Removes {@code key} from this {@code TodoList}.
     * {@code key} must exist in the TodoList.
     */
    public void removeTodo(Todo key) {
        todos.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return todos.asUnmodifiableObservableList().size() + " todos";
    }

    @Override
    public ObservableList<Todo> getTodoList() {
        return todos.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TodoList // instanceof handles nulls
            && todos.equals(((TodoList) other).todos));
    }

    @Override
    public int hashCode() {
        return todos.hashCode();
    }
}

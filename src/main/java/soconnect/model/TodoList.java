package soconnect.model;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import soconnect.model.tag.Tag;
import soconnect.model.todo.Todo;
import soconnect.model.todo.UniqueTodoList;

/**
 * Wraps all data at the {@code TodoList} level.
 * Duplicate {@code Todo} is not allowed (using .equals comparison).
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
     * Creates a {@code TodoList} using the {@code Todo}s in the {@code toBeCopied}.
     */
    public TodoList(ReadOnlyTodoList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the {@code TodoList} with {@code todos}.
     * {@code todos} must not contain duplicate {@code Todo}s.
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
     * Returns true if a {@code Todo} identical to {@code todo} exists in the {@code TodoList}.
     */
    public boolean hasTodo(Todo todo) {
        requireNonNull(todo);
        return todos.contains(todo);
    }

    /**
     * Adds a {@code Todo} to the {@code TodoList}.
     * The {@code Todo} must not already exist in the {@code TodoList}.
     */
    public void addTodo(Todo todo) {
        todos.add(todo);
    }

    /**
     * Replaces the given {@code Todo} in the {@code TodoList} with {@code editedTodo}.
     *
     * @param target Must exist in the {@code TodoList}.
     * @param editedTodo Must not already exist in the {@code TodoList}.
     */
    public void setTodo(Todo target, Todo editedTodo) {
        requireAllNonNull(target, editedTodo);

        todos.setTodo(target, editedTodo);
    }

    /**
     * Removes a {@code Todo} from this {@code TodoList}.
     * The {@code Todo} must exist in the {@code TodoList}.
     */
    public void removeTodo(Todo key) {
        todos.remove(key);
    }

    /**
     * Changes {@code oldTag} to {@code newTag}.
     *
     * @param oldTag The original {@code Tag}.
     * @param newTag The new {@code Tag}.
     */
    public void editTag(Tag oldTag, Tag newTag) {
        todos.changeRelevantTodoTag(oldTag, newTag);
    }

    /**
     * Deletes a {@code Tag} from the {@code TodoList}.
     *
     * @param tag The {@code Tag} to be deleted.
     */
    public void deleteTag(Tag tag) {
        todos.removeRelevantTodoTag(tag);
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

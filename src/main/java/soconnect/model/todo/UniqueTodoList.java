package soconnect.model.todo;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import soconnect.model.todo.exceptions.DuplicateTodoException;
import soconnect.model.todo.exceptions.TodoNotFoundException;

/**
 * A list of todos that enforces uniqueness between its elements and does not allow nulls.
 * Supports a minimal set of list operations.
 */
public class UniqueTodoList implements Iterable<Todo> {

    private final ObservableList<Todo> internalList = FXCollections.observableArrayList();
    private final ObservableList<Todo> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent todo as the given argument.
     */
    public boolean contains(Todo toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a todo to the list.
     * The todo must not already exist in the list.
     */
    public void add(Todo toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTodoException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the todo {@code target} in the list with {@code editedTodo}.
     * {@code target} must exist in the list.
     * The new todo must not be the same as an existing todo in the list.
     */
    public void setTodo(Todo target, Todo editedTodo) {
        requireAllNonNull(target, editedTodo);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TodoNotFoundException();
        }

        if (!target.equals(editedTodo) && contains(editedTodo)) {
            throw new DuplicateTodoException();
        }

        internalList.set(index, editedTodo);
    }

    /**
     * Removes the equivalent todo from the list.
     * The todo must exist in the list.
     */
    public void remove(Todo toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TodoNotFoundException();
        }
    }

    public void setTodos(UniqueTodoList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code todos}.
     * {@code todos} must not contain duplicate todos.
     */
    public void setTodos(List<Todo> todos) {
        requireAllNonNull(todos);
        if (!todosAreUnique(todos)) {
            throw new DuplicateTodoException();
        }

        internalList.setAll(todos);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Todo> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Todo> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueTodoList // instanceof handles nulls
            && internalList.equals(((UniqueTodoList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code todos} contains only unique todos.
     */
    private boolean todosAreUnique(List<Todo> todos) {
        for (int i = 0; i < todos.size() - 1; i++) {
            for (int j = i + 1; j < todos.size(); j++) {
                if (todos.get(i).equals(todos.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}

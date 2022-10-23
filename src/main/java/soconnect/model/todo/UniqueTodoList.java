package soconnect.model.todo;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import soconnect.model.tag.Tag;
import soconnect.model.todo.exceptions.DuplicateTodoException;
import soconnect.model.todo.exceptions.TodoNotFoundException;

/**
 * A list of {@code Todo}s that enforces uniqueness between its elements and does not allow nulls.
 * Supports a minimal set of list operations.
 */
public class UniqueTodoList implements Iterable<Todo> {

    private final ObservableList<Todo> internalList = FXCollections.observableArrayList();
    private final ObservableList<Todo> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent {@code Todo} as the given argument.
     */
    public boolean contains(Todo toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a {@code Todo} to the list.
     * The {@code Todo} must not already exist in the list.
     */
    public void add(Todo toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTodoException();
        }
        internalList.add(toAdd);
        sort();
    }

    /**
     * Replaces a {@code Todo} in the list with {@code editedTodo}.
     * {@code target} must exist in the list.
     * The {@code editedTodo} must not be the same as an existing {@code Todo} in the list.
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
        sort();
    }

    /**
     * Removes the equivalent {@code Todo} from the list.
     * The {@code Todo} must exist in the list.
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
        sort();
    }

    /**
     * Replaces the contents of this list with {@code todos}.
     * {@code todos} must not contain duplicate {@code Todo}s.
     */
    public void setTodos(List<Todo> todos) {
        requireAllNonNull(todos);
        if (!todosAreUnique(todos)) {
            throw new DuplicateTodoException();
        }

        internalList.setAll(todos);
        sort();
    }

    /**
     * Updates {@code oldTag} in every {@code Todo} that contains it.
     *
     * @param oldTag The old {@code Tag} to be changed.
     * @param newTag The new {@code Tag}.
     */
    public void changeRelevantTodoTag(Tag oldTag, Tag newTag) {
        for (int i = 0; i < internalList.size(); i++) {
            Todo oldTodo = internalList.get(i);
            List<Tag> todoTags = new ArrayList<>(oldTodo.getTags());
            if (todoTags.contains(oldTag)) {
                int index = todoTags.indexOf(oldTag);
                todoTags.set(index, newTag);
                Set<Tag> newTags = new HashSet<>(todoTags);
                Todo newTodo = new Todo(oldTodo.getDescription(), oldTodo.getPriority(), newTags);
                internalList.set(i, newTodo);
            }
        }
    }

    /**
     * Removes {@code tag} from every {@code Todo} that contains it.
     *
     * @param tag The {@code Tag} to be removed.
     */
    public void removeRelevantTodoTag(Tag tag) {
        for (int i = 0; i < internalList.size(); i++) {
            Todo oldTodo = internalList.get(i);
            List<Tag> todoTags = new ArrayList<>(oldTodo.getTags());
            if (todoTags.contains(tag)) {
                todoTags.remove(tag);
                Set<Tag> updatedTags = new HashSet<>(todoTags);
                Todo updatedTodo = new Todo(oldTodo.getDescription(), oldTodo.getPriority(), updatedTags);
                internalList.set(i, updatedTodo);
            }
        }
    }

    /**
     * Sort the {@code TodoList} in order of decreasing {@code Priority}.
     */
    private void sort() {
        SortedList<Todo> sorted = internalList.sorted((curr, next) -> curr.getPriority().compareTo(next.getPriority()));
        internalList.setAll(sorted);
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
     * Returns true if {@code todos} contains only unique {@code Todo}s.
     */
    private boolean todosAreUnique(List<Todo> todos) {
        return todos.stream().distinct().count() == (long) todos.size();
    }
}

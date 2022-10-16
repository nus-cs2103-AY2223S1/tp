package soconnect.model;

import java.nio.file.Path;
import java.util.TreeSet;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import soconnect.commons.core.GuiSettings;
import soconnect.model.person.Person;
import soconnect.model.tag.Tag;
import soconnect.model.todo.Todo;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true.
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true.
     */
    Predicate<Todo> PREDICATE_SHOW_ALL_TODOS = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' SoConnect file path.
     */
    Path getSoConnectFilePath();

    /**
     * Sets the user prefs' SoConnect file path.
     */
    void setSoConnectFilePath(Path soConnectFilePath);

    /**
     * Returns the user prefs' TodoList file path.
     */
    Path getTodoListFilePath();

    /**
     * Sets the user prefs' TodoList file path.
     */
    void setTodoListFilePath(Path todoListFilePath);

    /**
     * Returns the SoConnect.
     */
    ReadOnlySoConnect getSoConnect();

    /**
     * Replaces SoConnect data with the data in {@code soConnect}.
     */
    void setSoConnect(ReadOnlySoConnect soConnect);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the SoConnect.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the SoConnect.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the SoConnect.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the SoConnect.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the SoConnect.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Sorts the SoConnect by name in alphabetical order.
     *
     * @param isReverse Whether the sorting should be in reverse order.
     */
    void sortByName(Boolean isReverse);

    /**
     * Sorts the SoConnect by phone number in increasing order.
     *
     * @param isReverse Whether the sorting should be in reverse order.
     */
    void sortByPhone(Boolean isReverse);

    /**
     * Sorts the SoConnect by email in alphabetical order.
     *
     * @param isReverse Whether the sorting should be in reverse order.
     */
    void sortByEmail(Boolean isReverse);

    /**
     * Sorts the SoConnect by address in alphabetical order.
     *
     * @param isReverse Whether the sorting should be in reverse order.
     */
    void sortByAddress(Boolean isReverse);

    /**
     * Sorts the SoConnect by a tag.
     * Contacts with the tag appear before those without the tag.
     *
     * @param tag       The tag to sort with.
     * @param isReverse Whether the sorting should be in reverse order.
     */
    void sortByTag(Tag tag, Boolean isReverse);

    /**
     * Returns a set of unique {@code Name} in the SoConnect.
     */
    TreeSet<String> getUniqueNames();

    /**
     * Returns the TodoList.
     */
    ReadOnlyTodoList getTodoList();

    /**
     * Replaces TodoList data with the data in {@code todoList}.
     */
    void setTodoList(ReadOnlyTodoList todoList);

    /**
     * Returns true if a Todo identical to {@code todo} exists in the TodoList.
     */
    boolean hasTodo(Todo todo);

    /**
     * Deletes the given Todo.
     * The Todo must exist in the TodoList.
     */
    void deleteTodo(Todo target);

    /**
     * Adds the given Todo.
     * {@code todo} must not already exist in the TodoList.
     */
    void addTodo(Todo todo);

    /**
     * Replaces the given Todo {@code target} with {@code editedTodo}.
     * {@code target} must exist in the TodoList.
     * {@code editedTodo} must not be the same as another existing Todo in the TodoList.
     */
    void setTodo(Todo target, Todo editedTodo);

    /**
     * Returns an unmodifiable view of the filtered person list.
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns an unmodifiable view of the filtered todo list.
     */
    ObservableList<Todo> getFilteredTodoList();

    /**
     * Updates the filter of the filtered todo list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTodoList(Predicate<Todo> predicate);

    /**
     * Returns true if tag exists.
     *
     * @param tag The tag to be checked if it exists.
     * @return True if tag exists. False if otherwise.
     */
    boolean hasTag(Tag tag);

    /**
     * Adds a tag.
     *
     * @param tag The tag to be added.
     */
    void addTag(Tag tag);

    /**
     * Changes the oldTag to the newTag.
     *
     * @param oldTag The tag to be changed
     * @param newTag The tag to be changed into
     */
    void editTag(Tag oldTag, Tag newTag);

}

package soconnect.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import javafx.beans.property.SimpleStringProperty;
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
     * {@code Predicate} that always evaluate to false.
     */
    Predicate<Person> PREDICATE_SHOW_NO_PERSON = unused -> false;

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
     * Returns the user prefs' attribute order in GUI settings.
     */
    String getAttributeOrder();

    /**
     * Returns the user prefs' hidden attributes in GUI settings.
     */
    String getHiddenAttributes();

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
     * Returns true if all the tags in the contact exist in the Tag list.
     *
     * @param person The person to be added.
     * @return True if all the tags exist in the tagList. False if otherwise.
     */
    boolean areTagsAvailable(Person person);

    /**
     * Returns true if all the {@code Tag}s in the {@code Todo} exist in the Tag list.
     *
     * @param todo The {@code Todo} to check.
     * @return True if all the {@code Tag}s exist. False if otherwise.
     */
    boolean areTagsAvailable(Todo todo);

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
     * Sorts the SoConnect by {@code Name} in alphabetical order.
     *
     * @param isReverse Whether the sorting should be in reverse order.
     */
    void sortByName(Boolean isReverse);

    /**
     * Sorts the SoConnect by {@code Phone} in increasing order.
     *
     * @param isReverse Whether the sorting should be in reverse order.
     */
    void sortByPhone(Boolean isReverse);

    /**
     * Sorts the SoConnect by {@code Email} in alphabetical order.
     *
     * @param isReverse Whether the sorting should be in reverse order.
     */
    void sortByEmail(Boolean isReverse);

    /**
     * Sorts the SoConnect by {@code Address} in alphabetical order.
     *
     * @param isReverse Whether the sorting should be in reverse order.
     */
    void sortByAddress(Boolean isReverse);

    /**
     * Sorts the SoConnect by a {@code Tag}.
     * Contacts with the {@code Tag} appear before those without the {@code Tag}.
     *
     * @param tag       The {@code Tag} to sort with.
     * @param isReverse Whether the sorting should be in reverse order.
     */
    void sortByTag(Tag tag, Boolean isReverse);

    /**
     * Returns the TodoList.
     */
    ReadOnlyTodoList getTodoList();

    /**
     * Replaces {@code TodoList} data with the data in {@code todoList}.
     */
    void setTodoList(ReadOnlyTodoList todoList);

    /**
     * Returns true if a {@code Todo} identical to {@code todo} exists in the {@code TodoList}.
     */
    boolean hasTodo(Todo todo);

    /**
     * Deletes the given {@code Todo}.
     *
     * @param target Must exist in the {@code TodoList}.
     */
    void deleteTodo(Todo target);

    /**
     * Adds the given {@code Todo}.
     *
     * @param todo Must not already exist in the {@code TodoList}.
     */
    void addTodo(Todo todo);

    /**
     * Replaces the given {@code Todo} with {@code editedTodo}.
     *
     * @param target Must exist in the {@code TodoList}.
     * @param editedTodo Must not be the same as another existing {@code Todo} in the {@code TodoList}.
     */
    void setTodo(Todo target, Todo editedTodo);

    /**
     * Returns an unmodifiable view of the filtered person list.
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns true if the filtered person list is empty.
     */
    Boolean isFilteredPersonListEmpty();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns an unmodifiable view of the filtered {@code TodoList}.
     */
    ObservableList<Todo> getFilteredTodoList();

    /**
     * Updates the filter of the filtered {@code TodoList} to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTodoList(Predicate<Todo> predicate);

    /**
     * Returns the header to be displayed on the {@code TodoListPanel}.
     */
    SimpleStringProperty getTodoListHeader();

    /**
     * Updates the header of the {@code TodoListPanel}.
     *
     * @param header The new header
     */
    void updateTodoListHeader(String header);
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
     * @param oldTag The tag to be changed.
     * @param newTag The tag to be changed into.
     */
    void editTag(Tag oldTag, Tag newTag);

    /**
     * Deletes the tag from the tagList.
     *
     * @param tag The tag to be deleted.
     */
    void deleteTag(Tag tag);

    /**
     * Returns the right tag from the taglist.
     *
     * @param tag The reference tag.
     * @return the tag that has the same name as the reference tag.
     */
    Tag getTagFromList(Tag tag);

    /**
     * Gets the tagList.
     *
     * @return The tagList.
     */
    ArrayList<Tag> getTagList();

    /**
     * Sets the tagList in SoConnect.
     *
     * @param tagList The tagList to be set.
     */
    void setTagList(List<Tag> tagList);

}

package soconnect.model;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import soconnect.commons.core.GuiSettings;
import soconnect.commons.core.LogsCenter;
import soconnect.model.person.Person;
import soconnect.model.tag.Tag;
import soconnect.model.todo.Todo;

/**
 * Represents the in-memory model of the SoConnect data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final SoConnect soConnect;
    private final TodoList todoList;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Todo> filteredTodos;
    private final ObservableList<Tag> tags;

    /**
     * Initializes a ModelManager with the given soConnect, todoList, and userPrefs.
     */
    public ModelManager(ReadOnlySoConnect soConnect, ReadOnlyTodoList todoList, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(soConnect, todoList, userPrefs);

        logger.fine("Initializing with SoConnect: " + soConnect + ", TodoList: " + " and user prefs " + userPrefs);

        this.soConnect = new SoConnect(soConnect);
        this.todoList = new TodoList(todoList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.soConnect.getPersonList());
        filteredTodos = new FilteredList<>(this.todoList.getTodoList());
        this.tags = this.soConnect.getTagList();
    }

    public ModelManager() {
        this(new SoConnect(), new TodoList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getSoConnectFilePath() {
        return userPrefs.getSoConnectFilePath();
    }

    @Override
    public void setSoConnectFilePath(Path soConnectFilePath) {
        requireNonNull(soConnectFilePath);
        userPrefs.setSoConnectFilePath(soConnectFilePath);
    }

    @Override
    public Path getTodoListFilePath() {
        return userPrefs.getTodoListFilePath();
    }

    @Override
    public void setTodoListFilePath(Path todoListFilePath) {
        requireNonNull(todoListFilePath);
        userPrefs.setTodoListFilePath(todoListFilePath);
    }

    //=========== SoConnect ================================================================================

    @Override
    public ReadOnlySoConnect getSoConnect() {
        return soConnect;
    }

    @Override
    public void setSoConnect(ReadOnlySoConnect soConnect) {
        this.soConnect.resetData(soConnect);
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return soConnect.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        soConnect.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        soConnect.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        soConnect.setPerson(target, editedPerson);
    }

    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return soConnect.hasTag(tag);
    }

    @Override
    public void addTag(Tag tag) {
        soConnect.addTag(tag);
    }

    @Override
    public void editTag(Tag oldTag, Tag newTag) {
        soConnect.editTag(oldTag, newTag);
    }

    @Override
    public void sortByName(Boolean isReverse) {
        soConnect.sortByName(isReverse);
    }

    @Override
    public void sortByPhone(Boolean isReverse) {
        soConnect.sortByPhone(isReverse);
    }

    @Override
    public void sortByEmail(Boolean isReverse) {
        soConnect.sortByEmail(isReverse);
    }

    @Override
    public void sortByAddress(Boolean isReverse) {
        soConnect.sortByAddress(isReverse);
    }

    @Override
    public void sortByTag(Tag tag, Boolean isReverse) {
        soConnect.sortByTag(tag, isReverse);
    }

    @Override
    public TreeSet<String> getUniqueNames() {
        return soConnect.getUniqueNames();
    }

    //=========== TodoList ================================================================================

    @Override
    public ReadOnlyTodoList getTodoList() {
        return todoList;
    }

    @Override
    public void setTodoList(ReadOnlyTodoList todoList) {
        this.todoList.resetData(todoList);
    }

    @Override
    public boolean hasTodo(Todo todo) {
        requireNonNull(todo);
        return todoList.hasTodo(todo);
    }

    @Override
    public void deleteTodo(Todo target) {
        todoList.removeTodo(target);
    }

    @Override
    public void addTodo(Todo todo) {
        todoList.addTodo(todo);
        updateFilteredTodoList(PREDICATE_SHOW_ALL_TODOS);
    }

    @Override
    public void setTodo(Todo target, Todo editedTodo) {
        requireAllNonNull(target, editedTodo);

        todoList.setTodo(target, editedTodo);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedSoConnect}.
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public Boolean isFilteredPersonListEmpty() {
        return filteredPersons.size() == 0;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered TodoList Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedTodoList}.
     */
    @Override
    public ObservableList<Todo> getFilteredTodoList() {
        return filteredTodos;
    }

    @Override
    public void updateFilteredTodoList(Predicate<Todo> predicate) {
        requireNonNull(predicate);
        filteredTodos.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return soConnect.equals(other.soConnect)
                && todoList.equals(other.todoList)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredTodos.equals(other.filteredTodos);
    }

}

package soconnect.logic;

import java.nio.file.Path;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import soconnect.commons.core.GuiSettings;
import soconnect.logic.autocomplete.Autocomplete;
import soconnect.logic.commands.CommandResult;
import soconnect.logic.commands.exceptions.CommandException;
import soconnect.logic.parser.exceptions.ParseException;
import soconnect.model.Model;
import soconnect.model.ReadOnlySoConnect;
import soconnect.model.ReadOnlyTodoList;
import soconnect.model.person.Person;
import soconnect.model.todo.Todo;

/**
 * API of the Logic component.
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return The result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the SoConnect.
     *
     * @see Model#getSoConnect()
     */
    ReadOnlySoConnect getSoConnect();

    /**
     * Returns the {@code TodoList}.
     *
     * @see Model#getTodoList()
     */
    ReadOnlyTodoList getTodoList();

    /**
     * Returns an unmodifiable view of the filtered list of persons.
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the filtered list of {@code Todo}s.
     */
    ObservableList<Todo> getFilteredTodoList();

    /**
     * Returns the header of the {@code TodoListPanel}.
     */
    SimpleStringProperty getTodoListHeader();

    /**
     * Returns the user prefs' SoConnect file path.
     */
    Path getSoConnectFilePath();

    /**
     * Returns the user prefs' {@code TodoList} file path.
     */
    Path getTodoListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
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
     * Returns the AutocompleteManager.
     */
    Autocomplete getAutocompleteManager();
}

package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyPersonModel;
import seedu.address.model.ReadOnlyPropertyModel;
import seedu.address.model.person.Person;
import seedu.address.model.property.Property;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the PersonModel.
     *
     * @see seedu.address.model.Model#getPersonModel()
     */
    ReadOnlyPersonModel getPersonModel();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the PropertyModel.
     *
     * @see seedu.address.model.Model#getPropertyModel()
     */
    ReadOnlyPropertyModel getPropertyModel();

    /** Returns an unmodifiable view of the filtered list of properties */
    ObservableList<Property> getFilteredPropertyList();

    /**
     * Returns the user prefs' person model file path.
     */
    Path getPersonModelFilePath();

    /**
     * Returns the user prefs' property model file path.
     */
    Path getPropertyModelFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}

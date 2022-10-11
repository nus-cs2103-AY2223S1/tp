package bookface.logic;

import java.nio.file.Path;

import bookface.commons.core.GuiSettings;
import bookface.logic.commands.CommandResult;
import bookface.logic.commands.exceptions.CommandException;
import bookface.logic.parser.exceptions.ParseException;
import bookface.model.Model;
import bookface.model.ReadOnlyBookFace;
import bookface.model.book.Book;
import bookface.model.person.Person;
import javafx.collections.ObservableList;

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
     * Returns the BookFace.
     *
     * @see Model#getBookFace()
     */
    ReadOnlyBookFace getBookFace();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of books */
    ObservableList<Book> getFilteredBookList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getBookFaceFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}

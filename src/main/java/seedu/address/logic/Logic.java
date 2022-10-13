package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;

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
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Student> getFilteredStudentList();


    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Tutor> getFilteredTutorList();

    /** Returns an unmodifiable view of the filtered class list */
    ObservableList<TuitionClass> getFilteredTuitionClassList();

    /**
     * Returns the user prefs' tutor address book file path.
     */
    Path getTutorAddressBookFilePath();

    /**
     * Returns the user prefs' student address book file path.
     */
    Path getStudentAddressBookFilePath();

    /**
     * Returns the user prefs' tuition class address book file path.
     */
    Path getTuitionClassAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /** Updates the type of the current list **/
    void updateCurrentListType(Model.ListType type);

    /** Returns the type of the current list **/
    Model.ListType getCurrentListType();
}

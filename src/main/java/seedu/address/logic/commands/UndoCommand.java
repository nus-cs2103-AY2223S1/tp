package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

/**
 * Undoes the last modifying operator. An undo can be undone, effectively
 * cancelling the effects of one another.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undoes the "
            + "last modifying operator. An undo can be undone, effectively "
            + "cancelling the effects of one another.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_UNDO_MESSAGE = "Undid last action: %1$s";

    public static final String NO_ACTION_TO_UNDO = "No action to undo.";


    private static Command prepareLastCommand = null;
    private static ReadOnlyAddressBook prepareAddressBook = null;
    private static ObservableList<Person> prepareFilteredPersons = null;
    private static SortedList<Person> prepareSortedPersons = null;
    private static ObservableList<Person> prepareGroupedPersons = null;

    private static Command lastCommand = null;

    private static ReadOnlyAddressBook undoAddressBook = null;
    private static ObservableList<Person> undoFilteredPersons = null;
    private static SortedList<Person> undoSortedPersons = null;
    private static ObservableList<Person> undoGroupedPersons = null;

    /*
     * For every possibly modifying command, add this just before model is
     * modified:
     * UndoCommand.prepareSaveModelBefore(this, model);
     * and this just before the method returns:
     * UndoCommand.saveBeforeMod(model);
     * of their execute functions.
     */

    /**
     * Saves the previous state of the model before it changes.
     * @param lastCommand The command that potentially modifies the model.
     * @param model The model itself.
     * @param mask A bitmask of the parts of the model that may change.
     */
    public static void prepareSaveModelBefore(Command lastCommand, Model model, int mask) {
        prepareLastCommand = lastCommand;
        if ((mask & 1) == 1) {
            prepareAddressBook = (ReadOnlyAddressBook) model.getAddressBook().clone();
        } else {
            prepareAddressBook = undoAddressBook;
        }
        if ((mask & 2) == 2) {
            prepareFilteredPersons = FXCollections.observableArrayList(model.getFilteredPersonList());
        } else {
            prepareFilteredPersons = undoFilteredPersons;
        }
        if ((mask & 4) == 4) {
            prepareSortedPersons = new SortedList<>(model.getSortedPersonList());
        } else {
            prepareSortedPersons = undoSortedPersons;
        }
        if ((mask & 8) == 8) {
            prepareGroupedPersons = FXCollections.observableArrayList(model.getGroupedPersonList());
        } else {
            prepareGroupedPersons = undoGroupedPersons;
        }
    }
    /**
     * Checks if a command modifies the {@code AddressBook} and saves it if
     * it did.
     * @param model The model after modification.
     */
    public static void saveBeforeMod(Model model) {
        if (isNull(prepareAddressBook)
            || isNull(prepareFilteredPersons)
            || isNull(prepareSortedPersons)
            || isNull(prepareGroupedPersons)
            || prepareAddressBook.equals(model.getAddressBook())
            && prepareFilteredPersons.equals(model.getFilteredPersonList())
            && prepareSortedPersons.equals(model.getSortedPersonList())
            && prepareGroupedPersons.equals(model.getGroupedPersonList())) {
            return;
        }
        lastCommand = prepareLastCommand;
        undoAddressBook = prepareAddressBook;
        undoFilteredPersons = prepareFilteredPersons;
        undoSortedPersons = prepareSortedPersons;
        undoGroupedPersons = prepareGroupedPersons;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (undoAddressBook == null) {
            throw new CommandException(NO_ACTION_TO_UNDO);
        }
        prepareSaveModelBefore(this, model, 15);
        model.setAddressBook(undoAddressBook);
        saveBeforeMod(model);
        return new CommandResult(String.format(SHOWING_UNDO_MESSAGE,
                lastCommand));
    }
}

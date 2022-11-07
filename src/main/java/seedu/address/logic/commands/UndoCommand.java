package seedu.address.logic.commands;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.storage.HistoryList;

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


    private static String prepareLastCommand = null;
    private static ReadOnlyAddressBook prepareAddressBook = null;

    private static String lastCommand = null;

    private static ReadOnlyAddressBook undoAddressBook = null;

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
     * @param model The model itself.
     */
    public static void prepareSaveModelBefore(Model model) {
        prepareLastCommand = HistoryList.getList().getLast();
        prepareAddressBook = (ReadOnlyAddressBook) model.getAddressBook().clone();
    }
    /**
     * Checks if a command modifies the {@code AddressBook} and saves it if
     * it did.
     * @param model The model after modification.
     */
    public static void saveBeforeMod(Model model) {
        if (isNull(prepareAddressBook)
            || prepareAddressBook.equals(model.getAddressBook())) {
            return;
        }
        lastCommand = prepareLastCommand;
        undoAddressBook = prepareAddressBook;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (isNull(prepareAddressBook)) {
            throw new CommandException(NO_ACTION_TO_UNDO);
        }
        UndoCommand.prepareSaveModelBefore(model);
        model.setAddressBook(undoAddressBook);
        String undidCommand = lastCommand;
        saveBeforeMod(model);
        return new CommandResult(String.format(SHOWING_UNDO_MESSAGE,
                undidCommand));
    }
}

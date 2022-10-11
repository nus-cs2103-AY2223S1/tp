package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;

import static java.util.Objects.requireNonNull;

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

    public static Command lastCommand = null;

    public static ReadOnlyAddressBook undoAddressBook = null;

    /*
     * For every possibly modifying command, add this just before model is
     * modified:
     * ReadOnlyAddressBook pastAddressBook =
     *         (ReadOnlyAddressBook) model.getAddressBook().clone();
     * and this just before the method returns:
     * UndoCommand.saveBeforeMod(this, pastAddressBook,
     *         model.getAddressBook());
     * of their execute functions.
     */
    public static void saveBeforeMod(Command lastCommand, ReadOnlyAddressBook
            pastAddressBook, ReadOnlyAddressBook newAddressBook) {
        if (pastAddressBook.equals(newAddressBook)) {
            return;
        }
        UndoCommand.lastCommand = lastCommand;
        undoAddressBook = pastAddressBook;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (undoAddressBook == null) {
            throw new CommandException(NO_ACTION_TO_UNDO);
        }
        ReadOnlyAddressBook pastAddressBook = (ReadOnlyAddressBook)
                model.getAddressBook().clone();
        model.setAddressBook(undoAddressBook);
        UndoCommand.saveBeforeMod(this, pastAddressBook,
                model.getAddressBook());
        return new CommandResult(String.format(SHOWING_UNDO_MESSAGE,
                lastCommand));
    }
}

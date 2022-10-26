package paymelah.logic.commands;

import static java.util.Objects.requireNonNull;
import static paymelah.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import paymelah.logic.commands.exceptions.CommandException;
import paymelah.model.Model;

/**
 * Undo the last command that modified the AddressBook of PayMeLah.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_SUCCESS = "Undid command:\n%1$s";
    public static final String MESSAGE_EMPTY_UNDO_HISTORY = "There are no commands to undo.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getHistoriesSize() == 0) {
            throw new CommandException(MESSAGE_EMPTY_UNDO_HISTORY);
        }

        model.undoAddressBook();
        String previousCommandMessage = model.popPreviousCommandMessage();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, previousCommandMessage));
    }

}

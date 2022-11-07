package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.HistoryList;

/**
 * Lists all previous commands typed out to the user.
 */
public class HistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";

    public static final String MESSAGE_SUCCESS = "Previous commands used: \n";

    public static final String MESSAGE_EMPTY = "No previously typed commands!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (HistoryList.isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY);
        }
        return new CommandResult(MESSAGE_SUCCESS + HistoryList.printList());
    }
}

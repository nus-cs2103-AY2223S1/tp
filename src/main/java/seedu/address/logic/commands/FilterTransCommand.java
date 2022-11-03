package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Filters all the transaction by all the clients.
 */
public class FilterTransCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters buy or sell transactions.\n"
            + "Parameters: TYPE (Must be either 'buy' or 'sell')\n"
            + "Example: " + COMMAND_WORD + " buy";

    public static final String MESSAGE_SUCCESS = "All %1$s transactions are filtered in the transaction panel.\n"
            + "All clients are listed in the client panel.";

    private final boolean isBuy;

    public FilterTransCommand(boolean isBuy) {
        this.isBuy = isBuy;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, isBuy ? "buy" : "sell"),
            false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterTransCommand)) {
            return false;
        }

        // state check
        FilterTransCommand e = (FilterTransCommand) other;
        return isBuy == e.isBuy;
    }

}

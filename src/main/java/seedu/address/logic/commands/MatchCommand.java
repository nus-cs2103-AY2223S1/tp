package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.order.Order;

/**
 * Matches pets given an order.
 */
public class MatchCommand extends Command {

    public static final String COMMAND_WORD = "match";

    private final Index index;

    /**
     * Constructs a MatchCommand object.
     * @param index The index of the order that needs to be matched to pets.
     */
    public MatchCommand(Index index) {
        this.index = index;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}

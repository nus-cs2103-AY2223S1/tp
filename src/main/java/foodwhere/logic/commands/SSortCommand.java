package foodwhere.logic.commands;

import static java.util.Objects.requireNonNull;

import foodwhere.logic.commands.exceptions.CommandException;
import foodwhere.model.Model;

/**
 * Sort and list all stalls in the address book to the user.
 */
public class SSortCommand extends Command {

    public static final String COMMAND_WORD = "ssort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort the stall list by name.\n";

    public static final String MESSAGE_SUCCESS = "The stall list is now sorted by name";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.sortStalls();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

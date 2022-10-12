package seedu.condonery.logic.commands.client;

import static java.util.Objects.requireNonNull;
import static seedu.condonery.model.Model.PREDICATE_SHOW_ALL_CLIENTS;

import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.model.Model;

/**
 * Lists all properties in the address book to the user.
 */
public class ListClientCommand extends Command {

    public static final String COMMAND_WORD = "list -c";

    public static final String MESSAGE_SUCCESS = "Listed all clients";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredClientList(PREDICATE_SHOW_ALL_CLIENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

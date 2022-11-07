package seedu.condonery.logic.commands.client;

import static java.util.Objects.requireNonNull;

import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.model.Model;
import seedu.condonery.model.client.ClientDirectory;

/**
 * Clears the client directory.
 */
public class ClearClientCommand extends Command {

    public static final String COMMAND_WORD = "clear -c";
    public static final String MESSAGE_SUCCESS = "Client directory has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setClientDirectory(new ClientDirectory());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}


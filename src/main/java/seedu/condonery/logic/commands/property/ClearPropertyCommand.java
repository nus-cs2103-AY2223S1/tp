package seedu.condonery.logic.commands.property;

import static java.util.Objects.requireNonNull;

import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.model.Model;
import seedu.condonery.model.property.PropertyDirectory;

/**
 * Clears the property directory.
 */
public class ClearPropertyCommand extends Command {

    public static final String COMMAND_WORD = "clear -p";
    public static final String MESSAGE_SUCCESS = "Property directory has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setPropertyDirectory(new PropertyDirectory());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

package seedu.condonery.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.condonery.model.Model;
import seedu.condonery.model.PropertyDirectory;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setPropertyDirectory(new PropertyDirectory());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

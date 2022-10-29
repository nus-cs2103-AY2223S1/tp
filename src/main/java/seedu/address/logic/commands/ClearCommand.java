package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.HealthContact;
import seedu.address.model.Model;

/**
 * Clears the HealthContact.
 */
public class ClearCommand extends Command {

    public static final CommandWord COMMAND_WORD = new CommandWord("clear");
    public static final String MESSAGE_SUCCESS = "HealthContact has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setHealthContact(new HealthContact());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

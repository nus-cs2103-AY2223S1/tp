package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class InspectCommand extends Command {

    public static final String COMMAND_WORD = "inspect";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Inspects the person identified by the index number used or by name registered"
            + " in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) <OR> NAME\n"
            + "Example: " + COMMAND_WORD + " Alex";

    public static final String MESSAGE_INSPECT_PERSON = "Inspecting Person";

    private final String[] splitName;

    public InspectCommand(String[] splitName) {
        this.splitName = splitName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(MESSAGE_INSPECT_PERSON, CommandResult.UiState.Inspect, splitName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InspectCommand // instanceof handles nulls
                && splitName.equals(((InspectCommand) other).splitName)); // state check
    }
}

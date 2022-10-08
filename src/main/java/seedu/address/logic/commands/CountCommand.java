package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Returns count of all patients in the hospital.
 */
public class CountCommand extends Command {
    public static final String COMMAND_WORD = "count";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows count of all patients in the hospital.\n"
            + "Parameters: NONE\n"
            + "Example: count";
    public static final String MESSAGE_COUNT = "Current patient count: %d";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_COUNT, model.getPersonCount()));
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof CountCommand;
    }
}

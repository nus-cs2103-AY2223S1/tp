package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.Survin;

/**
 * Clears Survin.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Survin has been cleared!";
    public static final String MESSAGE_USAGE = "Clears all persons from Survin.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setSurvin(new Survin());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

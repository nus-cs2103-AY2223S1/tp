package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Changes mode to Light Mode.
 */
public class LightModeCommand extends Command {

    public static final String COMMAND_WORD = "light";

    public static final String MESSAGE_SUCCESS = "Switched to light mode.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, false, false, false,
                false, false, false,
                false, false, false, true, false);
    }
}

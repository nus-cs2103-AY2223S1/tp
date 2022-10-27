package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Toggles the PersonCards between a compacted or an expanded mode.
 */
public class ToggleListModeCommand extends Command {

    public static final String COMMAND_WORD = "toggle-list-mode";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Toggles the entries of the list between a compacted mode or "
            + "an expanded mode depending on the current mode\n"
            + "Usage: " + COMMAND_WORD;

    public static final String TOGGLED_MODE_MESSAGE = "Toggled list mode";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(TOGGLED_MODE_MESSAGE, false, false, true);
    }
}

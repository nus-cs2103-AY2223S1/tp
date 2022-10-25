package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Toggles the PersonCard view between compacted and expanded depending on the current view.
 */
public class ToggleCardViewCommand extends Command {

    public static final String COMMAND_WORD = "toggle-card-view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Toggles the PersonCard view between compacted and expanded depending on the current view\n"
            + "Usage: " + COMMAND_WORD;

    public static final String TOGGLED_VIEW_MESSAGE = "Toggled PersonCard view";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(TOGGLED_VIEW_MESSAGE, false, false, true);
    }
}

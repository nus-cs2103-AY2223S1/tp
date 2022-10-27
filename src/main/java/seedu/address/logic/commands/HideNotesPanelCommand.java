package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HideNotesPanelCommand extends Command {

    public static final String COMMAND_WORD = "hideNotes";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Hides the notes panel.\n"
            + "Example: " + COMMAND_WORD;

    public static final String HIDING_NOTES_PANEL_MESSAGE = "Hiding notes panel";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HIDING_NOTES_PANEL_MESSAGE, CommandResult.UiState.HideNotes);
    }
}

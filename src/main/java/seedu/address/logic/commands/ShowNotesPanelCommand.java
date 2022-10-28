package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class ShowNotesPanelCommand extends Command {

    public static final String COMMAND_WORD = "showNotes";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the notes panel.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_NOTES_PANEL_MESSAGE = "Showing notes panel";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_NOTES_PANEL_MESSAGE, CommandResult.UiState.ShowNotes);
    }
}

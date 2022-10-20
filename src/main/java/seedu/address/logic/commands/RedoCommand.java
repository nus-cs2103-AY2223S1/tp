package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Redoes last command that was undone.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_REDO_ACKNOWLEDGEMENT = "Previous command redid.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_REDO_ACKNOWLEDGEMENT, false, false, true, false);
    }
}

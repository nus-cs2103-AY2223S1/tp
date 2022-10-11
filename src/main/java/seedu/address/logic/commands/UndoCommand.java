package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Undoes last undoable command.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_UNDO_ACKNOWLEDGEMENT = "Previous command undone.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_UNDO_ACKNOWLEDGEMENT, false, true, false, false);
    }
}

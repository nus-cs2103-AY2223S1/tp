package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic, ability to be executed and ability to be undone.
 */

public abstract class UndoableCommand extends Command{

    /**
     * Undoes the last action made.
     *
     * @param model {@code Model} which the command should operate on.
     */
    public abstract CommandResult undo(Model model);

    /**
     * Redoes the last action that was undone
     *
     * @param model {@code Model} which the command should operate on.
     */
    public abstract CommandResult redo(Model model);
}
